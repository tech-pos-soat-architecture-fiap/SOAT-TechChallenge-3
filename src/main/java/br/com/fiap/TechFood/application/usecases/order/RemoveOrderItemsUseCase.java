package br.com.fiap.TechFood.application.usecases.order;

import br.com.fiap.TechFood.application.port.order.OrderItemPort;

import java.util.List;

public interface RemoveOrderItemsUseCase {

    Order removeItems(List<? extends OrderItemPort> orderItems, Long orderId);
}
