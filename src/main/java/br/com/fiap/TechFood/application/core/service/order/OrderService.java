package br.com.fiap.TechFood.application.core.service.order;

import br.com.fiap.TechFood.application.core.domain.order.Order;
import br.com.fiap.TechFood.application.port.PagePort;
import br.com.fiap.TechFood.application.port.order.OrderRepositoryPort;
import br.com.fiap.TechFood.application.port.order.OrderServicePort;

public class OrderService implements OrderServicePort {

    private final OrderRepositoryPort orderRepositoryPort;

    public OrderService(OrderRepositoryPort orderRepositoryPort) {
        this.orderRepositoryPort = orderRepositoryPort;
    }

    @Override
    public PagePort<Order> findAll(int page, int size) {
        return orderRepositoryPort.findAll(page, size);
    }
}
