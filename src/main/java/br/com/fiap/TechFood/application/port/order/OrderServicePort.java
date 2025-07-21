package br.com.fiap.TechFood.application.port.order;

import br.com.fiap.TechFood.application.usecases.order.Order;
import br.com.fiap.TechFood.application.port.PagePort;

import java.util.List;

public interface OrderServicePort {
    PagePort<Order> findAll(int page, int size);

    Order findById(Long id);

    Order save(Order order);

    Order changeStatus(Long orderId);

    Order create(Long userId);

    Order addItems(List<? extends OrderItemPort> orderItems, Long orderId);

    Order removeItems(List<? extends OrderItemPort> orderItems, Long orderId);
}
