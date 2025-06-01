package br.com.fiap.TechFood.infrastructure.adapter.in.order;

import br.com.fiap.TechFood.application.core.domain.user.User;

public record UserOrderView(Long id, String cpf, String name) {

    public static UserOrderView of(User user) {
        return new UserOrderView(user.getId(), user.getCpf(), user.getName());
    }
}
