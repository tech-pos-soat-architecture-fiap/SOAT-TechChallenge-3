package br.com.fiap.TechFood.application.usecases.order;

import br.com.fiap.TechFood.application.domain.Order;
import br.com.fiap.TechFood.application.port.PagePort;
import br.com.fiap.TechFood.application.port.order.out.OrderRepositoryPort;
import br.com.fiap.TechFood.application.port.order.in.FindAllActiveOrderPort;

public class FindAllActiveOrderUseCase implements FindAllActiveOrderPort {

    private final OrderRepositoryPort orderRepository;

    public FindAllActiveOrderUseCase(OrderRepositoryPort orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public PagePort<Order> findAllActiveSorted(int page, int size) {
        return orderRepository.findAllActiveSorted(page, size);
    }
}
