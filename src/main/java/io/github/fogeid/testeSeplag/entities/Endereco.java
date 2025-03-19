package io.github.fogeid.testeSeplag.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb_endereco")
public class Endereco implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long endIdd;

    private String endTipoLogradouro;
    private String endLougradouro;
    private Integer endNumero;
    private String endBairro;

    @ManyToOne
    @JoinColumn(name = "cid_id", nullable = false)
    private Cidade cidade;

    public Endereco() {
    }

    public Endereco(Long endIdd, String endTipoLogradouro, String endLougradouro, Integer endNumero, String endBairro, Cidade cidade) {
        this.endIdd = endIdd;
        this.endTipoLogradouro = endTipoLogradouro;
        this.endLougradouro = endLougradouro;
        this.endNumero = endNumero;
        this.endBairro = endBairro;
        this.cidade = cidade;
    }

    public Long getEndIdd() {
        return endIdd;
    }

    public void setEndIdd(Long endIdd) {
        this.endIdd = endIdd;
    }

    public String getEndTipoLogradouro() {
        return endTipoLogradouro;
    }

    public void setEndTipoLogradouro(String endTipoLogradouro) {
        this.endTipoLogradouro = endTipoLogradouro;
    }

    public String getEndLougradouro() {
        return endLougradouro;
    }

    public void setEndLougradouro(String endLougradouro) {
        this.endLougradouro = endLougradouro;
    }

    public Integer getEndNumero() {
        return endNumero;
    }

    public void setEndNumero(Integer endNumero) {
        this.endNumero = endNumero;
    }

    public String getEndBairro() {
        return endBairro;
    }

    public void setEndBairro(String endBairro) {
        this.endBairro = endBairro;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Endereco endereco)) return false;
        return endIdd.equals(endereco.endIdd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(endIdd);
    }
}
