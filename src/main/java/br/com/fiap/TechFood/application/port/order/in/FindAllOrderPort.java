package br.com.fiap.TechFood.application.port.order.in;

import br.com.fiap.TechFood.application.domain.order.Order;
import br.com.fiap.TechFood.application.port.PagePort;

public interface FindAllOrderPort {
    PagePort<Order> findAll(int page, int size);
}
