package io.github.fogeid.testeSeplag.dto.pessoa;

import io.github.fogeid.testeSeplag.dto.lotacao.LotacaoDTO;
import io.github.fogeid.testeSeplag.dto.endereco.EnderecoDTO;
import io.github.fogeid.testeSeplag.dto.fotoPessoa.FotoPessoaDTO;
import io.github.fogeid.testeSeplag.dto.servidorEfetivo.ServidorEfetivoDTO;
import io.github.fogeid.testeSeplag.dto.servidorTemporario.ServidorTemporarioDTO;
import io.github.fogeid.testeSeplag.entities.Pessoa;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PessoaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pesId;

    @NotBlank
    private String pesNome;

    @NotBlank
    private LocalDate pesDataNascimento;

    @NotBlank
    private String pesSexo;

    @NotBlank
    private String pesMae;

    @NotBlank
    private String pesPai;
    private List<FotoPessoaDTO> fotos = new ArrayList<>(); // Usar DTO em vez de entidade
    private Set<EnderecoDTO> enderecos = new HashSet<>(); // Usar DTO em vez de entidade
    private ServidorTemporarioDTO servidorTemporario; // Usar DTO em vez de entidade
    private ServidorEfetivoDTO servidorEfetivo; // Usar DTO em vez de entidade
    private List<LotacaoDTO> lotacoes = new ArrayList<>(); // Usar DTO em vez de entidade

    public PessoaDTO() {
    }

    public PessoaDTO(Long pesId, String pesNome, LocalDate pesDataNascimento, String pesSexo, String pesMae, String pesPai) {
        this.pesId = pesId;
        this.pesNome = pesNome;
        this.pesDataNascimento = pesDataNascimento;
        this.pesSexo = pesSexo;
        this.pesMae = pesMae;
        this.pesPai = pesPai;
    }

    public PessoaDTO(Pessoa pessoa) {
        this.pesId = pessoa.getPesId();
        this.pesNome = pessoa.getPesNome();
        this.pesDataNascimento = pessoa.getPesDataNascimento();
        this.pesSexo = pessoa.getPesSexo();
        this.pesMae = pessoa.getPesMae();
        this.pesPai = pessoa.getPesPai();
        this.fotos = pessoa.getFotos().stream()
                .map(foto -> new FotoPessoaDTO(foto))
                .collect(Collectors.toList());
        if (pessoa.getServidorTemporario() != null) {
            this.servidorTemporario = new ServidorTemporarioDTO(pessoa.getServidorTemporario());
        }
        if (pessoa.getServidorEfetivo() != null) {
            this.servidorEfetivo = new ServidorEfetivoDTO(pessoa.getServidorEfetivo());
        }
        this.enderecos = pessoa.getEnderecos().stream()
                .map(endereco -> new EnderecoDTO(endereco))
                .collect(Collectors.toSet());
        this.lotacoes = pessoa.getLotacoes().stream()
                .map(lotacao -> new LotacaoDTO(lotacao))
                .collect(Collectors.toList());
    }

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

    public LocalDate getPesDataNascimento() {
        return pesDataNascimento;
    }

    public void setPesDataNascimento(LocalDate pesDataNascimento) {
        this.pesDataNascimento = pesDataNascimento;
    }

    public String getPesSexo() {
        return pesSexo;
    }

    public void setPesSexo(String pesSexo) {
        this.pesSexo = pesSexo;
    }

    public String getPesMae() {
        return pesMae;
    }

    public void setPesMae(String pesMae) {
        this.pesMae = pesMae;
    }

    public String getPesPai() {
        return pesPai;
    }

    public void setPesPai(String pesPai) {
        this.pesPai = pesPai;
    }

    public List<FotoPessoaDTO> getFotos() {
        return fotos;
    }

    public void setFotos(List<FotoPessoaDTO> fotos) {
        this.fotos = fotos;
    }

    public Set<EnderecoDTO> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(Set<EnderecoDTO> enderecos) {
        this.enderecos = enderecos;
    }

    public ServidorTemporarioDTO getServidorTemporario() {
        return servidorTemporario;
    }

    public void setServidorTemporario(ServidorTemporarioDTO servidorTemporario) {
        this.servidorTemporario = servidorTemporario;
    }

    public ServidorEfetivoDTO getServidorEfetivo() {
        return servidorEfetivo;
    }

    public void setServidorEfetivo(ServidorEfetivoDTO servidorEfetivo) {
        this.servidorEfetivo = servidorEfetivo;
    }

    public List<LotacaoDTO> getLotacoes() {
        return lotacoes;
    }

    public void setLotacoes(List<LotacaoDTO> lotacoes) {
        this.lotacoes = lotacoes;
    }
}