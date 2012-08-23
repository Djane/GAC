package br.com.sw2.gac.vo;

import java.util.Date;


/**
 * <b>Descrição: Classe que representa uma mensagem SMS.</b> <br>.
 *
 * @author: SW2
 * @version 1.0
 *
 * Copyright 2012 SmartAngel.
 */
public class SmsPadraoVO {

    /** Atributo id sms. */
    private Integer idSms;

    /** Atributo titulo. */
    private String titulo;

    /** Atributo descricao. */
    private String descricao;

    /** Atributo dt inicio validade. */
    private Date dtInicioValidade;

    /** Atributo dt termino validade. */
    private Date dtTerminoValidade;

    /**
     * Nome: getTitulo Recupera o valor do atributo 'titulo'.
     * @return valor do atributo 'titulo'
     * @see
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Nome: setTitulo Registra o valor do atributo 'titulo'.
     * @param tituloMensagem valor do atributo titulo
     * @see
     */
    public void setTitulo(String tituloMensagem) {
        this.titulo = tituloMensagem;
    }

    /**
     * Nome: getDescricao Recupera o valor do atributo 'descricao'.
     * @return valor do atributo 'descricao'
     * @see
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Nome: setDescricao Registra o valor do atributo 'descricao'.
     * @param descricao valor do atributo descricao
     * @see
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Nome: getIdSms Recupera o valor do atributo 'idSms'.
     * @return valor do atributo 'idSms'
     * @see
     */
    public Integer getIdSms() {
        return idSms;
    }

    /**
     * Nome: setIdSms Registra o valor do atributo 'idSms'.
     * @param idSms valor do atributo id sms
     * @see
     */
    public void setIdSms(Integer idSms) {
        this.idSms = idSms;
    }

    /**
     * Nome: getDtInicioValidade
     * Recupera o valor do atributo 'dtInicioValidade'.
     *
     * @return valor do atributo 'dtInicioValidade'
     * @see
     */
    public Date getDtInicioValidade() {
        return dtInicioValidade;
    }

    /**
     * Nome: setDtInicioValidade
     * Registra o valor do atributo 'dtInicioValidade'.
     *
     * @param dtInicioValidade valor do atributo dt inicio validade
     * @see
     */
    public void setDtInicioValidade(Date dtInicioValidade) {
        this.dtInicioValidade = dtInicioValidade;
    }

    /**
     * Nome: getDtTerminoValidade
     * Recupera o valor do atributo 'dtTerminoValidade'.
     *
     * @return valor do atributo 'dtTerminoValidade'
     * @see
     */
    public Date getDtTerminoValidade() {
        return dtTerminoValidade;
    }

    /**
     * Nome: setDtTerminoValidade
     * Registra o valor do atributo 'dtTerminoValidade'.
     *
     * @param dtTerminoValidade valor do atributo dt termino validade
     * @see
     */
    public void setDtTerminoValidade(Date dtTerminoValidade) {
        this.dtTerminoValidade = dtTerminoValidade;
    }
}
