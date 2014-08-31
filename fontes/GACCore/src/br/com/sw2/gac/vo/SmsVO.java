package br.com.sw2.gac.vo;

import java.util.Date;

/**
 * <b>Descrição: Objeto que representa uma mensagem SMS.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class SmsVO {

    /** Atributo id sms. */
    private Integer idSms;

    /** Atributo tp mensagem. */
    private String titulo;

    /** Atributo ds mensagem. */
    private String texto;

    /** Atributo dt inicio validade. */
    private Date dtInicioValidade;

    /** Atributo dt termino validade. */
    private Date dtTerminoValidade;

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
     * Nome: getTitulo Recupera o valor do atributo 'titulo'.
     * @return valor do atributo 'titulo'
     * @see
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Nome: setTitulo Registra o valor do atributo 'titulo'.
     * @param titulo valor do atributo titulo
     * @see
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Nome: getTexto Recupera o valor do atributo 'texto'.
     * @return valor do atributo 'texto'
     * @see
     */
    public String getTexto() {
        return texto;
    }

    /**
     * Nome: setTexto Registra o valor do atributo 'texto'.
     * @param texto valor do atributo texto
     * @see
     */
    public void setTexto(String texto) {
        this.texto = texto;
    }

    /**
     * Nome: getDtInicioValidade Recupera o valor do atributo 'dtInicioValidade'.
     * @return valor do atributo 'dtInicioValidade'
     * @see
     */
    public Date getDtInicioValidade() {
        return dtInicioValidade;
    }

    /**
     * Nome: setDtInicioValidade Registra o valor do atributo 'dtInicioValidade'.
     * @param dtInicioValidade valor do atributo dt inicio validade
     * @see
     */
    public void setDtInicioValidade(Date dtInicioValidade) {
        this.dtInicioValidade = dtInicioValidade;
    }

    /**
     * Nome: getDtTerminoValidade Recupera o valor do atributo 'dtTerminoValidade'.
     * @return valor do atributo 'dtTerminoValidade'
     * @see
     */
    public Date getDtTerminoValidade() {
        return dtTerminoValidade;
    }

    /**
     * Nome: setDtTerminoValidade Registra o valor do atributo 'dtTerminoValidade'.
     * @param dtTerminoValidade valor do atributo dt termino validade
     * @see
     */
    public void setDtTerminoValidade(Date dtTerminoValidade) {
        this.dtTerminoValidade = dtTerminoValidade;
    }

}
