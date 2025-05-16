package br.com.fiap.TechFood.infrastructure.adapters;

import br.com.fiap.TechFood.core.domain.User;
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
