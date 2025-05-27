package br.com.fiap.TechFood.infrastructure.adapter.out.product.repository;

import br.com.fiap.TechFood.application.core.domain.product.ProductCategory;
import br.com.fiap.TechFood.infrastructure.adapter.out.product.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductEntityRepository extends JpaRepository<ProductEntity, Long> {

    Page<ProductEntity> findAllByCategory(ProductCategory category, Pageable pageable);
}
