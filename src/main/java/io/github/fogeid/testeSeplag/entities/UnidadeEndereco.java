package io.github.fogeid.testeSeplag.entities;


import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb_unidade_endereco")
public class UnidadeEndereco implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ueId;

    @ManyToOne
    @JoinColumn(name = "unid_id", nullable = false)
    private Unidade unidade;


    @ManyToOne
    @JoinColumn(name = "end_id", nullable = false)
    private Endereco endereco;

    public UnidadeEndereco() {

    }

    public UnidadeEndereco(Unidade unidade, Endereco endereco) {
        this.unidade = unidade;
        this.endereco = endereco;
    }

    public Long getUeId() {
        return ueId;
    }

    public void setUeId(Long ueId) {
        this.ueId = ueId;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UnidadeEndereco that)) return false;
        return ueId.equals(that.ueId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ueId);
    }
}
