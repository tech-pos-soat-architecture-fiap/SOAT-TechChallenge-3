package br.com.fiap.TechFood.infrastructure.config;

import br.com.fiap.TechFood.core.ports.UserGateway;
import br.com.fiap.TechFood.core.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DependencyInjectionConfig {

    @Bean
    public UserService userService(UserGateway userGateway) {
        return new UserService(userGateway);
    }
}
