package br.com.fiap.TechFood.application.port.user;

import br.com.fiap.TechFood.application.domain.user.User;

public interface CreateUserPort {
    User createUser(String name, String email, String cpf);
} 