package br.com.fiap.TechFood.infrastructure.adapter.in.order;

import br.com.fiap.TechFood.application.core.domain.order.Order;
import br.com.fiap.TechFood.application.core.domain.order.OrderStatus;

import java.math.BigDecimal;

public record OrderView(BigDecimal total, OrderStatus status) {

    public static OrderView from(Order order) {
        return new OrderView(order.getTotal(), order.getStatus());
    }

}
