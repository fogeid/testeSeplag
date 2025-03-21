package io.github.fogeid.testeSeplag.dto.cidade;

import io.github.fogeid.testeSeplag.entities.Cidade;
import io.github.fogeid.testeSeplag.entities.Endereco;
import jakarta.persistence.*;

import java.io.Serializable;

public class EnderecoDTO implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long endId;

    private String endTipoLogradouro;

    private String endLougradouro;

    private Integer endNumero;

    private String endBairro;

    private Long cidId;

    public EnderecoDTO() {

    }

    public EnderecoDTO(Endereco endereco) {
        this.endId = endereco.getEndId();
        this.endTipoLogradouro = endereco.getEndTipoLogradouro();
        this.endLougradouro = endereco.getEndLougradouro();
        this.endNumero = endereco.getEndNumero();
        this.endBairro = endereco.getEndBairro();
        this.cidId = endereco.getCidade().getCidId();
    }

    public EnderecoDTO(Long endId, String endTipoLogradouro, String endLougradouro, Integer endNumero, String endBairro, Long cidId) {
        this.endId = endId;
        this.endTipoLogradouro = endTipoLogradouro;
        this.endLougradouro = endLougradouro;
        this.endNumero = endNumero;
        this.endBairro = endBairro;
        this.cidId = cidId;
    }

    public Long getEndId() {
        return endId;
    }

    public void setEndId(Long endId) {
        this.endId = endId;
    }

    public String getEndTipoLogradouro() {
        return endTipoLogradouro;
    }

    public void setEndTipoLogradouro(String endTipoLogradouro) {
        this.endTipoLogradouro = endTipoLogradouro;
    }

    public String getEndLougradouro() {
        return endLougradouro;
    }

    public void setEndLougradouro(String endLougradouro) {
        this.endLougradouro = endLougradouro;
    }

    public Integer getEndNumero() {
        return endNumero;
    }

    public void setEndNumero(Integer endNumero) {
        this.endNumero = endNumero;
    }

    public String getEndBairro() {
        return endBairro;
    }

    public void setEndBairro(String endBairro) {
        this.endBairro = endBairro;
    }

    public Long getCidId() {
        return cidId;
    }

    public void setCidId(Long cidId) {
        this.cidId = cidId;
    }
}
