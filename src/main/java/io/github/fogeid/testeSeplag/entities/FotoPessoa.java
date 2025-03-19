package io.github.fogeid.testeSeplag.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "tb_foto_pessoa")
public class FotoPessoa implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fpId;

    private LocalDate fpData;

    private String fpBucket;

    private String fpHash;

    @ManyToOne
    @JoinColumn(name = "pes_id", nullable = false)
    private Pessoa pessoa;

    public FotoPessoa() {

    }

    public FotoPessoa(Long fpId, LocalDate fpData, String fpBucket, String fpHash, Pessoa pessoa) {
        this.fpId = fpId;
        this.fpData = fpData;
        this.fpBucket = fpBucket;
        this.fpHash = fpHash;
        this.pessoa = pessoa;
    }

    public Long getFpId() {
        return fpId;
    }

    public void setFpId(Long fpId) {
        this.fpId = fpId;
    }

    public LocalDate getFpData() {
        return fpData;
    }

    public void setFpData(LocalDate fpData) {
        this.fpData = fpData;
    }

    public String getFpBucket() {
        return fpBucket;
    }

    public void setFpBucket(String fpBucket) {
        this.fpBucket = fpBucket;
    }

    public String getFpHash() {
        return fpHash;
    }

    public void setFpHash(String fpHash) {
        this.fpHash = fpHash;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FotoPessoa that)) return false;
        return fpId.equals(that.fpId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fpId);
    }
}
