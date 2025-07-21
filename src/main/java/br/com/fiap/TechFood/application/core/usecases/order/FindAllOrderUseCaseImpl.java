package br.com.fiap.TechFood.application.core.usecases.order;

import br.com.fiap.TechFood.application.core.domain.order.Order;
import br.com.fiap.TechFood.application.port.PagePort;
import br.com.fiap.TechFood.application.port.order.OrderRepositoryPort;

public class FindAllOrderUseCaseImpl implements FindAllOrderUseCase {

    private final OrderRepositoryPort orderRepository;

    public FindAllOrderUseCaseImpl(OrderRepositoryPort orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public PagePort<Order> findAll(int page, int size) {
        return orderRepository.findAll(page, size);
    }
}
