package io.github.fogeid.testeSeplag.dto.cidade;

import io.github.fogeid.testeSeplag.entities.Unidade;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;

public class UnidadeDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long unidId;

    private String unidNome;

    private String unidSigla;

    public UnidadeDTO() {

    }

    public UnidadeDTO(Unidade unidade) {
        this.unidId = unidade.getUnidId();
        this.unidNome = unidade.getUnidNome();
        this.unidSigla = unidade.getUnidSigla();
    }

    public UnidadeDTO(Long unidId, String unidNome, String unidSigla) {
        this.unidId = unidId;
        this.unidNome = unidNome;
        this.unidSigla = unidSigla;
    }

    public Long getUnidId() {
        return unidId;
    }

    public void setUnidId(Long unidId) {
        this.unidId = unidId;
    }

    public String getUnidNome() {
        return unidNome;
    }

    public void setUnidNome(String unidNome) {
        this.unidNome = unidNome;
    }

    public String getUnidSigla() {
        return unidSigla;
    }

    public void setUnidSigla(String unidSigla) {
        this.unidSigla = unidSigla;
    }
}
