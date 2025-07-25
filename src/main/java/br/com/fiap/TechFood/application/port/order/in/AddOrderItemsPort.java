package br.com.fiap.TechFood.application.port.order.in;

import br.com.fiap.TechFood.application.domain.Order;
import br.com.fiap.TechFood.application.port.order.OrderItemPort;

import java.util.List;

public interface AddOrderItemsPort {
    Order addItems(List<? extends OrderItemPort> orderItems, Long orderId);
}
