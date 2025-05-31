package br.com.fiap.TechFood.application.port.user;

import br.com.fiap.TechFood.application.core.domain.user.User;
import br.com.fiap.TechFood.application.port.PagePort;

import java.util.Optional;

public interface UserRespositoryPort {

    Optional<User> findById(Long id);

    Optional<User> findByCpf(String cpf);

    User save(User user);

    PagePort<User> findAll(int page, int size);
}
