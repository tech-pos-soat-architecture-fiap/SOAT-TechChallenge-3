package br.com.fiap.TechFood.application.port.user;

import br.com.fiap.TechFood.application.usecases.user.User;
import java.util.Optional;

public interface FindUserByCpfPort {
    Optional<User> findByCpf(String cpf);
} 