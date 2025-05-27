package br.com.fiap.TechFood.infrastructure.config;

import br.com.fiap.TechFood.application.core.service.user.ProductService;
import br.com.fiap.TechFood.application.core.service.user.UserService;
import br.com.fiap.TechFood.application.port.product.ProductRepositoryPort;
import br.com.fiap.TechFood.application.port.product.ProductServicePort;
import br.com.fiap.TechFood.application.port.user.UserRepositoryPort;
import br.com.fiap.TechFood.application.port.user.UserServicePort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DependencyInjectionConfig {

    @Bean
    public UserServicePort userService(UserRepositoryPort userGateway) {
        return new UserService(userGateway);
    }

    @Bean
    public ProductServicePort productService(ProductRepositoryPort productGateway) {
        return new ProductService((productGateway));
    }
}
