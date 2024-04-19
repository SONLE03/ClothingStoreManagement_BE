package com.sa.clothingstore.model.order;

import com.sa.clothingstore.model.product.ProductItem;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "order_item")
public class OrderItem {
    @EmbeddedId
    OrderItemKey id;
    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    private Order order;
    @ManyToOne
    @MapsId("productItemId")
    @JoinColumn(name = "product_item")
    private ProductItem productItem;
    @Column(name = "quantity")
    private Integer quantity;
}
