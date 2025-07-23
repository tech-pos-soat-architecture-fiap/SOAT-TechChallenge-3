package br.com.fiap.TechFood.application.port.user;

import br.com.fiap.TechFood.application.usecases.user.User;
import java.util.Optional;

public interface FindUserByIdPort {
    Optional<User> findById(Long id);
} 