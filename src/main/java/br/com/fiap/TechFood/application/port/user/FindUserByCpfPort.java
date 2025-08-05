package br.com.fiap.TechFood.application.port.user;

import br.com.fiap.TechFood.infrastructure.adapter.in.user.UserView;

import java.util.Optional;

public interface FindUserByCpfPort {
    Optional<UserView> findByCpf(String cpf);
} 