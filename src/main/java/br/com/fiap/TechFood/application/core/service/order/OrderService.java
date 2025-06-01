package br.com.fiap.TechFood.application.core.service.order;

import br.com.fiap.TechFood.application.core.domain.order.Order;
import br.com.fiap.TechFood.application.core.domain.order.OrderStatus;
import br.com.fiap.TechFood.application.port.PagePort;
import br.com.fiap.TechFood.application.port.order.OrderRepositoryPort;
import br.com.fiap.TechFood.application.port.order.OrderServicePort;
import br.com.fiap.TechFood.infrastructure.adapter.in.order.OrderStatusView;

import java.util.Optional;

public class OrderService implements OrderServicePort {

    private final OrderRepositoryPort orderRepositoryPort;

    public OrderService(OrderRepositoryPort orderRepositoryPort) {
        this.orderRepositoryPort = orderRepositoryPort;
    }

    @Override
    public PagePort<Order> findAll(int page, int size) {
        return orderRepositoryPort.findAll(page, size);
    }

    @Override
    public OrderStatusView changeStatus(Long orderId) {
        Optional<Order> possibleOrder = this.findById(orderId);
        if (possibleOrder.isPresent() && possibleOrder.get().getStatus().isNotFinished()) {
            Order order = possibleOrder.get();
            OrderStatus nextStatus = order.getStatus().next();
            order.setStatus(nextStatus);
            save(order);
            return new OrderStatusView(orderId, order.getStatusName());
        }
        throw new IllegalStateException("Order not found or already finished.");
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepositoryPort.findById(id);
    }

    @Override
    public Order save(Order order) {
        return orderRepositoryPort.save(order);
    }
}
