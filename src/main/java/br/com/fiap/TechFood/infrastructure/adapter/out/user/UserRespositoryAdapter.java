package br.com.fiap.TechFood.infrastructure.adapter.out.user;

import br.com.fiap.TechFood.application.core.domain.user.User;
import br.com.fiap.TechFood.application.port.PagePort;
import br.com.fiap.TechFood.application.port.user.UserRespositoryPort;
import br.com.fiap.TechFood.infrastructure.adapter.out.PageDTO;
import br.com.fiap.TechFood.infrastructure.adapter.out.user.entity.UserEntity;
import br.com.fiap.TechFood.infrastructure.adapter.out.user.repository.UserEntityRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserRespositoryAdapter implements UserRespositoryPort {

    private final UserEntityRepository userEntityRepository;

    public UserRespositoryAdapter(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    @Override
    public Optional<User> findById(Long id) {
        return userEntityRepository.findById(id).map(UserEntity::getUser);
    }

    @Override
    public PagePort<User> findAll(int page, int size) {
        Page<User> users = userEntityRepository.findAll(PageRequest.of(page, size)).map(UserEntity::getUser);
        return new PageDTO<>(users);
    }

    @Override
    public User save(User user) {
        return userEntityRepository.save(new UserEntity(user)).getUser();
    }
}
