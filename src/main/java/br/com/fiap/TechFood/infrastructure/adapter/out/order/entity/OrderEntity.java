package br.com.fiap.TechFood.infrastructure.adapter.out.order.entity;

import br.com.fiap.TechFood.application.core.domain.order.Order;
import br.com.fiap.TechFood.application.core.domain.order.OrderStatus;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@Entity(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private BigDecimal total = BigDecimal.ZERO;

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
        this.total = order.getTotal();
        this.status = order.getStatus();
        this.createdAt = order.getCreatedAt();
        this.items = order.getOrderItems().stream().map(OrderItemEntity::new).collect(Collectors.toSet());
        this.userId = order.getUserId();
    }

    public Order getOrder() {
        return new Order(
                this.id,
                this.userId,
                this.total,
                this.status,
                this.items.stream()
                        .map(OrderItemEntity::toOrderItem)
                        .collect(Collectors.toSet()));
    }
}
