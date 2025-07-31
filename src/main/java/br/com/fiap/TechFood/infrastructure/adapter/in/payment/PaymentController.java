package br.com.fiap.TechFood.infrastructure.adapter.in.payment;

import br.com.fiap.TechFood.application.port.payment.in.GetPaymentStatusPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentController {

    private final GetPaymentStatusPort getPaymentStatusUseCase;

    public PaymentController(GetPaymentStatusPort getPaymentStatusUseCase) {
        this.getPaymentStatusUseCase = getPaymentStatusUseCase;
    }

    @GetMapping("/api/payment/{paymentId}/status")
    public ResponseEntity<PaymentStatusView> getPaymentStatus(@PathVariable Long paymentId) {
        var status = getPaymentStatusUseCase.getPaymentStatus(paymentId);
        return ResponseEntity.ok(new PaymentStatusView(paymentId, status));
    }

//    @PostMapping("/api/payment/confirm")
//    public ResponseEntity<Void> confirmPayment(@RequestBody Object storeOrderId) {
//        return ResponseEntity.noContent().build();
//    }
}
