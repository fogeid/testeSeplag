package io.github.fogeid.testeSeplag.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb_servidor_efetivo")
public class ServidorEfetivo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Long pesId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "pes_id", nullable = false)
    private Pessoa pessoa;

    private String seMatricula;

//    public ServidorEfetivo(ServidorEfetivo servidorEfetivo) {
//        this.pesId = servidorEfetivo.getPesId();
//        this.pessoa
//    }

    public ServidorEfetivo() {
        this.pesId = pesId;
        this.pessoa = pessoa;
        this.seMatricula = seMatricula;
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

    public String getSeMatricula() {
        return seMatricula;
    }

    public void setSeMatricula(String seMatricula) {
        this.seMatricula = seMatricula;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ServidorEfetivo that)) return false;
        return pesId.equals(that.pesId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pesId);
    }
}
