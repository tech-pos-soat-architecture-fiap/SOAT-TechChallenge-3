package br.com.fiap.TechFood.infrastructure.adapter.in.user;

import br.com.fiap.TechFood.application.core.domain.user.User;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

public record UserForm(
        @NotBlank String name,
        @Email String email,
        @CPF String cpf
) {

    public User toUser() {
        return new User(name, email, cpf);
    }
}
