package br.com.fiap.TechFood.infrastructure.adapter.out.payment.repository;

import br.com.fiap.TechFood.application.domain.payment.Payment;
import br.com.fiap.TechFood.application.port.payment.out.PaymentRepositoryPort;
import br.com.fiap.TechFood.infrastructure.adapter.out.payment.entity.PaymentEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PaymentRepositoryAdapter implements PaymentRepositoryPort {

    private final PaymentRepository paymentRepository;

    public PaymentRepositoryAdapter(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment save(Payment payment) {
        PaymentEntity paymentEntity = paymentRepository.save(new PaymentEntity(payment));
        return paymentEntity.toPayment();
    }

    @Override
    public Optional<Payment> findById(Long paymentId) {
        return paymentRepository.findById(paymentId).map(PaymentEntity::toPayment);
    }

}
