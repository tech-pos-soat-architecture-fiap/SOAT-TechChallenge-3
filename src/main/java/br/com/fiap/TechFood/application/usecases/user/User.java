package br.com.fiap.TechFood.application.usecases.user;

import br.com.fiap.TechFood.application.usecases.user.vo.Cpf;

public class User {

    private Long id;
    private String name;
    private String email;
    private Cpf cpf;

    public User(String name, String email, Cpf cpf) {
        this.name = name;
        this.email = email;
        this.cpf = cpf;
    }

    public User(Long id, String name, String email, Cpf cpf) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cpf = cpf;
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

    public Cpf getCpf() {
        return cpf;
    }

    public String getCpfAsString() {
        return cpf.getCpf();
    }
}
