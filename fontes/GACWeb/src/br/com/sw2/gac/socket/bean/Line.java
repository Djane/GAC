package br.com.sw2.gac.socket.bean;

import br.com.sw2.gac.vo.AcionamentoVO;

/**
 * <b>Descrição:</b> <br>.
 *
 * @author: SW2
 * @version 1.0
 *
 * Copyright 2013 SmartAngel.
 */
public class Line {

    /** Atributo numero linha. */
    private Integer numeroLinha;

    /** Atributo status linha. */
    private Integer statusLinha;

    /** Atributo numero discado. */
    private String numeroDiscado;

    /** Atributo tipo ligacao. */
    private Integer tipoLigacao;

    /** Atributo acionamento. */
    private AcionamentoVO acionamento;

    /**
     * Nome: getNumeroLinha
     * Recupera o valor do atributo 'numeroLinha'.
     *
     * @return valor do atributo 'numeroLinha'
     * @see
     */
    public Integer getNumeroLinha() {
        return numeroLinha;
    }

    /**
     * Nome: setNumeroLinha
     * Registra o valor do atributo 'numeroLinha'.
     *
     * @param numeroLinha valor do atributo numero linha
     * @see
     */
    public void setNumeroLinha(Integer numeroLinha) {
        this.numeroLinha = numeroLinha;
    }

    /**
     * Nome: getStatusLinha
     * Recupera o valor do atributo 'statusLinha'.
     *
     * @return valor do atributo 'statusLinha'
     * @see
     */
    public Integer getStatusLinha() {
        return statusLinha;
    }

    /**
     * Nome: setStatusLinha
     * Registra o valor do atributo 'statusLinha'.
     *
     * @param statusLinha valor do atributo status linha
     * @see
     */
    public void setStatusLinha(Integer statusLinha) {
        this.statusLinha = statusLinha;
    }

    /**
     * Nome: getNumeroDiscado
     * Recupera o valor do atributo 'numeroDiscado'.
     *
     * @return valor do atributo 'numeroDiscado'
     * @see
     */
    public String getNumeroDiscado() {
        return numeroDiscado;
    }

    /**
     * Nome: setNumeroDiscado
     * Registra o valor do atributo 'numeroDiscado'.
     *
     * @param numeroDiscado valor do atributo numero discado
     * @see
     */
    public void setNumeroDiscado(String numeroDiscado) {
        this.numeroDiscado = numeroDiscado;
    }

    /**
     * Nome: getTipoLigacao
     * Recupera o valor do atributo 'tipoLigacao'.
     *
     * @return valor do atributo 'tipoLigacao'
     * @see
     */
    public Integer getTipoLigacao() {
        return tipoLigacao;
    }

    /**
     * Nome: setTipoLigacao
     * Registra o valor do atributo 'tipoLigacao'.
     *
     * @param tipoLigacao valor do atributo tipo ligacao
     * @see
     */
    public void setTipoLigacao(Integer tipoLigacao) {
        this.tipoLigacao = tipoLigacao;
    }

    /**
     * Nome: getAcionamento
     * Recupera o valor do atributo 'acionamento'.
     *
     * @return valor do atributo 'acionamento'
     * @see
     */
    public AcionamentoVO getAcionamento() {
        return acionamento;
    }

    /**
     * Nome: setAcionamento
     * Registra o valor do atributo 'acionamento'.
     *
     * @param acionamento valor do atributo acionamento
     * @see
     */
    public void setAcionamento(AcionamentoVO acionamento) {
        this.acionamento = acionamento;
    }
}
