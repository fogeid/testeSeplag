package io.github.fogeid.testeSeplag.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name = "tb_unidade_endereco")
public class UnidadeEndereco implements Serializable {

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
}
