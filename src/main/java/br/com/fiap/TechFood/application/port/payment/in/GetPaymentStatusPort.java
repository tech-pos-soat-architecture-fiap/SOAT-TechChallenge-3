package br.com.fiap.TechFood.application.port.payment.in;

import br.com.fiap.TechFood.application.domain.payment.PaymentStatus;

public interface GetPaymentStatusPort {

    PaymentStatus getPaymentStatus(Long paymentId);
}
