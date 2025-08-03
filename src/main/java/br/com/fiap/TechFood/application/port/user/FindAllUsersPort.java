package br.com.fiap.TechFood.application.port.user;

import br.com.fiap.TechFood.application.port.PagePort;
import br.com.fiap.TechFood.application.domain.user.User;

public interface FindAllUsersPort {
    PagePort<User> findAll(int page, int size);
} 