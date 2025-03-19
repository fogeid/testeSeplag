package io.github.fogeid.testeSeplag.entities;


import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "tb_servidor_temporario")
public class ServidorTemporario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long pesId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "pes_id", nullable = false)
    private Pessoa pessoa;

    private LocalDate stDataAdmissao;

    private LocalDate stDataDemissao;

    public ServidorTemporario() {

    }

    public ServidorTemporario(Long pesId, Pessoa pessoa, LocalDate stDataAdmissao, LocalDate stDataDemissao) {
        this.pesId = pesId;
        this.pessoa = pessoa;
        this.stDataAdmissao = stDataAdmissao;
        this.stDataDemissao = stDataDemissao;
    }

    public Long getPesId() {
        return pesId;
    }

    public void setPesId(Long pesId) {
        this.pesId = pesId;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public LocalDate getStDataAdmissao() {
        return stDataAdmissao;
    }

    public void setStDataAdmissao(LocalDate stDataAdmissao) {
        this.stDataAdmissao = stDataAdmissao;
    }

    public LocalDate getStDataDemissao() {
        return stDataDemissao;
    }

    public void setStDataDemissao(LocalDate stDataDemissao) {
        this.stDataDemissao = stDataDemissao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ServidorTemporario that)) return false;
        return pesId.equals(that.pesId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pesId);
    }
}
