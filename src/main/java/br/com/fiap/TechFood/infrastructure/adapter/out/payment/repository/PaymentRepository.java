package br.com.fiap.TechFood.infrastructure.adapter.out.payment.repository;

import br.com.fiap.TechFood.infrastructure.adapter.out.payment.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {
}
