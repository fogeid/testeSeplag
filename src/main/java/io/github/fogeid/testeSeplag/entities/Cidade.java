package io.github.fogeid.testeSeplag.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb_cidade")
public class Cidade implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cidIdd;

    private String cidNome;

    private String cidUf;

    public Cidade() {

    }

    public Cidade(Long cidIdd, String cidNome, String cidUf) {
        this.cidIdd = cidIdd;
        this.cidNome = cidNome;
        this.cidUf = cidUf;
    }

    public Long getCidIdd() {
        return cidIdd;
    }

    public void setCidIdd(Long cidIdd) {
        this.cidIdd = cidIdd;
    }

    public String getCidNome() {
        return cidNome;
    }

    public void setCidNome(String cidNome) {
        this.cidNome = cidNome;
    }

    public String getCidUf() {
        return cidUf;
    }

    public void setCidUf(String cidUf) {
        this.cidUf = cidUf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cidade cidade)) return false;
        return cidIdd.equals(cidade.cidIdd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cidIdd);
    }
}
