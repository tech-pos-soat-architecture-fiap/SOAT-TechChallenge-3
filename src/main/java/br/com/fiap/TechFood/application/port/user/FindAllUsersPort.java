package br.com.fiap.TechFood.application.port.user;

import br.com.fiap.TechFood.application.port.PagePort;
import br.com.fiap.TechFood.application.domain.user.User;
import br.com.fiap.TechFood.infrastructure.adapter.in.user.UserView;

public interface FindAllUsersPort {
    PagePort<UserView> findAll(int page, int size);
} 