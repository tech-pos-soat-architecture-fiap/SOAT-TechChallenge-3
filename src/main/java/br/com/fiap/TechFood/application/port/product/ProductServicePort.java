package br.com.fiap.TechFood.application.port.product;

import br.com.fiap.TechFood.application.core.domain.product.Product;
import br.com.fiap.TechFood.application.port.PagePort;
import br.com.fiap.TechFood.infrastructure.adapter.in.product.ProductForm;

import java.util.Optional;

public interface ProductServicePort {

    Optional<Product> findById(Long id);

    Product create(Product product);

    Product update(Product product, ProductForm productForm);

    void remove(Product product);

    PagePort<Product> findAll(int page, int size);

    PagePort<Product> findAllByCategory(String categoryName, int page, int size);
}
