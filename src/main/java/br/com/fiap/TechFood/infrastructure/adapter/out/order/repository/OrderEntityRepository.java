package br.com.fiap.TechFood.infrastructure.adapter.out.order.repository;

import br.com.fiap.TechFood.infrastructure.adapter.out.order.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderEntityRepository extends JpaRepository<OrderEntity, Long> {
}
