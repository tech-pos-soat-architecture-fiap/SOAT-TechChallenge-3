package br.com.fiap.TechFood.application.port.order.in;

import br.com.fiap.TechFood.application.port.payment.PaymentQRCodeView;

public interface CreateOrderPaymentPort {
    PaymentQRCodeView createPayment(Long orderId);
}
