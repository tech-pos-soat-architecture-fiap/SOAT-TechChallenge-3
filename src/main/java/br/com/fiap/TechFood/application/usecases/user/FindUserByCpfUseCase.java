package br.com.fiap.TechFood.application.usecases.user;

import br.com.fiap.TechFood.application.domain.user.User;
import br.com.fiap.TechFood.application.port.user.FindUserByCpfPort;
import br.com.fiap.TechFood.application.port.user.UserRepositoryPort;

import java.util.Optional;

public class FindUserByCpfUseCase implements FindUserByCpfPort {

    private final UserRepositoryPort userRepositoryPort;

    public FindUserByCpfUseCase(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    public Optional<User> findByCpf(String cpf) {
        return userRepositoryPort.findByCpf_Cpf(cpf);
    }
} 