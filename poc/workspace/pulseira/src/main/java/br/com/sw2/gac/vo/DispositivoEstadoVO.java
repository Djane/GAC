package br.com.sw2.gac.vo;

import java.math.BigDecimal;

/**
 * <b>Descrição:</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class DispositivoEstadoVO {

    /** Atributo estado. */
    private String estado;

    /** Atributo quantidade. */
    private Integer quantidade;

    /** Atributo porcentagem. */
    private BigDecimal porcentagem;

    /**
     * Nome: getEstado Recupera o valor do atributo 'estado'.
     * @return valor do atributo 'estado'
     * @see
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Nome: setEstado Registra o valor do atributo 'estado'.
     * @param estado valor do atributo estado
     * @see
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Nome: getQuantidade Recupera o valor do atributo 'quantidade'.
     * @return valor do atributo 'quantidade'
     * @see
     */
    public Integer getQuantidade() {
        return quantidade;
    }

    /**
     * Nome: setQuantidade Registra o valor do atributo 'quantidade'.
     * @param quantidade valor do atributo quantidade
     * @see
     */
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * Nome: getPorcentagem Recupera o valor do atributo 'porcentagem'.
     * @return valor do atributo 'porcentagem'
     * @see
     */
    public BigDecimal getPorcentagem() {
        return porcentagem;
    }

    /**
     * Nome: setPorcentagem Registra o valor do atributo 'porcentagem'.
     * @param porcentagem valor do atributo porcentagem
     * @see
     */
    public void setPorcentagem(BigDecimal porcentagem) {
        this.porcentagem = porcentagem;
    }
}
