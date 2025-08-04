package br.com.fiap.TechFood.application.usecases.order;

import br.com.fiap.TechFood.application.domain.order.Order;
import br.com.fiap.TechFood.application.domain.order.OrderStatus;
import br.com.fiap.TechFood.application.domain.payment.Payment;
import br.com.fiap.TechFood.application.port.order.in.ChangeOrderStatusPort;
import br.com.fiap.TechFood.application.port.order.out.OrderRepositoryPort;
import br.com.fiap.TechFood.application.shared.exception.NotFoundException;

public class ChangeOrderStatusUseCase implements ChangeOrderStatusPort {

    private final OrderRepositoryPort orderRepositoryPort;

    public ChangeOrderStatusUseCase(OrderRepositoryPort orderRepositoryPort) {
        this.orderRepositoryPort = orderRepositoryPort;
    }

    @Override
    public Order changeStatus(Long orderId) {
        Order order = orderRepositoryPort.findById(orderId).orElseThrow(NotFoundException::new);
        return changeStatus(order);
    }

    @Override
    public void changeStatus(Payment payment) {
        Order order = orderRepositoryPort.findByPayment(payment).orElseThrow(NotFoundException::new);
        changeStatus(order.getId());
    }

    private Order changeStatus(Order order) {
        if (order.isFinished()) {
            throw new IllegalStateException("Order already finished.");
        }

        if (order.isPendingPaymentStatus() && !order.hasApprovedPayment()) {
            throw new IllegalStateException("Order cannot be advanced without an approved payment.");
        }

        OrderStatus nextStatus = order.getStatus().next();
        order.setStatus(nextStatus);
        return orderRepositoryPort.save(order);
    }
}
