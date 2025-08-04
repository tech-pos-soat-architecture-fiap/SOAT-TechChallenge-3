package br.com.fiap.TechFood.infrastructure.adapter.out.order.entity;

import br.com.fiap.TechFood.application.domain.order.Order;
import br.com.fiap.TechFood.application.domain.order.OrderStatus;
import br.com.fiap.TechFood.application.domain.payment.Payment;
import br.com.fiap.TechFood.infrastructure.adapter.out.payment.entity.PaymentEntity;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@Entity(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.DRAFT;

    @NotNull
    private LocalDateTime createdAt = LocalDateTime.now();

    @NotNull
    @ElementCollection
    @CollectionTable(name = "order_items", joinColumns = @JoinColumn(name = "order_id"))
    private Set<OrderItemEntity> items = new HashSet<>();

    @Nullable
    private Long userId;

    @OneToOne
    private PaymentEntity payment;

    @Deprecated
    public OrderEntity() {
    }

    public OrderEntity(Order order) {
        this.id = order.getId();
        this.status = order.getStatus();
        this.createdAt = order.getCreatedAt();
        this.items = order.getOrderItems().stream().map(OrderItemEntity::new).collect(Collectors.toSet());
        this.userId = order.getUserId();
        this.payment = order.getPayment().map(PaymentEntity::new).orElse(null);
    }

    public Order toOrder() {
        Payment payment = this.payment != null ? this.payment.toPayment() : null;
        return new Order(
                this.id,
                this.userId,
                this.status,
                payment,
                this.items.stream()
                        .map(OrderItemEntity::toOrderItem)
                        .collect(Collectors.toSet()),
                this.createdAt
        );
    }
}
