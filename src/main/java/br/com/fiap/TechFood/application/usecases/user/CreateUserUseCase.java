package br.com.fiap.TechFood.application.usecases.user;

import br.com.fiap.TechFood.application.domain.user.User;
import br.com.fiap.TechFood.application.port.user.CreateUserPort;
import br.com.fiap.TechFood.application.port.user.UserRepositoryPort;
import br.com.fiap.TechFood.application.usecases.user.vo.Cpf;

public class CreateUserUseCase implements CreateUserPort {
    private final UserRepositoryPort userRepositoryPort;

    public CreateUserUseCase(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    public User createUser(String name, String email, String cpf) {
        User user = new User(name, email, new Cpf(cpf));
        return userRepositoryPort.save(user);
    }
} 