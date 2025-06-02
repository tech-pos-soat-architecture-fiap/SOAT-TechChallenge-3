package br.com.fiap.TechFood.infrastructure.adapter.in.order;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public record OrderForm(Set<OrderItemForm> orderItemsForm, Optional<Long> userId) {
    public Set<OrderItemForm> orderItems() {
        return orderItemsForm() == null ? null : new HashSet<>(orderItemsForm());
    }
}
