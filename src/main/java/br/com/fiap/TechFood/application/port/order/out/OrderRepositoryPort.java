package br.com.fiap.TechFood.application.port.order.out;

import br.com.fiap.TechFood.application.domain.Order;
import br.com.fiap.TechFood.application.port.PagePort;

import java.util.Optional;

public interface OrderRepositoryPort {
    PagePort<Order> findAll(int page, int size);
    Optional<Order> findById(Long id);
    Order save(Order order);
}
