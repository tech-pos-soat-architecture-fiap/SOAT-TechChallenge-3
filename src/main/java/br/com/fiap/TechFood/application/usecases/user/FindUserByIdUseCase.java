package br.com.fiap.TechFood.application.usecases.user;

import br.com.fiap.TechFood.application.port.user.FindUserByIdPort;
import br.com.fiap.TechFood.application.port.user.UserRepositoryPort;
import br.com.fiap.TechFood.infrastructure.adapter.in.user.UserView;

import java.util.Optional;

public class FindUserByIdUseCase implements FindUserByIdPort {

    private final UserRepositoryPort userRepositoryPort;

    public FindUserByIdUseCase(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    public Optional<UserView> findById(Long id) {
        return userRepositoryPort.findById(id).map(UserView::of);
    }
} 