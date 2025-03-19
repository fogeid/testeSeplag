package io.github.fogeid.testeSeplag.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_pessoa")
public class Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pesId;

    private String pesNome;
    private LocalDate pesDataNascimento;
    private String pesSexo;
    private String pesMae;
    private String pesPai;

    @OneToMany(mappedBy = "pessoa")
    private List<FotoPessoa> fotos;

    @OneToMany(mappedBy = "pessoa")
    private List<PessoaEndereco> enderecos;

    @OneToMany(mappedBy = "pessoa")
    private List<Lotacao> lotacoes;

    @OneToOne(mappedBy = "pessoa")
    private ServidorTemporario servidorTemporario;

    @OneToOne(mappedBy = "pessoa")
    private ServidorEfetivo servidorEfetivo;

    public Pessoa() {

    }

    public Pessoa(Long pesId, String pesNome, LocalDate pesDataNascimento, String pesSexo, String pesMae, String pesPai, List<FotoPessoa> fotos, List<PessoaEndereco> enderecos, List<Lotacao> lotacoes, ServidorTemporario servidorTemporario, ServidorEfetivo servidorEfetivo) {
        this.pesId = pesId;
        this.pesNome = pesNome;
        this.pesDataNascimento = pesDataNascimento;
        this.pesSexo = pesSexo;
        this.pesMae = pesMae;
        this.pesPai = pesPai;
        this.fotos = fotos;
        this.enderecos = enderecos;
        this.lotacoes = lotacoes;
        this.servidorTemporario = servidorTemporario;
        this.servidorEfetivo = servidorEfetivo;
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

    public List<FotoPessoa> getFotos() {
        return fotos;
    }

    public void setFotos(List<FotoPessoa> fotos) {
        this.fotos = fotos;
    }

    public List<PessoaEndereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<PessoaEndereco> enderecos) {
        this.enderecos = enderecos;
    }

    public List<Lotacao> getLotacoes() {
        return lotacoes;
    }

    public void setLotacoes(List<Lotacao> lotacoes) {
        this.lotacoes = lotacoes;
    }

    public ServidorTemporario getServidorTemporario() {
        return servidorTemporario;
    }

    public void setServidorTemporario(ServidorTemporario servidorTemporario) {
        this.servidorTemporario = servidorTemporario;
    }

    public ServidorEfetivo getServidorEfetivo() {
        return servidorEfetivo;
    }

    public void setServidorEfetivo(ServidorEfetivo servidorEfetivo) {
        this.servidorEfetivo = servidorEfetivo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pessoa pessoa)) return false;
        return pesId.equals(pessoa.pesId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pesId);
    }
}

