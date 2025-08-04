package br.com.fiap.TechFood.infrastructure.adapter.in.payment;

import br.com.fiap.TechFood.application.domain.payment.PaymentStatus;
import jakarta.validation.constraints.NotNull;

public record PaymentRequestWebhook(
        @NotNull
        Long paymentId,
        @NotNull
        PaymentStatus status
) {
}
