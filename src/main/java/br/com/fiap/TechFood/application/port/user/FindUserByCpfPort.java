package br.com.fiap.TechFood.application.port.user;

import br.com.fiap.TechFood.application.domain.user.User;
import java.util.Optional;

public interface FindUserByCpfPort {
    Optional<User> findByCpf(String cpf);
} 