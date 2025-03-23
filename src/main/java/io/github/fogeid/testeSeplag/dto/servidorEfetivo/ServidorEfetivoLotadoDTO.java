package io.github.fogeid.testeSeplag.dto.servidorEfetivo;

import jakarta.persistence.Id;

import java.io.Serializable;
import java.time.LocalDate;

public class ServidorEfetivoLotadoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Long pesId;
    private String pesNome;
    private String seMatricula;
    private Long unidId;
    private String unidNome;
    private LocalDate lotDataLotacao;
    private LocalDate lotDataRemocao;
    private String lotPortaria;

    public ServidorEfetivoLotadoDTO(Long pesId, String pesNome, String seMatricula, Long unidId, String unidNome,
                                    LocalDate lotDataLotacao, LocalDate lotDataRemocao, String lotPortaria) {
        this.pesId = pesId;
        this.pesNome = pesNome;
        this.seMatricula = seMatricula;
        this.unidId = unidId;
        this.unidNome = unidNome;
        this.lotDataLotacao = lotDataLotacao;
        this.lotDataRemocao = lotDataRemocao;
        this.lotPortaria = lotPortaria;
    }

    // Getters e Setters
    public Long getPesId() {
        return pesId;
    }

    public void setPesId(Long pesId) {
        this.pesId = pesId;
    }

    public String getPesNome() {
        return pesNome;
    }

    public void setPesNome(String pesNome) {
        this.pesNome = pesNome;
    }

    public String getSeMatricula() {
        return seMatricula;
    }

    public void setSeMatricula(String seMatricula) {
        this.seMatricula = seMatricula;
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
}