package br.com.fiap.TechFood.application.usecases.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class Order {
    private Long id;
    private Long userId;
    private final LocalDateTime createdAt = LocalDateTime.now();
    private OrderStatus status = OrderStatus.DRAFT;
    private Set<OrderItem> orderItems = new HashSet<>();

    public static Order createDraft(Long userId) {
        return new Order(userId);
    }

    private Order(Long user) {
        this.userId = user;
    }

    public Order(Long id, Long userId,
                 OrderStatus status, Set<OrderItem> orderItems) {
        this.id = id;
        this.userId = userId;
        this.status = status;
        this.orderItems = orderItems;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public BigDecimal getTotal() {
        return orderItems.stream()
                .map(OrderItem::getSubTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public boolean isNotFinished() {
        return !status.is(OrderStatus.FINISHED);
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

    public void addItems(List<OrderItem> items) {
        items.forEach(this::addItem);
    }


    private void addItem(OrderItem item) {
        Optional<OrderItem> existingItem = orderItems.stream()
                .filter(cartItem -> cartItem.getProductId().equals(item.getProductId()))
                .findFirst();

        if (existingItem.isPresent()) {
            OrderItem currentItem = existingItem.get();
            currentItem.setQuantity(currentItem.getQuantity() + item.getQuantity());
        } else {
            orderItems.add(item);
        }
    }

    public void removeItems(List<OrderItem> items) {
        items.forEach(this::removeItem);
    }

    public void removeItem(OrderItem item) {
        Optional<OrderItem> existingItem = orderItems.stream()
                .filter(cartItem -> cartItem.getProductId().equals(item.getProductId()))
                .findFirst();

        if (existingItem.isPresent()) {
            int newQuantity = existingItem.get().getQuantity() - item.getQuantity();
            if (newQuantity > 0) {
                existingItem.get().setQuantity(newQuantity);
            } else {
                orderItems.remove(existingItem.get());
            }
        }
    }

    public boolean isNotDraft() {
        return !status.is(OrderStatus.DRAFT);
    }

}
