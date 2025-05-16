package br.com.fiap.TechFood.infrastructure.adapters.out.entity;

import br.com.fiap.TechFood.core.domain.User;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String cpf;

    public UserEntity() {
    }

    public UserEntity(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.cpf = user.getCpf();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public User getUser() {
        return new User(this.getId(), this.getName(), this.getEmail(), this.getCpf());
    }
}
