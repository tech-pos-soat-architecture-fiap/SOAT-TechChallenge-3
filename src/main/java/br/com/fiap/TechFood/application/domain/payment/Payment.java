package br.com.fiap.TechFood.application.domain.payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Payment {
    private Long id;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;
    private PaymentStatus status;
    private String transactionId;
    private BigDecimal amount;

    public Payment(Long id, LocalDateTime createdAt, PaymentStatus status, String transactionId, BigDecimal amount) {
        this.id = id;
        this.createdAt = createdAt;
        this.status = status;
        this.transactionId = transactionId;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
