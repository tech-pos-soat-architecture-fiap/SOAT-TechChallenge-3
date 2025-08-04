package br.com.fiap.TechFood.application.port.payment.in;

import br.com.fiap.TechFood.application.domain.payment.PaymentStatus;

public interface PaymentWebhookProcessorPort {
    void processPaymentWebhook(Long paymentId, PaymentStatus paymentStatus);
}
