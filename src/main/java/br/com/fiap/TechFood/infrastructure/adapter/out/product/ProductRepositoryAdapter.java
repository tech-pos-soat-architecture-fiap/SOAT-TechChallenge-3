package br.com.fiap.TechFood.infrastructure.adapter.out.product;

import br.com.fiap.TechFood.application.core.domain.product.Product;
import br.com.fiap.TechFood.application.core.domain.product.ProductCategory;
import br.com.fiap.TechFood.application.port.PagePort;
import br.com.fiap.TechFood.application.port.product.ProductRepositoryPort;
import br.com.fiap.TechFood.infrastructure.adapter.out.PageDTO;
import br.com.fiap.TechFood.infrastructure.adapter.out.product.entity.ProductEntity;
import br.com.fiap.TechFood.infrastructure.adapter.out.product.repository.ProductEntityRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProductRepositoryAdapter implements ProductRepositoryPort {

    private final ProductEntityRepository productEntityRepository;

    public ProductRepositoryAdapter(ProductEntityRepository productEntityRepository) {
        this.productEntityRepository = productEntityRepository;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productEntityRepository.findById(id).map(ProductEntity::getProduct);
    }

    @Override
    public Product save(Product product) {
        return productEntityRepository.save(new ProductEntity(product)).getProduct();
    }

    @Override
    public PagePort<Product> findAll(int page, int size) {
        Page<Product> products = productEntityRepository.findAll(PageRequest.of(page, size)).map(ProductEntity::getProduct);
        return new PageDTO<>(products);
    }

    @Override
    public PagePort<Product> findAllByCategory(ProductCategory category, int page, int size) {
        Page<Product> products = productEntityRepository.findAllByCategory(category, PageRequest.of(page, size)).map(ProductEntity::getProduct);
        return new PageDTO<>(products);
    }

    @Override
    public void remove(Product product) {
        productEntityRepository.delete(new ProductEntity(product));
    }
}
