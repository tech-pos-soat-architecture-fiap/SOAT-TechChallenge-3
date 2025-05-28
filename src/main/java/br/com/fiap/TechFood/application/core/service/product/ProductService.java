package br.com.fiap.TechFood.application.core.service.product;

import br.com.fiap.TechFood.application.core.domain.product.Product;
import br.com.fiap.TechFood.application.core.domain.product.ProductCategory;
import br.com.fiap.TechFood.application.port.PagePort;
import br.com.fiap.TechFood.application.port.product.ProductRepositoryPort;
import br.com.fiap.TechFood.application.port.product.ProductServicePort;
import br.com.fiap.TechFood.infrastructure.adapter.in.product.ProductForm;
import br.com.fiap.TechFood.infrastructure.adapter.out.PageDTO;

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
    public Product update(Product product, ProductForm productForm) {
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
    public PagePort<Product> findAllByCategory(String categoryName, int page, int size) {
        Optional<ProductCategory> category = ProductCategory.findByName(categoryName);
        if (category.isEmpty()) return PageDTO.empty();
        return productRepositoryPort.findAllByCategory(category.get(), page, size);
    }
}
