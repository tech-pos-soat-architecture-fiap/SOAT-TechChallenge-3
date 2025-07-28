package br.com.fiap.TechFood.infrastructure.adapter.out.order.entity;

import br.com.fiap.TechFood.application.domain.order.OrderItem;
import br.com.fiap.TechFood.infrastructure.adapter.out.product.entity.ProductEntity;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

@Embeddable
public class OrderItemEntity {
    @NotNull
    @Positive
    private BigDecimal price = BigDecimal.ZERO;
    @PositiveOrZero
    private int quantity;
    @NotNull
    @ManyToOne
    private ProductEntity product;

    @Deprecated
    public OrderItemEntity() {
    }

    public OrderItemEntity(OrderItem orderItem) {
        this.price = orderItem.getSubTotal();
        this.quantity = orderItem.getQuantity();
        this.product = new ProductEntity(orderItem.getProduct());
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public OrderItem toOrderItem() {
        return new OrderItem(this.quantity, this.product.toProduct());
    }
}
