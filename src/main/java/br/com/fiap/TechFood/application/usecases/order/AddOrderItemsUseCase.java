package br.com.fiap.TechFood.application.usecases.order;

import br.com.fiap.TechFood.application.port.order.OrderItemPort;

import java.util.List;

public interface AddOrderItemsUseCase {
    Order addItems(List<? extends OrderItemPort> orderItems, Long orderId);
}
