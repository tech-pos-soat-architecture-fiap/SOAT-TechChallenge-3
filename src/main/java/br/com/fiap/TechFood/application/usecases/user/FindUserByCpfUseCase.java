package br.com.fiap.TechFood.application.usecases.user;

import br.com.fiap.TechFood.application.port.user.FindUserByCpfPort;
import br.com.fiap.TechFood.application.port.user.UserRepositoryPort;
import br.com.fiap.TechFood.infrastructure.adapter.in.user.UserView;

import java.util.Optional;

public class FindUserByCpfUseCase implements FindUserByCpfPort {

    private final UserRepositoryPort userRepositoryPort;

    public FindUserByCpfUseCase(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    public Optional<UserView> findByCpf(String cpf) {
        return userRepositoryPort.findByCpf_Cpf(cpf).map(UserView::of);
    }
} 