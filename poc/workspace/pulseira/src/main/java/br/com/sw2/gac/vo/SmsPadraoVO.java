package br.com.sw2.gac.vo;


/**
 * <b>Descrição:</b> <br>.
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

}
