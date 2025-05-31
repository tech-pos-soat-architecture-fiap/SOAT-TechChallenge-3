package br.com.fiap.TechFood.application.core.domain.order;

import br.com.fiap.TechFood.application.core.domain.user.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Order {
    private Long id;
    private User user;
    private BigDecimal total = BigDecimal.ZERO;
    private LocalDateTime createdAt = LocalDateTime.now();
    private OrderStatus status = OrderStatus.RECEIVED;
    private Set<OrderItem> orderItems = new HashSet<>();
    //TODO colocar o payment aqui(?)

    public Order(Long id, User user, BigDecimal total,
                 OrderStatus status, Set<OrderItem> orderItems) {
        this.id = id;
        this.user = user;
        this.total = total;
        this.status = status;
        this.orderItems = orderItems;
    }

    public User getUser() {
        return user;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }
}
