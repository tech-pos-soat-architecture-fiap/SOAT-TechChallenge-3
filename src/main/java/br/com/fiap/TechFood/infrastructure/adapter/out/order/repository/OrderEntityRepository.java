package br.com.fiap.TechFood.infrastructure.adapter.out.order.repository;

import br.com.fiap.TechFood.infrastructure.adapter.out.order.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderEntityRepository extends JpaRepository<OrderEntity, Long> {
    @Query("""
    SELECT o FROM orders o
    WHERE o.status IN ('COMPLETED', 'IN_PROGRESS', 'RECEIVED')
    ORDER BY
      CASE o.status
        WHEN 'COMPLETED' THEN 1
        WHEN 'IN_PROGRESS' THEN 2
        WHEN 'RECEIVED' THEN 3
        ELSE 4
      END,
      o.createdAt ASC
""")
    Page<OrderEntity> findAllActiveSorted(PageRequest pageRequest);
}
