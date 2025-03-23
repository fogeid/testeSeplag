package io.github.fogeid.testeSeplag.dto.endereco;

import io.github.fogeid.testeSeplag.dto.unidade.UnidadeDTO;
import io.github.fogeid.testeSeplag.entities.Endereco;
import io.github.fogeid.testeSeplag.entities.Unidade;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class EnderecoDTO implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long endId;
    private String endTipoLogradouro;
    private String endLogradouro;
    private Integer endNumero;
    private String endBairro;
    private Long cidId;
    private Set<Long> pessoas = new HashSet<>();
    private Set<Long> unidades = new HashSet<>();

    public EnderecoDTO() {
    }

    public EnderecoDTO(Long endId, String endTipoLogradouro, String endLogradouro, Integer endNumero, String endBairro, Long cidId) {
        this.endId = endId;
        this.endTipoLogradouro = endTipoLogradouro;
        this.endLogradouro = endLogradouro;
        this.endNumero = endNumero;
        this.endBairro = endBairro;
        this.cidId = cidId;
    }

    public EnderecoDTO(Endereco endereco) {
        this.endId = endereco.getEndId();
        this.endTipoLogradouro = endereco.getEndTipoLogradouro();
        this.endLogradouro = endereco.getEndLogradouro();
        this.endNumero = endereco.getEndNumero();
        this.endBairro = endereco.getEndBairro();
        this.cidId = endereco.getCidade() != null ? endereco.getCidade().getCidId() : null;
        this.pessoas = endereco.getPessoas().stream()
                .map(pessoa -> pessoa.getPesId())
                .collect(Collectors.toSet());
        this.unidades = endereco.getUnidades().stream()
                .map(unidade -> unidade.getUnidId())
                .collect(Collectors.toSet());
    }

    public EnderecoDTO(Endereco endereco, List<Unidade> unidades) {
        this(endereco);
        unidades.forEach(x -> this.unidades.add(new UnidadeDTO(x).getUnidId()));
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

    public String getEndLogradouro() {
        return endLogradouro;
    }

    public void setEndLogradouro(String endLogradouro) {
        this.endLogradouro = endLogradouro;
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

    public Set<Long> getPessoas() {
        return pessoas;
    }

    public void setPessoas(Set<Long> pessoaIds) {
        this.pessoas = pessoaIds;
    }

    public Set<Long> getUnidades() {
        return unidades;
    }

    public void setUnidades(Set<Long> unidadeIds) {
        this.unidades = unidadeIds;
    }
}