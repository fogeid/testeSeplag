package io.github.fogeid.testeSeplag.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "tb_lotacao")
public class Lotacao implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lotId;

    private LocalDate lotDataLotacao;

    private LocalDate lotDataRemocao;

    private String lotPortaria;

    @ManyToOne
    @JoinColumn(name = "pes_id", nullable = false)
    private Pessoa pessoa;

    @ManyToOne
    @JoinColumn(name = "unid_id", nullable = false)
    private Unidade unidade;

    public Lotacao() {

    }

    public Lotacao(Long lotId, LocalDate lotDataLotacao, LocalDate lotDataRemocao, String lotPortaria, Pessoa pessoa, Unidade unidade) {
        this.lotId = lotId;
        this.lotDataLotacao = lotDataLotacao;
        this.lotDataRemocao = lotDataRemocao;
        this.lotPortaria = lotPortaria;
        this.pessoa = pessoa;
        this.unidade = unidade;
    }

    public Long getLotId() {
        return lotId;
    }

    public void setLotId(Long lotId) {
        this.lotId = lotId;
    }

    public LocalDate getLotDataLotacao() {
        return lotDataLotacao;
    }

    public void setLotDataLotacao(LocalDate lotDataLotacao) {
        this.lotDataLotacao = lotDataLotacao;
    }

    public LocalDate getLotDataRemocao() {
        return lotDataRemocao;
    }

    public void setLotDataRemocao(LocalDate lotDataRemocao) {
        this.lotDataRemocao = lotDataRemocao;
    }

    public String getLotPortaria() {
        return lotPortaria;
    }

    public void setLotPortaria(String lotPortaria) {
        this.lotPortaria = lotPortaria;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lotacao lotacao)) return false;
        return lotId.equals(lotacao.lotId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lotId);
    }
}
