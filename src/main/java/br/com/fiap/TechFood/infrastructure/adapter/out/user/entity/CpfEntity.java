package br.com.fiap.TechFood.infrastructure.adapter.out.user.entity;

import jakarta.persistence.Embeddable;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Objects;

@Embeddable
public class CpfEntity {

    @CPF
    private String cpf;

    @Deprecated
    public CpfEntity() {
    }

    public CpfEntity(String cpf) {
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CpfEntity cpfEntity = (CpfEntity) o;
        return Objects.equals(cpf, cpfEntity.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cpf);
    }
}
