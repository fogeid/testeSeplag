package io.github.fogeid.testeSeplag.dto.servidorTemporario;

import io.github.fogeid.testeSeplag.entities.ServidorTemporario;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.time.LocalDate;

public class ServidorTemporarioDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Long pesId;

    private Long pessoa;

    private LocalDate stDataAdmissao;

    private LocalDate stDataDemissao;

    public ServidorTemporarioDTO() {

    }

    public ServidorTemporarioDTO(Long pesId, Long pessoa, LocalDate stDataAdmissao, LocalDate stDataDemissao) {
        this.pesId = pesId;
        this.pessoa = pessoa;
        this.stDataAdmissao = stDataAdmissao;
        this.stDataDemissao = stDataDemissao;
    }

    public  ServidorTemporarioDTO(ServidorTemporario servidorTemporario) {
        this.pesId = servidorTemporario.getPesId();
        this.pessoa = servidorTemporario.getPessoa() != null ? servidorTemporario.getPessoa().getPesId() : null;
        this.stDataAdmissao = servidorTemporario.getStDataAdmissao();
        this.stDataDemissao = servidorTemporario.getStDataDemissao();
    }

    public Long getPesId() {
        return pesId;
    }

    public void setPesId(Long pesId) {
        this.pesId = pesId;
    }

    public Long getPessoa() {
        return pessoa;
    }

    public void setPessoa(Long pessoa) {
        this.pessoa = pessoa;
    }

    public LocalDate getStDataAdmissao() {
        return stDataAdmissao;
    }

    public void setStDataAdmissao(LocalDate stDataAdmissao) {
        this.stDataAdmissao = stDataAdmissao;
    }

    public LocalDate getStDataDemissao() {
        return stDataDemissao;
    }

    public void setStDataDemissao(LocalDate stDataDemissao) {
        this.stDataDemissao = stDataDemissao;
    }
}
