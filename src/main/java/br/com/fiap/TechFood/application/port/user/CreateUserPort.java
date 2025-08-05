package br.com.fiap.TechFood.application.port.user;

import br.com.fiap.TechFood.infrastructure.adapter.in.user.UserView;

public interface CreateUserPort {
    UserView createUser(String name, String email, String cpf);
} 