package br.com.fiap.TechFood.application.core.service.user;

import br.com.fiap.TechFood.application.core.domain.product.Product;
import br.com.fiap.TechFood.application.core.domain.product.ProductCategory;
import br.com.fiap.TechFood.application.port.PagePort;
import br.com.fiap.TechFood.application.port.product.ProductRepositoryPort;
import br.com.fiap.TechFood.application.port.product.ProductServicePort;

import java.util.Optional;

public class ProductService implements ProductServicePort {

    private final ProductRepositoryPort productRepositoryPort;

    public ProductService(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepositoryPort.findById(id);
    }

    @Override
    public Product create(Product product) {
        return productRepositoryPort.save(product);
    }

    @Override
    public void remove(Product product) {
        productRepositoryPort.remove(product);
    }

    @Override
    public PagePort<Product> findAll(int page, int size) {
        return productRepositoryPort.findAll(page, size);
    }

    @Override
    public PagePort<Product> findAllByCategory(ProductCategory category, int page, int size) {
        return productRepositoryPort.findAllByCategory(category, page, size);
    }
}
