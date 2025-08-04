package br.com.fiap.TechFood.infrastructure.adapter.out.payment;

import br.com.fiap.TechFood.application.domain.payment.Payment;
import br.com.fiap.TechFood.application.domain.payment.PaymentStatus;

public record PaymentStatusView(Long paymentId, PaymentStatus status) {

    public static PaymentStatusView from(Payment payment) {
        return new PaymentStatusView(payment.getId(), payment.getStatus());
    }
}
