package io.github.fogeid.testeSeplag.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_cidade")
public class Cidade implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cidId;

    private String cidNome;

    private String cidUf;

    @OneToMany(mappedBy = "cidade")
    private List<Endereco> enderecos;

    public Cidade() {

    }

    public Cidade(Long cidId, String cidNome, String cidUf, List<Endereco> enderecos) {
        this.cidId = cidId;
        this.cidNome = cidNome;
        this.cidUf = cidUf;
        this.enderecos = enderecos;
    }

    public Long getCidId() {
        return cidId;
    }

    public void setCidId(Long cidId) {
        this.cidId = cidId;
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

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cidade cidade)) return false;
        return cidId.equals(cidade.cidId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cidId);
    }
}
