package br.com.fiap.TechFood.infrastructure.config;

import br.com.fiap.TechFood.application.core.service.product.ProductService;
import br.com.fiap.TechFood.application.core.usecases.product.CreateProductUseCase;
import br.com.fiap.TechFood.application.core.usecases.product.FindProductsByCategoryUSeCase;
import br.com.fiap.TechFood.application.port.product.CreateProductPort;
import br.com.fiap.TechFood.application.port.product.FindProductsByCategoryPort;
import br.com.fiap.TechFood.application.port.product.ProductGatewayPort;
import br.com.fiap.TechFood.application.port.product.ProductServicePort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductDependencyInjectionConfig {

    private final ProductGatewayPort productGatewayPort;

    public ProductDependencyInjectionConfig(ProductGatewayPort productGatewayPort) {
        this.productGatewayPort = productGatewayPort;
    }

    @Bean
    public ProductServicePort productService() {
        return new ProductService(productGatewayPort);
    }

    @Bean
    public CreateProductPort createProductUseCase() {
        return new CreateProductUseCase(productService());
    }

    @Bean
    public FindProductsByCategoryPort findProductsByCategoryUSeCase() {
        return new FindProductsByCategoryUSeCase(productService());
    }
}
