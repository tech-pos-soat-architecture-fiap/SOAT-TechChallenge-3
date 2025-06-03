package br.com.fiap.TechFood.application.port.order;

import br.com.fiap.TechFood.application.core.domain.order.Order;
import br.com.fiap.TechFood.application.port.PagePort;
import br.com.fiap.TechFood.infrastructure.adapter.in.order.OrderItemForm;
import br.com.fiap.TechFood.infrastructure.adapter.in.order.OrderStatusView;

import java.util.List;

public interface OrderServicePort {
    PagePort<Order> findAll(int page, int size);

    Order findById(Long id);

    Order save(Order order);

    OrderStatusView changeStatus(Long orderId);

    Order create(Long userId);

    Order addItems(List<OrderItemForm> orderItemsForms, Long orderId);

    Order removeItems(List<OrderItemForm> orderItemsForms, Long orderId);
}
