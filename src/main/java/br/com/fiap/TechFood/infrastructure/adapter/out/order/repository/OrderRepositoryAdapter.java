package br.com.fiap.TechFood.infrastructure.adapter.out.order.repository;

import br.com.fiap.TechFood.application.core.domain.order.Order;
import br.com.fiap.TechFood.application.port.PagePort;
import br.com.fiap.TechFood.application.port.order.OrderRepositoryPort;
import br.com.fiap.TechFood.infrastructure.adapter.out.PageDTO;
import br.com.fiap.TechFood.infrastructure.adapter.out.order.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
public class OrderRepositoryAdapter implements OrderRepositoryPort {

    private final OrderEntityRepository orderEntityRepository;

    public OrderRepositoryAdapter(OrderEntityRepository orderEntityRepository) {
        this.orderEntityRepository = orderEntityRepository;
    }

    @Override
    public PagePort<Order> findAll(int page, int size) {
        Page<Order> orders = orderEntityRepository.findAll(PageRequest.of(page, size)).map(OrderEntity::getOrder);
        return new PageDTO<>(orders);
    }
}
