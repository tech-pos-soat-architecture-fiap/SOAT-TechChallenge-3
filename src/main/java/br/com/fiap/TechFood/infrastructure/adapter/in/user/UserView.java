package br.com.fiap.TechFood.infrastructure.adapter.in.user;

import br.com.fiap.TechFood.application.core.domain.user.User;

public record UserView(Long id, String name, String email, String cpf) {

    public static UserView of(User user) {
        return new UserView(user.getId(), user.getName(), user.getEmail(), user.getCpfAsString());
    }
}
