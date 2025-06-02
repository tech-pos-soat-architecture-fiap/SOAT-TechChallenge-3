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

    public static Order createDraft(User user) {
        return new Order(user, OrderStatus.DRAFT);
    }

    private Order(User user, OrderStatus status) {
        this.user = user;
        this.status = status;
    }

    public Order(Long id, User user, BigDecimal total,
                 OrderStatus status, Set<OrderItem> orderItems) {
        this.id = id;
        this.user = user;
        this.total = total;
        this.status = status;
        this.orderItems = orderItems;
    }

    public Long getId() {
        return id;
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

    public String getStatusName() {
        return status.name();
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
