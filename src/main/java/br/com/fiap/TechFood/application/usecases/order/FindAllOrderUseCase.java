package br.com.fiap.TechFood.application.usecases.order;

import br.com.fiap.TechFood.application.port.PagePort;

public interface FindAllOrderUseCase {
    PagePort<Order> findAll(int page, int size);
}
