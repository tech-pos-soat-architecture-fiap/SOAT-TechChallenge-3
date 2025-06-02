package br.com.fiap.TechFood.application.port.user;

import br.com.fiap.TechFood.application.core.domain.user.User;
import br.com.fiap.TechFood.application.port.PagePort;

import java.util.Optional;

public interface UserRepositoryPort {

    Optional<User> findById(Long id);

    User save(User user);

    PagePort<User> findAll(int page, int size);
}
