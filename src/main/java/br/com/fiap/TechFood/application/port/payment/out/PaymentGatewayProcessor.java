package br.com.fiap.TechFood.application.port.payment.out;

import br.com.fiap.TechFood.application.domain.order.Order;
import br.com.fiap.TechFood.application.port.payment.PaymentQRCodeView;

public interface PaymentGatewayProcessor {
    PaymentQRCodeView generateQRCode(Order order);
}
