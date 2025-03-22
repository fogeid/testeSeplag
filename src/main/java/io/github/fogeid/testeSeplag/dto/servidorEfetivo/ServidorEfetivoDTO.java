package io.github.fogeid.testeSeplag.dto.servidorEfetivo;

import io.github.fogeid.testeSeplag.entities.Pessoa;
import io.github.fogeid.testeSeplag.entities.ServidorEfetivo;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;

import java.io.Serializable;

public class ServidorEfetivoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Long pesId;

    private Long pessoa;

    private String seMatricula;

    public ServidorEfetivoDTO() {

    }

    public ServidorEfetivoDTO(Long pesId, Long pessoa, String seMatricula) {
        this.pesId = pesId;
        this.pessoa = pessoa;
        this.seMatricula = seMatricula;
    }

    public ServidorEfetivoDTO(ServidorEfetivo servidorEfetivo) {
        this.pesId = servidorEfetivo.getPesId();
        this.pessoa = servidorEfetivo.getPessoa() != null ? servidorEfetivo.getPessoa().getPesId() : null;
        this.seMatricula = servidorEfetivo.getSeMatricula();
    }

    public Long getPesId() {
        return pesId;
    }

    public void setPesId(Long pesId) {
        this.pesId = pesId;
    }

    public Long getPessoa() {
        return pessoa;
    }

    public void setPessoa(Long pessoa) {
        this.pessoa = pessoa;
    }

    public String getSeMatricula() {
        return seMatricula;
    }

    public void setSeMatricula(String seMatricula) {
        this.seMatricula = seMatricula;
    }
}
