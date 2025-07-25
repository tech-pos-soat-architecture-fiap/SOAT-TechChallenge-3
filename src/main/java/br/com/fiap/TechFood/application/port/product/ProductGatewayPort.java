package br.com.fiap.TechFood.application.port.product;

import br.com.fiap.TechFood.application.usecases.product.domain.Product;
import br.com.fiap.TechFood.application.usecases.product.domain.ProductCategory;
import br.com.fiap.TechFood.application.port.PagePort;

import java.util.List;
import java.util.Optional;

public interface ProductGatewayPort {

    Optional<Product> findById(Long id);

    Product save(Product product);

    Product update(Long id, Product product);

    PagePort<Product> findAll(int page, int size);

    PagePort<Product> findAllByCategory(ProductCategory category, int page, int size);

    void remove(Product product);

    List<Product> findAllByIdIn(List<Long> ids);

    Optional<Product> findByName(String name);
}
