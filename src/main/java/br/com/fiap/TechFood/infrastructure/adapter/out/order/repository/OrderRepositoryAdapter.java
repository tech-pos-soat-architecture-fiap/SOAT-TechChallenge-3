package br.com.fiap.TechFood.infrastructure.adapter.out.order.repository;

import br.com.fiap.TechFood.application.domain.order.Order;
import br.com.fiap.TechFood.application.port.PagePort;
import br.com.fiap.TechFood.application.port.order.out.OrderRepositoryPort;
import br.com.fiap.TechFood.infrastructure.adapter.out.PageDTO;
import br.com.fiap.TechFood.infrastructure.adapter.out.order.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class OrderRepositoryAdapter implements OrderRepositoryPort {

    private final OrderEntityRepository orderEntityRepository;

    public OrderRepositoryAdapter(OrderEntityRepository orderEntityRepository) {
        this.orderEntityRepository = orderEntityRepository;
    }

    @Override
    public PagePort<Order> findAllActiveSorted(int page, int size) {
        Page<Order> orders = orderEntityRepository.findAllActiveSorted(PageRequest.of(page, size)).map(OrderEntity::toOrder);
        return new PageDTO<>(orders);
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderEntityRepository.findById(id).map(OrderEntity::toOrder);
    }

    @Override
    public Order save(Order order) {
        return orderEntityRepository.save(new OrderEntity(order)).toOrder();
    }
}
