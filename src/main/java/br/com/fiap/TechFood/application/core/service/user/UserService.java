package br.com.fiap.TechFood.application.core.service.user;

import br.com.fiap.TechFood.application.core.domain.user.User;
import br.com.fiap.TechFood.application.port.PagePort;
import br.com.fiap.TechFood.application.port.user.UserRespositoryPort;
import br.com.fiap.TechFood.application.port.user.UserServicePort;

import java.util.Optional;

public class UserService implements UserServicePort {

    private final UserRespositoryPort userRespositoryPort;

    public UserService(UserRespositoryPort userRespositoryPort) {
        this.userRespositoryPort = userRespositoryPort;
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRespositoryPort.findById(id);
    }

    @Override
    public PagePort<User> findAll(int page, int size) {
        return userRespositoryPort.findAll(page, size);
    }

    @Override
    public User create(User user) {
        return userRespositoryPort.save(user);
    }
}
