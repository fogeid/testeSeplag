package io.github.fogeid.testeSeplag.dto.servidorEfetivo;

public class ServidorEfetivoLotadoDTO {
    private String pesNome;
    private Integer pesIdade;
    private String unidadeLotacao;
    private String ftBucket;

    public ServidorEfetivoLotadoDTO(String pesNome, Integer pesIdade, String unidadeLotacao, String ftBucket) {
        this.pesNome = pesNome;
        this.pesIdade = pesIdade;
        this.unidadeLotacao = unidadeLotacao;
        this.ftBucket = ftBucket;
    }

    public String getPesNome() {
        return pesNome;
    }

    public void setPesNome(String pesNome) {
        this.pesNome = pesNome;
    }

    public Integer getPesIdade() {
        return pesIdade;
    }

    public void setPesIdade(Integer pesIdade) {
        this.pesIdade = pesIdade;
    }

    public String getUnidadeLotacao() {
        return unidadeLotacao;
    }

    public void setUnidadeLotacao(String unidadeLotacao) {
        this.unidadeLotacao = unidadeLotacao;
    }

    public String getFtBucket() {
        return ftBucket;
    }

    public void setFtBucket(String ftBucket) {
        this.ftBucket = ftBucket;
    }
}