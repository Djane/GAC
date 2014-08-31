package br.com.sw2.gac.vo;

import java.math.BigDecimal;

/**
 * <b>Descrição: Classe que representa os dados do relatorio de dispositivos por estado.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class DispositivoEstadoVO {

    /** Descrição do tipo de dispositivo. */
    private String tipo;

    /** Descrição do estado do dispositivo. */
    private String estado;

    /** quantidade de dispositivos. */
    private Integer quantidade;

    /** Representação em porcentagem da quantidade de dispositivos. */
    private BigDecimal porcentagem;

    /**
     * Construtor Padrao Instancia um novo objeto DispositivoEstadoVO.
     */
    public DispositivoEstadoVO() {
        super();
    }

    /**
     * Construtor Padrao Instancia um novo objeto DispositivoEstadoVO.
     * @param tipo the tipo
     * @param estado the estado
     * @param quantidade the quantidade
     * @param porcentagem the porcentagem
     */
    public DispositivoEstadoVO(String tipo, String estado, Integer quantidade,
            BigDecimal porcentagem) {
        super();
        this.tipo = tipo;
        this.estado = estado;
        this.quantidade = quantidade;
        this.porcentagem = porcentagem;
    }

    /**
     * Nome: getTipo Recupera o valor do atributo 'tipo'.
     * @return valor do atributo 'tipo'
     * @see
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Nome: setTipo Registra o valor do atributo 'tipo'.
     * @param tipo valor do atributo tipo
     * @see
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

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
