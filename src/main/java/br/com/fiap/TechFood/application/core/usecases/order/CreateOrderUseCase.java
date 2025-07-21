package br.com.fiap.TechFood.application.core.usecases.order;

import br.com.fiap.TechFood.application.core.domain.order.Order;

public interface CreateOrderUseCase {
    Order create(Long userId);
}
