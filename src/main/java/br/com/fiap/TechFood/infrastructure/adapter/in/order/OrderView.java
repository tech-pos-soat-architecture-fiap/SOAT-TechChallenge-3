package br.com.fiap.TechFood.infrastructure.adapter.in.order;

import br.com.fiap.TechFood.application.domain.order.Order;
import br.com.fiap.TechFood.infrastructure.adapter.out.payment.PaymentStatusView;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public record OrderView(Long id, LocalDateTime createdAt, Set<OrderItemView> items, BigDecimal total,
                        Long userId, OrderStatusView orderStatusView, PaymentStatusView paymentStatus) {

    public static OrderView from(Order order) {
        return new OrderView(
                order.getId(),
                order.getCreatedAt(),
                order.getOrderItems().stream().map(OrderItemView::from).collect(Collectors.toSet()),
                order.getTotal(),
                order.getUserId(),
                OrderStatusView.from(order),
                order.getPayment()
                        .map(PaymentStatusView::from)
                        .orElse(null)
        );
    }
}

