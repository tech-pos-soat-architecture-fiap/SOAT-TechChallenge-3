package br.com.fiap.TechFood.core.ports;

import br.com.fiap.TechFood.core.domain.User;

import java.util.Optional;

public interface UserGateway {

    Optional<User> findById(Long id);

    User save(User user);

    PagePort<User> findAll(int page, int size);
}
