package br.com.fiap.TechFood.infrastructure.adapter.out.payment.gatewayProcessor;

import br.com.fiap.TechFood.application.domain.order.Order;
import br.com.fiap.TechFood.application.domain.payment.PaymentStatus;
import br.com.fiap.TechFood.application.port.payment.out.PaymentGatewayProcessor;
import br.com.fiap.TechFood.application.port.payment.PaymentQRCodeView;
import br.com.fiap.TechFood.infrastructure.adapter.in.payment.PaymentRequestWebhook;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.UUID;

public class MockPaymentGatewayProcessor implements PaymentGatewayProcessor {

    @Override
    public PaymentQRCodeView generateQRCode(Order order) {
        Long paymentId = order.getPaymentId().orElseThrow(() -> new IllegalArgumentException("Order must have a payment ID"));

        return new MockPaymentQRCodeView(UUID.randomUUID().toString(), createQRCodeFake(order), paymentId, order.getId());
    }

    private String createQRCodeFake(Order order) {
        return """
                00020101021243650016COM.MERCADOLIBRE02013063638%s35204000053039865802BR5925IZABEL AAAA DE MELO6007BARUERI62070503***63040B6D%s
                """.formatted(UUID.randomUUID().toString(), order.getId());
    }


}
