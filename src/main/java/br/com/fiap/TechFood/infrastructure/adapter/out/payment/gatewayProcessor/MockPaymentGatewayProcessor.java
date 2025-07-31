package br.com.fiap.TechFood.infrastructure.adapter.out.payment.gatewayProcessor;

import br.com.fiap.TechFood.application.domain.order.Order;
import br.com.fiap.TechFood.application.port.payment.out.PaymentGatewayProcessor;
import br.com.fiap.TechFood.application.port.payment.PaymentQRCodeView;

import java.util.UUID;

public class MockPaymentGatewayProcessor implements PaymentGatewayProcessor {

    @Override
    public PaymentQRCodeView generateQRCode(Order order) {
        return new MockPaymentQRCodeView(order.getPaymentId().toString(), generateQrCode(order));
    }

    private String generateQrCode(Order order) {
        return """
                00020101021243650016COM.MERCADOLIBRE02013063638%s35204000053039865802BR5925IZABEL AAAA DE MELO6007BARUERI62070503***63040B6D%s
                """.formatted(UUID.randomUUID().toString(), order.getId());
    }

}
