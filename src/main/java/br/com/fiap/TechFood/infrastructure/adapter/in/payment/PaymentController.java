package br.com.fiap.TechFood.infrastructure.adapter.in.payment;

import br.com.fiap.TechFood.application.port.payment.in.GetPaymentStatusPort;
import br.com.fiap.TechFood.application.port.payment.in.PaymentWebhookProcessorPort;
import br.com.fiap.TechFood.infrastructure.adapter.out.payment.PaymentStatusView;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentController {

    private final GetPaymentStatusPort getPaymentStatusPort;
    private final PaymentWebhookProcessorPort paymentWebhookProcessorPort;

    public PaymentController(GetPaymentStatusPort getPaymentStatusPort, PaymentWebhookProcessorPort paymentWebhookProcessorPort) {
        this.getPaymentStatusPort = getPaymentStatusPort;
        this.paymentWebhookProcessorPort = paymentWebhookProcessorPort;
    }

    @GetMapping("/payments/{paymentId}/status")
    public ResponseEntity<PaymentStatusView> getPaymentStatus(@PathVariable Long paymentId) {
        var status = getPaymentStatusPort.getPaymentStatus(paymentId);
        return ResponseEntity.ok(new PaymentStatusView(paymentId, status));
    }

    @PostMapping("/payments/confirm")
    public ResponseEntity<Void> confirmPayment(@Valid @RequestBody PaymentRequestWebhook requestWebhook) {
        paymentWebhookProcessorPort.processPaymentWebhook(requestWebhook.paymentId(),  requestWebhook.status());
        return ResponseEntity.ok().build();
    }
}
