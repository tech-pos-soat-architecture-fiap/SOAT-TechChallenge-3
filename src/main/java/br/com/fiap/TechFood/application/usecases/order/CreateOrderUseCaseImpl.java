package br.com.fiap.TechFood.application.usecases.order;

import br.com.fiap.TechFood.application.port.order.OrderRepositoryPort;
import br.com.fiap.TechFood.application.port.user.UserRepositoryPort;
import br.com.fiap.TechFood.application.shared.exception.NotFoundException;

public class CreateOrderUseCaseImpl implements CreateOrderUseCase{

    private final UserRepositoryPort userRepositoryPort;
    private final OrderRepositoryPort orderRepositoryPort;

    public CreateOrderUseCaseImpl(UserRepositoryPort userRepositoryPort, OrderRepositoryPort orderRepositoryPort) {
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
