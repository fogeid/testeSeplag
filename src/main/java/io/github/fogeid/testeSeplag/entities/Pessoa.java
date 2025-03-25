package io.github.fogeid.testeSeplag.entities;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

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

    @OneToMany(mappedBy = "pessoa", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<FotoPessoa> fotos = new ArrayList<>();;

    @ManyToMany
    @JoinTable(
            name = "tb_pessoa_endereco",
            joinColumns = @JoinColumn(name = "pes_id"),
            inverseJoinColumns = @JoinColumn(name = "end_id")
    )
    private Set<Endereco> enderecos = new HashSet<>();

    @OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL)
    private ServidorTemporario servidorTemporario;

    @OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL)
    private ServidorEfetivo servidorEfetivo;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
    private List<Lotacao> lotacoes = new ArrayList<>();

    public Pessoa() {
    }

    public Pessoa(Long pesId, String pesNome, LocalDate pesDataNascimento, String pesSexo, String pesMae, String pesPai, List<FotoPessoa> fotos, Set<Endereco> enderecos, ServidorTemporario servidorTemporario, ServidorEfetivo servidorEfetivo, List<Lotacao> lotacoes) {
        this.pesId = pesId;
        this.pesNome = pesNome;
        this.pesDataNascimento = pesDataNascimento;
        this.pesSexo = pesSexo;
        this.pesMae = pesMae;
        this.pesPai = pesPai;
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

    public Set<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(Set<Endereco> enderecos) {
        this.enderecos = enderecos;
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

    public List<Lotacao> getLotacoes() {
        return lotacoes;
    }

    public void setLotacoes(List<Lotacao> lotacoes) {
        this.lotacoes = lotacoes;
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

