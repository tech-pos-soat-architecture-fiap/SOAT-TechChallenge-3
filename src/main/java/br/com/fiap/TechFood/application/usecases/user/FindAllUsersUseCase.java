package br.com.fiap.TechFood.application.usecases.user;

import br.com.fiap.TechFood.application.port.PagePort;
import br.com.fiap.TechFood.application.port.user.FindAllUsersPort;
import br.com.fiap.TechFood.application.port.user.UserRepositoryPort;
import br.com.fiap.TechFood.infrastructure.adapter.in.user.UserView;

public class FindAllUsersUseCase implements FindAllUsersPort {

    private final UserRepositoryPort userRepositoryPort;

    public FindAllUsersUseCase(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    public PagePort<UserView> findAll(int page, int size) {
        return userRepositoryPort.findAll(page, size).map(UserView::of);
    }
} 