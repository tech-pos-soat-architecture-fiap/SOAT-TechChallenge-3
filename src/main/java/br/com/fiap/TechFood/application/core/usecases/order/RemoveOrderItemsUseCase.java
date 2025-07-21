package br.com.fiap.TechFood.application.core.usecases.order;

import br.com.fiap.TechFood.application.core.domain.order.Order;
import br.com.fiap.TechFood.application.port.order.OrderItemPort;

import java.util.List;

public interface RemoveOrderItemsUseCase {

    Order removeItems(List<? extends OrderItemPort> orderItems, Long orderId);
}
