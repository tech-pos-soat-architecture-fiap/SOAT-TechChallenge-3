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

    private final RestTemplate restTemplate;

    public MockPaymentGatewayProcessor(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    //Como esse gerador de QR Code é um mock, daqui já se chama o webhook com o pagamento aprovado.
    @Override
    public PaymentQRCodeView generateQRCode(Order order) {
        sendWebhook(order);
        return new MockPaymentQRCodeView(order.getPaymentId().toString(), generateQrCode(order));
    }

    private String generateQrCode(Order order) {
        return """
                00020101021243650016COM.MERCADOLIBRE02013063638%s35204000053039865802BR5925IZABEL AAAA DE MELO6007BARUERI62070503***63040B6D%s
                """.formatted(UUID.randomUUID().toString(), order.getId());
    }

    private void sendWebhook(Order order) {
        String webhookUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/payment/confirm")
                .toUriString();

        PaymentRequestWebhook request = new PaymentRequestWebhook(order.getPaymentId(), PaymentStatus.APPROVED);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<PaymentRequestWebhook> entity = new HttpEntity<>(request, headers);

        restTemplate.postForEntity(webhookUrl, entity, Void.class);
    }

}
