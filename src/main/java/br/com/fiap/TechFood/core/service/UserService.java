package br.com.fiap.TechFood.core.service;

import br.com.fiap.TechFood.core.domain.User;
import br.com.fiap.TechFood.core.ports.PagePort;
import br.com.fiap.TechFood.core.ports.UserGateway;

import java.util.Optional;

public class UserService {

    private final UserGateway userGateway;

    public UserService(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public Optional<User> findById(Long id) {
        return userGateway.findById(id);
    }

    public PagePort<User> findAll(int page, int size) {
        return userGateway.findAll(page, size);
    }

    public User create(User user) {
        return userGateway.save(user);
    }
}
