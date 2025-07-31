package br.com.fiap.TechFood.infrastructure.adapter.out.payment.entity;

import br.com.fiap.TechFood.application.domain.payment.Payment;
import br.com.fiap.TechFood.application.domain.payment.PaymentStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static jakarta.persistence.EnumType.STRING;

@Entity
@Table(name = "payments")
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDateTime createdAt = LocalDateTime.now();

    @Enumerated(STRING)
    private PaymentStatus status = PaymentStatus.PENDING;

    @Positive
    private BigDecimal amount;

    @Deprecated
    public PaymentEntity() {
    }

    public PaymentEntity(Payment payment) {
        this.id = payment.getId();
        this.createdAt = payment.getCreatedAt();
        this.status = payment.getStatus();
        this.amount = payment.getAmount();
    }

    public Payment toDomain() {
        return new Payment(id, createdAt, status, amount);
    }
}
