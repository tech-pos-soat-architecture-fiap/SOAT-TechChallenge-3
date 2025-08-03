package br.com.fiap.TechFood.infrastructure.config;

import br.com.fiap.TechFood.application.port.product.in.*;
import br.com.fiap.TechFood.application.port.product.out.ProductGatewayPort;
import br.com.fiap.TechFood.application.usecases.product.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductDependencyInjectionConfig {

    @Bean
    public CreateProductPort createProductUseCase(ProductGatewayPort productGatewayPort) {
        return new CreateProductUseCase(productGatewayPort);
    }

    @Bean
    public FindProductsByCategoryPort findProductsByCategoryPort(ProductGatewayPort productGatewayPort) {
        return new FindProductsByCategoryUseCase(productGatewayPort);
    }

    @Bean
    public UpdateProductPort updateProductPort(ProductGatewayPort productGatewayPort) {
        return new UpdateProductUseCase(productGatewayPort);
    }

    @Bean
    public FindProductPort findProductPort(ProductGatewayPort productGatewayPort) {
        return new FindProductUseCase(productGatewayPort);
    }

    @Bean
    public FindAllProductsPort finddAllProductsPort(ProductGatewayPort productGatewayPort) {
        return new FindAllProductsUseCase(productGatewayPort);
    }

    @Bean
    public RemoveProductPort removeProductPort(ProductGatewayPort productGatewayPort) {
        return new RemoveProductUseCase(productGatewayPort);
    }
} 