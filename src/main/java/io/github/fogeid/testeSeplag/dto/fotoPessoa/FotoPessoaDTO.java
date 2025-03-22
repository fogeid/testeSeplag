package io.github.fogeid.testeSeplag.dto.fotoPessoa;

import io.github.fogeid.testeSeplag.entities.FotoPessoa;
import io.github.fogeid.testeSeplag.entities.Pessoa;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.time.LocalDate;

public class FotoPessoaDTO implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fpId;

    private LocalDate fpData;

    private String fpBucket;

    private String fpHash;

    private Long pessoa;

    public FotoPessoaDTO() {

    }

    public FotoPessoaDTO(Long fpId, LocalDate fpData, String fpBucket, String fpHash, Long pessoa) {
        this.fpId = fpId;
        this.fpData = fpData;
        this.fpBucket = fpBucket;
        this.fpHash = fpHash;
        this.pessoa = pessoa;
    }

    public FotoPessoaDTO(FotoPessoa fotoPessoa) {
        this.fpId = fotoPessoa.getFpId();
        this.fpData = fotoPessoa.getFpData();
        this.fpBucket = fotoPessoa.getFpBucket();
        this.fpHash = fotoPessoa.getFpHash();
        this.pessoa = fotoPessoa.getPessoa() != null ? fotoPessoa.getPessoa().getPesId() : null;
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

    public Long getPessoa() {
        return pessoa;
    }

    public void setPessoa(Long pessoa) {
        this.pessoa = pessoa;
    }
}
