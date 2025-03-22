package io.github.fogeid.testeSeplag.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_endereco")
public class Endereco implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long endId;

    private String endTipoLogradouro;

    private String endLougradouro;

    private Integer endNumero;

    private String endBairro;

    @ManyToOne
    @JoinColumn(name = "cid_id", nullable = false)
    private Cidade cidade;

    @ManyToMany(mappedBy = "enderecos")
    private Set<Pessoa> pessoas = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "tb_unidade_endereco",
            joinColumns = @JoinColumn(name = "end_id"),
            inverseJoinColumns = @JoinColumn(name = "unid_id")
    )
    private Set<Unidade> unidades = new HashSet<>();

    public Endereco() {
    }

    public Endereco(Long endId, String endTipoLogradouro, String endLougradouro, Integer endNumero, String endBairro, Cidade cidade, Set<Pessoa> pessoas, Set<Unidade> unidades) {
        this.endId = endId;
        this.endTipoLogradouro = endTipoLogradouro;
        this.endLougradouro = endLougradouro;
        this.endNumero = endNumero;
        this.endBairro = endBairro;
        this.cidade = cidade;
        this.pessoas = pessoas;
        this.unidades = unidades;
    }

    public Long getEndId() {
        return endId;
    }

    public void setEndId(Long endId) {
        this.endId = endId;
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

    public Set<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(Set<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    public void setUnidades(Set<Unidade> unidades) {
        this.unidades = unidades;
    }

    public Set<Unidade> getUnidades() {
        return unidades;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Endereco endereco)) return false;
        return endId.equals(endereco.endId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(endId);
    }
}
