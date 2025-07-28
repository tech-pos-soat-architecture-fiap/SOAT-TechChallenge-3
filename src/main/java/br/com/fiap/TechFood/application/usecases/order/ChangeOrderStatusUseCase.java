package br.com.fiap.TechFood.application.usecases.order;

import br.com.fiap.TechFood.application.domain.order.Order;
import br.com.fiap.TechFood.application.domain.order.OrderStatus;
import br.com.fiap.TechFood.application.port.order.out.OrderRepositoryPort;
import br.com.fiap.TechFood.application.port.order.in.ChangeOrderStatusPort;
import br.com.fiap.TechFood.application.shared.exception.NotFoundException;

public class ChangeOrderStatusUseCase implements ChangeOrderStatusPort {

    private final OrderRepositoryPort orderRepositoryPort;

    public ChangeOrderStatusUseCase(OrderRepositoryPort orderRepositoryPort) {
        this.orderRepositoryPort = orderRepositoryPort;
    }

    @Override
    public Order changeStatus(Long orderId) {
        Order order = orderRepositoryPort.findById(orderId).orElseThrow(NotFoundException::new);
        if (order.isNotFinished()) {
            OrderStatus nextStatus = order.getStatus().next();
            order.setStatus(nextStatus);
            return orderRepositoryPort.save(order);
        }
        throw new IllegalStateException("Order not found or already finished.");
    }
}
