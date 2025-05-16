package br.com.fiap.TechFood.infrastructure.adapters.out;

import br.com.fiap.TechFood.core.domain.User;
import br.com.fiap.TechFood.core.ports.PagePort;
import br.com.fiap.TechFood.core.ports.UserGateway;
import br.com.fiap.TechFood.infrastructure.adapters.PageDTO;
import br.com.fiap.TechFood.infrastructure.adapters.out.entity.UserEntity;
import br.com.fiap.TechFood.infrastructure.adapters.out.repository.UserEntityRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserGatewayAdapter implements UserGateway {

    private final UserEntityRepository userEntityRepository;

    public UserGatewayAdapter(UserEntityRepository userEntityRepository) {
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
