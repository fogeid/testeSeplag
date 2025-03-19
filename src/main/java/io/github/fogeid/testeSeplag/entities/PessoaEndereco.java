package io.github.fogeid.testeSeplag.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb_pessoa_endereco")
public class PessoaEndereco implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long peId;

    @ManyToOne
    @JoinColumn(name = "pes_id", nullable = false)
    private Pessoa pessoa;

    @ManyToOne
    @JoinColumn(name = "end_id", nullable = false)
    private Endereco endereco;

    public PessoaEndereco() {

    }

    public PessoaEndereco(Long peId, Pessoa pessoa, Endereco endereco) {
        this.peId = peId;
        this.pessoa = pessoa;
        this.endereco = endereco;
    }

    public Long getPeId() {
        return peId;
    }

    public void setPeId(Long peId) {
        this.peId = peId;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
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
        if (!(o instanceof PessoaEndereco that)) return false;
        return peId.equals(that.peId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(peId);
    }
}
