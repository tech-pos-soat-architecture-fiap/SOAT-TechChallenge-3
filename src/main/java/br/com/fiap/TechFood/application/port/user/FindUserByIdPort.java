package br.com.fiap.TechFood.application.port.user;

import br.com.fiap.TechFood.infrastructure.adapter.in.user.UserView;

import java.util.Optional;

public interface FindUserByIdPort {
    Optional<UserView> findById(Long id);
} 