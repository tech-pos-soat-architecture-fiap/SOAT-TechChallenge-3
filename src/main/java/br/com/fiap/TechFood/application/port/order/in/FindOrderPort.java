package br.com.fiap.TechFood.application.port.order.in;

import br.com.fiap.TechFood.application.domain.order.Order;

public interface FindOrderPort {
    Order findById(Long orderId);
}
