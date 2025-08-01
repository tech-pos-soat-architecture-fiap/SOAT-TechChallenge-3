package br.com.fiap.TechFood.infrastructure.adapter.in.payment;

import br.com.fiap.TechFood.application.port.payment.in.GetPaymentStatusPort;
import br.com.fiap.TechFood.application.port.payment.in.PaymentWebhookProcessorPort;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentController {

    private final GetPaymentStatusPort getPaymentStatusUseCase;
    private final PaymentWebhookProcessorPort paymentWebhookProcessorPort;

    public PaymentController(GetPaymentStatusPort getPaymentStatusUseCase, PaymentWebhookProcessorPort paymentWebhookProcessorPort) {
        this.getPaymentStatusUseCase = getPaymentStatusUseCase;
        this.paymentWebhookProcessorPort = paymentWebhookProcessorPort;
    }

    @GetMapping("/api/payment/{paymentId}/status")
    public ResponseEntity<PaymentStatusView> getPaymentStatus(@PathVariable Long paymentId) {
        var status = getPaymentStatusUseCase.getPaymentStatus(paymentId);
        return ResponseEntity.ok(new PaymentStatusView(paymentId, status));
    }

    @PostMapping("/api/payment/confirm")
    public ResponseEntity<Void> confirmPayment(@Valid @RequestBody PaymentRequestWebhook requestWebhook) {
        System.out.println("Received payment confirmation webhook: " + requestWebhook);
        paymentWebhookProcessorPort.processPaymentWebhook(requestWebhook.paymentId(),  requestWebhook.status());
        return ResponseEntity.ok().build();
    }
}
