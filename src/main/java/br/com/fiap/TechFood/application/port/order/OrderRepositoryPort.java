package br.com.fiap.TechFood.application.port.order;

import br.com.fiap.TechFood.application.core.domain.order.Order;
import br.com.fiap.TechFood.application.port.PagePort;

public interface OrderRepositoryPort {
    PagePort<Order> findAll(int page, int size);
}
