package br.com.fiap.TechFood.infrastructure.adapter.in.order;

import br.com.fiap.TechFood.application.domain.Order;

public record OrderStatusView(Long id, String statusName) {
    public static OrderStatusView from(Order order) {
        return new OrderStatusView(order.getId(), order.getStatusName());
    }
}
