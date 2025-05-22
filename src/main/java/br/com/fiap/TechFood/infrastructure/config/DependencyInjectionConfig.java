package br.com.fiap.TechFood.infrastructure.config;

import br.com.fiap.TechFood.application.core.service.user.UserService;
import br.com.fiap.TechFood.application.port.user.UserRespositoryPort;
import br.com.fiap.TechFood.application.port.user.UserServicePort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DependencyInjectionConfig {

    @Bean
    public UserServicePort userService(UserRespositoryPort userGateway) {
        return new UserService(userGateway);
    }
}
