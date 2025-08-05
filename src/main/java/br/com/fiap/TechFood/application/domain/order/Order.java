package br.com.fiap.TechFood.application.domain.order;

import br.com.fiap.TechFood.application.domain.payment.Payment;
import br.com.fiap.TechFood.application.domain.payment.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class Order {
    private Long id;
    private Long userId;
    private LocalDateTime createdAt = LocalDateTime.now();
    private OrderStatus status = OrderStatus.DRAFT;
    private Set<OrderItem> orderItems = new HashSet<>();
    private Payment payment;

    public static Order createDraft(Long userId) {
        return new Order(userId);
    }

    private Order(Long user) {
        this.userId = user;
    }

    public Order(Long id, Long userId, OrderStatus status, Payment payment, Set<OrderItem> orderItems, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.status = status;
        this.orderItems = orderItems;
        this.payment = payment;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Optional<Long> getPaymentId() {
        return Optional.ofNullable(payment).map(Payment::getId);
    }

    public Optional<Payment> getPayment() {
        return Optional.ofNullable(payment);
    }

    public boolean hasApprovedPayment() {
        return payment != null && PaymentStatus.APPROVED.equals(payment.getStatus());
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

    public boolean isPendingPaymentStatus() {
        return status.is(OrderStatus.PENDING_PAYMENT);
    }

    public boolean isFinished() {
        return status.is(OrderStatus.FINISHED);
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

    public void setPayment(Payment payment) {
        this.payment = payment;
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
