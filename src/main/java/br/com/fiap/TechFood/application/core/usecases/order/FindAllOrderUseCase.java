package br.com.fiap.TechFood.application.core.usecases.order;

import br.com.fiap.TechFood.application.core.domain.order.Order;
import br.com.fiap.TechFood.application.port.PagePort;

public interface FindAllOrderUseCase {
    PagePort<Order> findAll(int page, int size);
}
