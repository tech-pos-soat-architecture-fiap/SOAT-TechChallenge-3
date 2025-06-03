package br.com.fiap.TechFood.infrastructure.adapter.out.user.repository;

import br.com.fiap.TechFood.infrastructure.adapter.out.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByCpf_Cpf(String cpf);

}