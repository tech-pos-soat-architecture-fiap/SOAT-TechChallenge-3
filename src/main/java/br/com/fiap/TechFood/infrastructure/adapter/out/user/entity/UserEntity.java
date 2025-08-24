package br.com.fiap.TechFood.infrastructure.adapter.out.user.entity;

import br.com.fiap.TechFood.application.domain.user.User;
import br.com.fiap.TechFood.application.usecases.user.vo.Cpf;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

@Entity
@Table(name = "users", indexes = {
        @Index(name = "idx_user_cpf", columnList = "cpf")
})

public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Email
    private String email;

    @Embedded
    @AttributeOverride(name = "cpf", column = @Column(name = "cpf"))
    private CpfEntity cpf;

    public UserEntity() {
    }

    public UserEntity(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.cpf = new CpfEntity(user.getCpfAsString());
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

    public CpfEntity getCpf() {
        return cpf;
    }

    public String getCpfAsString() {
        return cpf.getCpf();
    }

    public User toUser() {
        return new User(this.getId(), this.getName(), this.getEmail(), new Cpf(this.getCpfAsString()));
    }
}
