package br.com.fiap.TechFood.application.port.product;

import br.com.fiap.TechFood.application.core.domain.product.Product;
import br.com.fiap.TechFood.application.port.PagePort;

import java.util.List;
import java.util.Optional;

public interface ProductServicePort {

    Optional<Product> findById(Long id);

    Product create(Product product);

    Product update(Long id, Product product);

    void remove(Long id);

    PagePort<Product> findAll(int page, int size);

    PagePort<Product> findAllByCategory(String categoryName, int page, int size);

    List<Product> getProductsByIds(List<Long> list);
}
