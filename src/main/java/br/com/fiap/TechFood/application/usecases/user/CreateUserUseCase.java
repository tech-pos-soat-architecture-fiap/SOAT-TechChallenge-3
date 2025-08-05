package br.com.fiap.TechFood.application.usecases.user;

import br.com.fiap.TechFood.application.domain.user.User;
import br.com.fiap.TechFood.application.port.user.CreateUserPort;
import br.com.fiap.TechFood.application.port.user.UserRepositoryPort;
import br.com.fiap.TechFood.application.usecases.user.vo.Cpf;
import br.com.fiap.TechFood.infrastructure.adapter.in.user.UserView;

public class CreateUserUseCase implements CreateUserPort {
    private final UserRepositoryPort userRepositoryPort;

    public CreateUserUseCase(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    public UserView createUser(String name, String email, String cpf) {
        User user = new User(name, email, new Cpf(cpf));
        return UserView.of(userRepositoryPort.save(user));
    }
} 