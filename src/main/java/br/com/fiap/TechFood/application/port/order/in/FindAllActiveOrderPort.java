package br.com.fiap.TechFood.application.port.order.in;

import br.com.fiap.TechFood.application.domain.order.Order;
import br.com.fiap.TechFood.application.port.PagePort;

public interface FindAllActiveOrderPort {
    PagePort<Order> findAllActiveSorted(int page, int size);
}
