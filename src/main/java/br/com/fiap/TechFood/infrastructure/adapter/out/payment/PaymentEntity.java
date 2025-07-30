package br.com.fiap.TechFood.infrastructure.adapter.out.payment;

import br.com.fiap.TechFood.application.domain.payment.Payment;
import br.com.fiap.TechFood.application.domain.payment.PaymentStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static jakarta.persistence.EnumType.STRING;

@Entity()
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createdAt = LocalDateTime.now();

    @Enumerated(STRING)
    private PaymentStatus status = PaymentStatus.PENDING;

    private String transactionId;

    private BigDecimal amount;


    @Deprecated
    public PaymentEntity() {
    }

    public PaymentEntity(Payment payment) {
        this.id = payment.getId();
        this.createdAt = payment.getCreatedAt();
        this.status = payment.getStatus();
        this.transactionId = payment.getTransactionId();
        this.amount = payment.getAmount();
    }

    public Payment toDomain() {
        return new Payment(id, createdAt, status, transactionId, amount);
    }
}
