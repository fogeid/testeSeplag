package io.github.fogeid.testeSeplag.dto.unidade;

import io.github.fogeid.testeSeplag.entities.Unidade;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class UnidadeDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long unidId;

    @NotBlank
    private String unidNome;

    @NotBlank
    private String unidSigla;
    private Set<Long> enderecoIds = new HashSet<>();

    public UnidadeDTO() {
    }

    public UnidadeDTO(Long unidId, String unidNome, String unidSigla) {
        this.unidId = unidId;
        this.unidNome = unidNome;
        this.unidSigla = unidSigla;
    }

    public UnidadeDTO(Unidade unidade) {
        this.unidId = unidade.getUnidId();
        this.unidNome = unidade.getUnidNome();
        this.unidSigla = unidade.getUnidSigla();
        this.enderecoIds = unidade.getEnderecos().stream()
                .map(endereco -> endereco.getEndId())
                .collect(Collectors.toSet());
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

    public Set<Long> getEnderecoIds() {
        return enderecoIds;
    }

    public void setEnderecoIds(Set<Long> enderecoIds) {
        this.enderecoIds = enderecoIds;
    }
}