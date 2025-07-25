package br.com.fiap.TechFood.infrastructure.adapter.in.order;

import br.com.fiap.TechFood.application.domain.Order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public record OrderView(Long id, LocalDateTime createdAt, Set<OrderItemView> items, BigDecimal total,
                        Long userId) {

    public static OrderView from(Order order) {
        return new OrderView(order.getId(), order.getCreatedAt(),
                order.getOrderItems().stream().map(OrderItemView::from).collect(Collectors.toSet()),
                order.getTotal(),
                order.getUserId());
    }

}
