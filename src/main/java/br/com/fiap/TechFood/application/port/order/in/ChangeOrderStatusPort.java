package br.com.fiap.TechFood.application.port.order.in;

import br.com.fiap.TechFood.application.domain.order.Order;
import br.com.fiap.TechFood.application.domain.payment.Payment;

public interface ChangeOrderStatusPort {
    Order changeStatus(Long orderId);
    void changeStatus(Payment payment);
}
