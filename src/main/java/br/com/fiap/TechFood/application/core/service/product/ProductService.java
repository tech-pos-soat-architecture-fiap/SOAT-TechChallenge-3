package br.com.fiap.TechFood.application.core.service.product;

import br.com.fiap.TechFood.application.core.usecases.product.domain.Product;
import br.com.fiap.TechFood.application.core.usecases.product.domain.ProductCategory;
import br.com.fiap.TechFood.application.port.PagePort;
import br.com.fiap.TechFood.application.port.product.ProductGatewayPort;
import br.com.fiap.TechFood.application.port.product.ProductServicePort;
import br.com.fiap.TechFood.application.shared.exception.NotFoundException;

import java.util.List;
import java.util.Optional;

public class ProductService implements ProductServicePort {

    private final ProductGatewayPort productGatewayPort;

    public ProductService(ProductGatewayPort productGatewayPort) {
        this.productGatewayPort = productGatewayPort;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productGatewayPort.findById(id);
    }

    @Override
    public Product create(Product product) {
        return productGatewayPort.save(product);
    }

    @Override
    public Product update(Long id, Product updatedProductData) {
        return productGatewayPort.update(id, updatedProductData);
    }

    @Override
    public void remove(Long id) {
        Product product = productGatewayPort.findById(id).orElseThrow(NotFoundException::new);
        productGatewayPort.remove(product);
    }

    @Override
    public PagePort<Product> findAll(int page, int size) {
        return productGatewayPort.findAll(page, size);
    }

    @Override
    public PagePort<Product> findAllByCategory(ProductCategory productCategory, int page, int size) {
        return productGatewayPort.findAllByCategory(productCategory, page, size);
    }

    @Override
    public List<Product> getProductsByIds(List<Long> ids) {
        return productGatewayPort.findAllByIdIn(ids);
    }

    @Override
    public Optional<Product> findByName(String name) {
        return productGatewayPort.findByName(name);
    }
}
