package br.com.fiap.TechFood.infrastructure.config;

import br.com.fiap.TechFood.application.port.user.*;
import br.com.fiap.TechFood.application.usecases.user.*;
import br.com.fiap.TechFood.infrastructure.adapter.out.user.UserRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserDependencyInjectionConfig {

    @Bean
    public UserRepositoryPort userRepositoryPort(UserRepositoryAdapter userRepositoryAdapter) {
        return userRepositoryAdapter;
    }

    @Bean
    public CreateUserPort createUserUsePort(UserRepositoryPort userRepositoryPort) {
        return new CreateUserUseCase(userRepositoryPort);
    }

    @Bean
    public FindAllUsersPort findAllUsersPort(UserRepositoryPort userRepositoryPort) {
        return new FindAllUsersUseCase(userRepositoryPort);
    }

    @Bean
    public FindUserByIdPort findUserByIdPort(UserRepositoryPort userRepositoryPort) {
        return new FindUserByIdUseCase(userRepositoryPort);
    }

    @Bean
    public FindUserByCpfPort findUserByCpfPort(UserRepositoryPort userRepositoryPort) {
        return new FindUserByCpfUseCase(userRepositoryPort);
    }
} 