package br.com.fiap.TechFood.application.port.product;

import br.com.fiap.TechFood.application.core.domain.product.Product;
import br.com.fiap.TechFood.application.core.domain.product.ProductCategory;
import br.com.fiap.TechFood.application.port.PagePort;

import java.util.Optional;

public interface ProductServicePort {

    Optional<Product> findById(Long id);

    Product create(Product product);

    void remove(Product product);

    PagePort<Product> findAll(int page, int size);

    PagePort<Product> findAllByCategory(ProductCategory category, int page, int size);
}
