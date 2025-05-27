package br.com.fiap.TechFood.infrastructure.adapter.out.user.repository;

import br.com.fiap.TechFood.infrastructure.adapter.out.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

}