package br.com.fiap.TechFood.application.port.order.in;

import br.com.fiap.TechFood.application.domain.Order;

public interface ChangeOrderStatusPort {
    Order changeStatus(Long orderId);
}
