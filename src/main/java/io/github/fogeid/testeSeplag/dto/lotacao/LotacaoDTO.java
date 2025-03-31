package io.github.fogeid.testeSeplag.dto.lotacao;

import io.github.fogeid.testeSeplag.entities.Lotacao;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.time.LocalDate;

public class LotacaoDTO implements Serializable {
    public static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lotId;

    @NotBlank
    private LocalDate lotDataLotacao;

    @NotBlank
    private LocalDate lotDataRemocao;

    @NotBlank
    private String lotPortaria;

    @NotBlank
    private Long pesId;

    @NotBlank
    private Long unidId;

    public LotacaoDTO() {
    }

    public LotacaoDTO(Long lotId, LocalDate lotDataLotacao, LocalDate lotDataRemocao, String lotPortaria, Long pesId, Long unidId) {
        this.lotId = lotId;
        this.lotDataLotacao = lotDataLotacao;
        this.lotDataRemocao = lotDataRemocao;
        this.lotPortaria = lotPortaria;
        this.pesId = pesId;
        this.unidId = unidId;
    }

    public LotacaoDTO(Lotacao lotacao) {
        this.lotId = lotacao.getLotId();
        this.lotDataLotacao = lotacao.getLotDataLotacao();
        this.lotDataRemocao = lotacao.getLotDataRemocao();
        this.lotPortaria = lotacao.getLotPortaria();
        this.pesId = lotacao.getPessoa() != null ? lotacao.getPessoa().getPesId() : null;
        this.unidId = lotacao.getUnidade() != null ? lotacao.getUnidade().getUnidId() : null;
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

    public Long getPesId() {
        return pesId;
    }

    public void setPesId(Long pesId) {
        this.pesId = pesId;
    }

    public Long getUnidId() {
        return unidId;
    }

    public void setUnidId(Long unidId) {
        this.unidId = unidId;
    }
}