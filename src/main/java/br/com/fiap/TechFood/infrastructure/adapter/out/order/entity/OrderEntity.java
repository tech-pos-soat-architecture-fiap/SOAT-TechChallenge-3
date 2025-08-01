package br.com.fiap.TechFood.infrastructure.adapter.out.order.entity;

import br.com.fiap.TechFood.application.domain.Order;
import br.com.fiap.TechFood.application.domain.OrderStatus;
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

    @Deprecated
    public OrderEntity() {
    }

    public OrderEntity(Order order) {
        this.id = order.getId();
        this.status = order.getStatus();
        this.createdAt = order.getCreatedAt();
        this.items = order.getOrderItems().stream().map(OrderItemEntity::new).collect(Collectors.toSet());
        this.userId = order.getUserId();
    }

    public Order toOrder() {
        return new Order(
                this.id,
                this.userId,
                this.status,
                this.items.stream()
                        .map(OrderItemEntity::toOrderItem)
                        .collect(Collectors.toSet()), createdAt);
    }
}
