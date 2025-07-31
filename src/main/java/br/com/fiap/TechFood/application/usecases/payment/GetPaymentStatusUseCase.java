package br.com.fiap.TechFood.application.usecases.payment;

import br.com.fiap.TechFood.application.domain.payment.Payment;
import br.com.fiap.TechFood.application.domain.payment.PaymentStatus;
import br.com.fiap.TechFood.application.port.payment.in.GetPaymentStatusPort;
import br.com.fiap.TechFood.application.port.payment.out.PaymentRepositoryPort;
import br.com.fiap.TechFood.application.shared.exception.NotFoundException;

public class GetPaymentStatusUseCase implements GetPaymentStatusPort {

    private final PaymentRepositoryPort paymentRepository;

    public GetPaymentStatusUseCase(PaymentRepositoryPort paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public PaymentStatus getPaymentStatus(Long paymentId) {
        if (paymentId == null) {
            throw new IllegalArgumentException("Payment ID cannot be null");
        }
        return paymentRepository.findById(paymentId)
                .map(Payment::getStatus)
                .orElseThrow(() -> new NotFoundException("Payment not found for ID: " + paymentId));
    }
}
