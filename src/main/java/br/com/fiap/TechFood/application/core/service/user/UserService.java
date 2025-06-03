package br.com.fiap.TechFood.application.core.service.user;

import br.com.fiap.TechFood.application.core.domain.user.User;
import br.com.fiap.TechFood.application.port.PagePort;
import br.com.fiap.TechFood.application.port.user.UserRepositoryPort;
import br.com.fiap.TechFood.application.port.user.UserServicePort;

import java.util.Optional;

public class UserService implements UserServicePort {

    private final UserRepositoryPort userRepositoryPort;

    public UserService(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepositoryPort.findById(id);
    }

    @Override
    public Optional<User> findByCpf(String cpf) {
        return userRepositoryPort.findByCpf_Cpf(cpf);
    }

    @Override
    public PagePort<User> findAll(int page, int size) {
        return userRepositoryPort.findAll(page, size);
    }

    @Override
    public User create(User user) {
        return userRepositoryPort.save(user);
    }
}
