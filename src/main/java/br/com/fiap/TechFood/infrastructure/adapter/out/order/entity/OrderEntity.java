package br.com.fiap.TechFood.infrastructure.adapter.out.order.entity;

import br.com.fiap.TechFood.application.core.domain.order.OrderStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

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

    @Deprecated
    public OrderEntity() {
    }

    public OrderEntity(BigDecimal total, OrderStatus status, LocalDateTime createdAt, Set<OrderItemEntity> items) {
        this.id = id;
        this.total = total;
        this.status = status;
        this.createdAt = createdAt;
        this.items = items;
    }
}
