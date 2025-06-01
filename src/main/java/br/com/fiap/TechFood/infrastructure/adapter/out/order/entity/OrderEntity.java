package br.com.fiap.TechFood.infrastructure.adapter.out.order.entity;

import br.com.fiap.TechFood.application.core.domain.order.Order;
import br.com.fiap.TechFood.application.core.domain.order.OrderStatus;
import br.com.fiap.TechFood.infrastructure.adapter.out.user.entity.UserEntity;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
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
    private OrderStatus status = OrderStatus.RECEIVED;

    @NotNull
    private LocalDateTime createdAt = LocalDateTime.now();

    @NotNull
    @ElementCollection
    @CollectionTable(name = "order_items", joinColumns = @JoinColumn(name = "order_id"))
    private Set<OrderItemEntity> items = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @Nullable
    private UserEntity user;

    @Deprecated
    public OrderEntity() {
    }

    public OrderEntity(Order order) {
        this.id = order.getId();
        this.total = order.getTotal();
        this.status = order.getStatus();
        this.createdAt = order.getCreatedAt();
        this.items = order.getOrderItems().stream().map(OrderItemEntity::new).collect(Collectors.toSet());
        this.user = order.getUser().isPresent() ? new UserEntity(order.getUser().get()) : null;
    }

    public OrderEntity(BigDecimal total, OrderStatus status, LocalDateTime createdAt, Set<OrderItemEntity> items) {
        this.total = total;
        this.status = status;
        this.createdAt = createdAt;
        this.items = items;
    }

    public Order getOrder() {
        return new Order(
                this.id,
                this.user != null ? Optional.of(this.user.getUser()) : Optional.empty(),
                this.total,
                this.status,
                this.items.stream()
                        .map(OrderItemEntity::toOrderItem)
                        .collect(Collectors.toSet()));
    }
}
