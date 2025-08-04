package br.com.fiap.TechFood.application.port.payment.out;

import br.com.fiap.TechFood.application.domain.payment.Payment;

import java.util.Optional;

public interface PaymentRepositoryPort {
    Payment save(Payment payment);
    Optional<Payment> findById(Long paymentId);
}
