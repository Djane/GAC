package br.com.sw2.gac.vo;

import java.math.BigDecimal;

public class DispositivoEstadoVO {

    private String estado;
    private Integer quantidade;
    private BigDecimal porcentagem;
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public Integer getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
    public BigDecimal getPorcentagem() {
        return porcentagem;
    }
    public void setPorcentagem(BigDecimal porcentagem) {
        this.porcentagem = porcentagem;
    }
}
