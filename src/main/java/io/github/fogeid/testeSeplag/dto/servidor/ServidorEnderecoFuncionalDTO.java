package io.github.fogeid.testeSeplag.dto.servidor;

public class ServidorEnderecoFuncionalDTO {
    private String pesNome;
    private String endLogradouro;
    private Integer endNumero;
    private String endBairro;
    private String cidNome;

    public ServidorEnderecoFuncionalDTO(String pesNome, String endLogradouro, Integer endNumero, String endBairro, String cidNome) {
        this.pesNome = pesNome;
        this.endLogradouro = endLogradouro;
        this.endNumero = endNumero;
        this.endBairro = endBairro;
        this.cidNome = cidNome;
    }

    public String getPesNome() {
        return pesNome;
    }

    public void setPesNome(String pesNome) {
        this.pesNome = pesNome;
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

    public String getCidNome() {
        return cidNome;
    }

    public void setCidNome(String cidNome) {
        this.cidNome = cidNome;
    }
}
