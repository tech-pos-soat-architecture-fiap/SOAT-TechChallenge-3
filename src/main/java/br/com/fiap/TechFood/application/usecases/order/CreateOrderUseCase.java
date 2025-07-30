package br.com.fiap.TechFood.application.usecases.order;

import br.com.fiap.TechFood.application.domain.order.Order;
import br.com.fiap.TechFood.application.port.order.out.OrderRepositoryPort;
import br.com.fiap.TechFood.application.port.order.in.CreateOrderPort;
import br.com.fiap.TechFood.application.port.user.UserRepositoryPort;
import br.com.fiap.TechFood.application.shared.exception.NotFoundException;

public class CreateOrderUseCase implements CreateOrderPort {

    private final UserRepositoryPort userRepositoryPort;
    private final OrderRepositoryPort orderRepositoryPort;

    public CreateOrderUseCase(UserRepositoryPort userRepositoryPort, OrderRepositoryPort orderRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
        this.orderRepositoryPort = orderRepositoryPort;
    }

    @Override
    public Order create(Long userId) {
        if (userId != null) {
            userRepositoryPort.findById(userId).orElseThrow(NotFoundException::new);
        }
        return orderRepositoryPort.save(Order.createDraft(userId));
    }
}
