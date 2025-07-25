package br.com.fiap.TechFood.infrastructure.adapter.in.order;

import br.com.fiap.TechFood.application.usecases.order.OrderItem;

import java.math.BigDecimal;

public record OrderItemView(int quantity, BigDecimal price, String productName) {
    public static OrderItemView from(OrderItem orderItem) {
        return new OrderItemView(orderItem.getQuantity(), orderItem.getSubTotal(), orderItem.getProduct().getName());
    }
}
