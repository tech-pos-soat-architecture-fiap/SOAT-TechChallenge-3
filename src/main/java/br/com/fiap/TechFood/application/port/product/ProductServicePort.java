package br.com.fiap.TechFood.application.port.product;

import br.com.fiap.TechFood.application.usecases.product.domain.Product;
import br.com.fiap.TechFood.application.usecases.product.domain.ProductCategory;
import br.com.fiap.TechFood.application.port.PagePort;

import java.util.List;
import java.util.Optional;

public interface ProductServicePort {

    Optional<Product> findById(Long id);

    Product create(Product product);

    Product update(Long id, Product product);

    void remove(Long id);

    PagePort<Product> findAll(int page, int size);

    PagePort<Product> findAllByCategory(ProductCategory productCategory, int page, int size);

    List<Product> getProductsByIds(List<Long> list);

    Optional<Product> findByName(String name);
}
