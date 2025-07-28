package br.com.fiap.TechFood.application.usecases.order;

import br.com.fiap.TechFood.application.domain.order.Order;
import br.com.fiap.TechFood.application.port.PagePort;
import br.com.fiap.TechFood.application.port.order.out.OrderRepositoryPort;
import br.com.fiap.TechFood.application.port.order.in.FindAllOrderPort;

public class FindAllOrderUseCase implements FindAllOrderPort {

    private final OrderRepositoryPort orderRepository;

    public FindAllOrderUseCase(OrderRepositoryPort orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public PagePort<Order> findAll(int page, int size) {
        return orderRepository.findAll(page, size);
    }
}
