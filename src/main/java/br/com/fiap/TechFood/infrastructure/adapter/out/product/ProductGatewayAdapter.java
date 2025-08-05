package br.com.fiap.TechFood.infrastructure.adapter.out.product;

import br.com.fiap.TechFood.application.port.PagePort;
import br.com.fiap.TechFood.application.port.product.out.ProductGatewayPort;
import br.com.fiap.TechFood.application.domain.product.Product;
import br.com.fiap.TechFood.application.domain.product.ProductCategory;
import br.com.fiap.TechFood.infrastructure.adapter.out.PageDTO;
import br.com.fiap.TechFood.infrastructure.adapter.out.product.entity.ProductEntity;
import br.com.fiap.TechFood.infrastructure.adapter.out.product.repository.ProductEntityRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProductGatewayAdapter implements ProductGatewayPort {

    private final ProductEntityRepository productEntityRepository;

    public ProductGatewayAdapter(ProductEntityRepository productEntityRepository) {
        this.productEntityRepository = productEntityRepository;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productEntityRepository.findById(id).map(ProductEntity::toProduct);
    }

    @Override
    public Product save(Product product) {
        return productEntityRepository.save(new ProductEntity(product)).toProduct();
    }

    @Override
    public PagePort<Product> findAll(int page, int size) {
        Page<Product> products = productEntityRepository.findAll(PageRequest.of(page, size)).map(ProductEntity::toProduct);
        return new PageDTO<>(products);
    }

    @Override
    public PagePort<Product> findAllByCategory(ProductCategory category, int page, int size) {
        Page<Product> products = productEntityRepository.findAllByCategory(category, PageRequest.of(page, size)).map(ProductEntity::toProduct);
        return new PageDTO<>(products);
    }

    @Override
    public void remove(Product product) {
        productEntityRepository.delete(new ProductEntity(product));
    }

    @Override
    public List<Product> findAllByIdIn(List<Long> ids) {
        return productEntityRepository.findAllById(ids).stream().map(ProductEntity::toProduct).toList();
    }

    @Override
    public Optional<Product> findByName(String name) {
        return productEntityRepository.findByName(name).map(ProductEntity::toProduct);
    }
}
