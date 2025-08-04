package br.com.fiap.TechFood.infrastructure.adapter.out.payment;

import br.com.fiap.TechFood.application.domain.payment.PaymentStatus;

public record PaymentStatusView(
        Long id,
        PaymentStatus status
) {
}
