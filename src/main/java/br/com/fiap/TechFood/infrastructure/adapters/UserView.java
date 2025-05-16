package br.com.fiap.TechFood.infrastructure.adapters;

import br.com.fiap.TechFood.core.domain.User;

public record UserView(Long id, String name, String email, String cpf) {

    public static UserView of(User user) {
        return new UserView(user.getId(), user.getName(), user.getEmail(), user.getCpf());
    }
}
