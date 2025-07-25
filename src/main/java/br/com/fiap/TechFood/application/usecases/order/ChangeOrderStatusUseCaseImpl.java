package br.com.fiap.TechFood.application.usecases.order;

import br.com.fiap.TechFood.application.port.order.OrderRepositoryPort;
import br.com.fiap.TechFood.application.shared.exception.NotFoundException;

public class ChangeOrderStatusUseCaseImpl implements ChangeOrderStatusUseCase{

    private final OrderRepositoryPort orderRepositoryPort;

    public ChangeOrderStatusUseCaseImpl(OrderRepositoryPort orderRepositoryPort) {
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
