package io.github.fogeid.testeSeplag.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb_unidade")
public class Unidade implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long unidId;

    private String unidNome;

    private String unidSigla;

    public Unidade() {

    }

    public Unidade(Long unidId, String unidNome, String unidSigla) {
        this.unidId = unidId;
        this.unidNome = unidNome;
        this.unidSigla = unidSigla;
    }

    public Long getUnidId() {
        return unidId;
    }

    public void setUnidId(Long unidId) {
        this.unidId = unidId;
    }

    public String getUnidNome() {
        return unidNome;
    }

    public void setUnidNome(String unidNome) {
        this.unidNome = unidNome;
    }

    public String getUnidSigla() {
        return unidSigla;
    }

    public void setUnidSigla(String unidSigla) {
        this.unidSigla = unidSigla;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Unidade unidade)) return false;
        return unidId.equals(unidade.unidId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(unidId);
    }
}
