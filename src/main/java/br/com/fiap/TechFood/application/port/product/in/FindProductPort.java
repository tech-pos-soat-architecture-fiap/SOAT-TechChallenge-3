package br.com.fiap.TechFood.application.port.product.in;

import br.com.fiap.TechFood.infrastructure.adapter.in.product.ProductView;

import java.util.Optional;

public interface FindProductPort {

    Optional<ProductView> findById(Long id);
}
