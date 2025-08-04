package br.com.fiap.TechFood.application.usecases.payment;

import br.com.fiap.TechFood.application.domain.payment.Payment;
import br.com.fiap.TechFood.application.domain.payment.PaymentStatus;
import br.com.fiap.TechFood.application.port.order.in.ChangeOrderStatusPort;
import br.com.fiap.TechFood.application.port.payment.in.PaymentWebhookProcessorPort;
import br.com.fiap.TechFood.application.port.payment.out.PaymentRepositoryPort;

public class PaymentWebhookProcessorUseCase implements PaymentWebhookProcessorPort {

    private final ChangeOrderStatusPort changeOrderStatusPort;
    private final PaymentRepositoryPort paymentRepositoryPort;

    public PaymentWebhookProcessorUseCase(ChangeOrderStatusPort changeOrderStatusPort, PaymentRepositoryPort paymentRepositoryPort) {
        this.changeOrderStatusPort = changeOrderStatusPort;
        this.paymentRepositoryPort = paymentRepositoryPort;
    }

    @Override
    public void processPaymentWebhook(Long paymentId, PaymentStatus paymentStatus) {
        Payment payment = paymentRepositoryPort.findById(paymentId).orElseThrow(() -> new IllegalArgumentException("Payment not found for ID: " + paymentId));
        payment.updateStatus(paymentStatus);
        paymentRepositoryPort.save(payment);

        if (PaymentStatus.APPROVED.equals(paymentStatus)) {
            changeOrderStatusPort.changeStatus(payment);
        }
    }
}
