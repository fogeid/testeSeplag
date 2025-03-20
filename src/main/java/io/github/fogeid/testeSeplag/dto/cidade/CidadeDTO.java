package io.github.fogeid.testeSeplag.dto.cidade;

import io.github.fogeid.testeSeplag.entities.Cidade;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;

public class CidadeDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cidId;

    private String cidNome;

    private String cidUf;

    public CidadeDTO() {

    }

    public CidadeDTO(Cidade cidade) {
        this.cidId = cidade.getCidId();
        this.cidNome = cidade.getCidNome();
        this.cidUf = cidade.getCidUf();
    }

    public CidadeDTO(Long cidId, String cidNome, String cidUf) {
        this.cidId = cidId;
        this.cidNome = cidNome;
        this.cidUf = cidUf;
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
}
