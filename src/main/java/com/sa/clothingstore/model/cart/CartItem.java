package com.sa.clothingstore.model.cart;

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
@Table(name = "cart_item")
public class CartItem {
    @EmbeddedId
    CartItemKey id;
    @ManyToOne
    @MapsId("cartId")
    @JoinColumn(name = "cart_id")
    private Cart cart;
    @ManyToOne
    @MapsId("productItemId")
    @JoinColumn(name = "product_item")
    private ProductItem productItem;
    @Column(name = "quantity")
    private Integer quantity;
}
