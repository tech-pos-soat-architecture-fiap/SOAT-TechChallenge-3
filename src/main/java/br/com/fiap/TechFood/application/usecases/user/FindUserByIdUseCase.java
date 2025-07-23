package br.com.fiap.TechFood.application.usecases.user;

import br.com.fiap.TechFood.application.port.user.FindUserByIdPort;
import br.com.fiap.TechFood.application.port.user.UserRepositoryPort;

import java.util.Optional;

public class FindUserByIdUseCase implements FindUserByIdPort {

    private final UserRepositoryPort userRepositoryPort;

    public FindUserByIdUseCase(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    public Optional<User> findById(Long id) {
        return userRepositoryPort.findById(id);
    }
} 