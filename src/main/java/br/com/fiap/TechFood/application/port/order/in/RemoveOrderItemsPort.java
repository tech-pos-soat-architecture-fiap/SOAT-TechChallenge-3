package br.com.fiap.TechFood.application.port.order.in;

import br.com.fiap.TechFood.application.domain.order.Order;
import br.com.fiap.TechFood.application.port.order.OrderItemPort;

import java.util.List;

public interface RemoveOrderItemsPort {

    Order removeItems(List<? extends OrderItemPort> orderItems, Long orderId);
}
