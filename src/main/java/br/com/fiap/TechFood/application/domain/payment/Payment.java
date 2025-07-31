package br.com.fiap.TechFood.application.domain.payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Payment {
    private Long id;
    private LocalDateTime createdAt = LocalDateTime.now();
    private PaymentStatus status = PaymentStatus.PENDING;
    private BigDecimal amount;

    public Payment(Long id, LocalDateTime createdAt, PaymentStatus status, BigDecimal amount) {
        this.id = id;
        this.createdAt = createdAt;
        this.status = status;
        this.amount = amount;
    }

    public Payment(BigDecimal amount) {
        if(amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be a positive value");
        }
        this.amount = amount;
    }

    public static Payment create(BigDecimal amount) {
        return new Payment(amount);
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

}
