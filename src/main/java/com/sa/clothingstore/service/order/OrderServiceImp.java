package com.sa.clothingstore.service.order;

import com.sa.clothingstore.constant.APIStatus;
import com.sa.clothingstore.dto.request.cart.CartRequest;
import com.sa.clothingstore.dto.request.order.OrderItemRequest;
import com.sa.clothingstore.dto.request.order.OrderRequest;
import com.sa.clothingstore.exception.BusinessException;
import com.sa.clothingstore.exception.ObjectNotFoundException;
import com.sa.clothingstore.model.CommonModel;
import com.sa.clothingstore.model.event.Coupon;
import com.sa.clothingstore.model.order.Order;
import com.sa.clothingstore.model.order.OrderItem;
import com.sa.clothingstore.model.order.OrderItemKey;
import com.sa.clothingstore.model.order.OrderStatus;
import com.sa.clothingstore.model.product.ProductItem;
import com.sa.clothingstore.repository.event.CouponRepository;
import com.sa.clothingstore.repository.order.OrderItemRepository;
import com.sa.clothingstore.repository.order.OrderRepository;
import com.sa.clothingstore.repository.payment.PaymentRepository;
import com.sa.clothingstore.repository.product.ProductItemRepository;
import com.sa.clothingstore.repository.user.customer.AddressRepository;
import com.sa.clothingstore.repository.user.customer.CustomerRepository;
import com.sa.clothingstore.service.cart.CartService;
import com.sa.clothingstore.service.user.service.UserDetailService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class OrderServiceImp implements OrderService{
    private final UserDetailService userDetailService;
    private final CustomerRepository customerRepository;
    private final ProductItemRepository productItemRepository;
    private final AddressRepository addressRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ModelMapper modelMapper;
    private final CouponRepository couponRepository;
    private final PaymentRepository paymentRepository;
    private final CartService cartService;

    @Override
    public List<Order> getAllOrder() {
        return null;
    }

    @Override
    public List<Order> getAllOrderByCustomer(UUID customerId) {
        return null;
    }
    @Transactional
    @Override
    public void createOrder(OrderRequest orderRequest) {
        var customer = orderRequest.getCustomerId();
        var address = orderRequest.getAddressId();
        if(!addressRepository.existsAddressForCustomer(customer, address)){
            throw new BusinessException(APIStatus.CUSTOMER_ADDRESS_NOT_FOUND);
        }
        Coupon coupon;
        var couponId = orderRequest.getCoupon();
        if(couponId == null){
            coupon = null;
        }else{
            coupon = couponRepository.findById(couponId).orElse(null);
        }
        BigDecimal _total = (coupon != null) ? coupon.getDiscountValue().negate() : BigDecimal.ZERO;
        // Shipping fee
        _total.add(new BigDecimal(35000));

        Order order = Order.builder()
                .note(orderRequest.getNote())
                .orderStatus(OrderStatus.PENDING)
                .address(addressRepository.findById(address).orElseThrow(
                        () -> new BusinessException(APIStatus.ADDRESS_NOT_FOUND)))
                .customer(customerRepository.findById(customer).orElseThrow(
                        () -> new BusinessException(APIStatus.CUSTOMER_NOT_FOUND)))
                .paymentMethod(paymentRepository.findById(orderRequest.getPaymentMethod()).orElseThrow(
                        () -> new BusinessException(APIStatus.PAYMENT_NOT_FOUND)))
                .shippingFee(new BigDecimal(35000))
                .coupon(coupon)
                .orderStatus(OrderStatus.PENDING)
                .build();
        orderRepository.save(order);
        List<CartRequest> cartList = orderRequest.getItems();
        List<OrderItem> orderItems = new ArrayList<>();
        for(CartRequest item : cartList){
            ProductItem productItem = productItemRepository.findById(item.getProductItemId()).orElseThrow(
                    () -> new BusinessException(APIStatus.PRODUCT_ITEM_NOT_FOUND));

            Integer quantity = item.getQuantity();
            BigDecimal price = productItem.getProduct().getPrice();
            BigDecimal total = price.multiply(BigDecimal.valueOf(quantity));

            _total = _total.add(total);

            OrderItemKey orderItemKey = new OrderItemKey();
            orderItemKey.setOrderId(order.getId());
            orderItemKey.setProductItemId(productItem.getId());

            OrderItem orderItem = new OrderItem();
            orderItem.setId(orderItemKey);
            orderItem.setOrder(order);
            orderItem.setProductItem(productItem);
            orderItem.setQuantity(quantity);
            orderItem.setPrice(price);
            orderItem.setTotal(total);

            productItem.setQuantity(productItem.getQuantity() - quantity);
            productItemRepository.save(productItem);

            orderItems.add(orderItem);
        }
        cartService.deleteProductInCart(customer, cartList);
        order.setTotal(_total);
        order.setCommonCreate(userDetailService.getIdLogin());
        orderItemRepository.saveAll(orderItems);
    }

    @Override
    public String updateOrderStatus(UUID orderId, OrderRequest orderRequest) {
        switch (orderRequest.getStatus()) {
            case 1:
                updateOrderStatusToCanceled(orderId);
                return "Order status updated to Canceled";
            case 2:
                updateOrderStatusToDelivered(orderId);
                return "Order status updated to Delivered";
            case 3:
                updateOrderStatusToCompleted(orderId);
                return "Order status updated to Completed";
            default:
                return "Order status cannot be modified";
        }
    }


    private void updateOrderStatusToCanceled(UUID orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new BusinessException(APIStatus.ORDER_NOT_FOUND));
        if(order.getOrderStatus() != OrderStatus.PENDING) {
            throw new BusinessException(APIStatus.ORDER_NOT_CANCEL);
        }
        for(OrderItem items : order.getOrderItems()){
            ProductItem productItem = productItemRepository.findById(items.getProductItem().getId()).orElseThrow(
                    () -> new BusinessException(APIStatus.PRODUCT_ITEM_NOT_FOUND));
            productItem.setQuantity(productItem.getQuantity() + items.getQuantity());
            productItemRepository.save(productItem);
        }
        order.setOrderStatus(OrderStatus.CANCELED);
        order.setCanceledAt(CommonModel.resultTimestamp());
        order.setCommonUpdate(userDetailService.getIdLogin());
        orderRepository.save(order);
//        return "Order canceled successfully";
    }


    private void updateOrderStatusToDelivered(UUID orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new BusinessException(APIStatus.ORDER_NOT_FOUND));
        OrderStatus orderStatus = order.getOrderStatus();
        if(orderStatus == OrderStatus.CANCELED || orderStatus == OrderStatus.COMPLETED){
            throw new BusinessException(APIStatus.ORDER_NOT_SHIPPING);
        }
        order.setOrderStatus(OrderStatus.DELIVERED);
        order.setShippingAt(CommonModel.resultTimestamp());
        order.setCommonUpdate(userDetailService.getIdLogin());
        orderRepository.save(order);
//        return "Order delivered successfully";
    }


    private void updateOrderStatusToCompleted(UUID orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new BusinessException(APIStatus.ORDER_NOT_FOUND));
        OrderStatus orderStatus = order.getOrderStatus();
        if(orderStatus == OrderStatus.CANCELED || orderStatus == OrderStatus.PENDING){
            throw new BusinessException(APIStatus.ORDER_NOT_COMPLETE);
        }
        order.setOrderStatus(OrderStatus.COMPLETED);
        order.setCompletedAt(CommonModel.resultTimestamp());
        order.setCommonUpdate(userDetailService.getIdLogin());
        orderRepository.save(order);
//        return "Order completed successfully";
    }
}
