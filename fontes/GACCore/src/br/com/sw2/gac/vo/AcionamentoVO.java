package br.com.sw2.gac.vo;

import java.util.Date;


/**
 * <b>Descrição:</b> <br>.
 *
 * @author: SW2
 * @version 1.0
 *
 * Copyright 2013 SmartAngel.
 */
public class AcionamentoVO {

    /** Atributo id dAcionamento. */
    private Integer idAcionamento;

    /** Atributo id ocorrencia. */
    private Integer idOcorrencia;

    /** Atributo id contato. */
    private Integer idContato;

    /** Atributo data hora do acionamento. */
    private Date dataHoraDoAcionamento;

    /** Atributo data hora inicio conversa. */
    private Date dataHoraInicioConversa;

    /** Atributo data hora final conversa. */
    private Date dataHoraFinalConversa;

    /** Atributo id sms padrao. */
    private Integer idSMSPadrao;

    /** Atributo text livre sms. */
    private String textLivreSMS;

    /** Atributo sucesso. */
    private boolean sucesso;

    public Integer getIdAcionamento() {
        return idAcionamento;
    }

    public void setIdAcionamento(Integer idAcionamento) {
        this.idAcionamento = idAcionamento;
    }

    public Integer getIdOcorrencia() {
        return idOcorrencia;
    }

    public void setIdOcorrencia(Integer idOcorrencia) {
        this.idOcorrencia = idOcorrencia;
    }

    public Integer getIdContato() {
        return idContato;
    }

    public void setIdContato(Integer idContato) {
        this.idContato = idContato;
    }

    public Date getDataHoraDoAcionamento() {
        return dataHoraDoAcionamento;
    }

    public void setDataHoraDoAcionamento(Date dataHoraDoAcionamento) {
        this.dataHoraDoAcionamento = dataHoraDoAcionamento;
    }

    public Date getDataHoraInicioConversa() {
        return dataHoraInicioConversa;
    }

    public void setDataHoraInicioConversa(Date dataHoraInicioConversa) {
        this.dataHoraInicioConversa = dataHoraInicioConversa;
    }

    public Date getDataHoraFinalConversa() {
        return dataHoraFinalConversa;
    }

    public void setDataHoraFinalConversa(Date dataHoraFinalConversa) {
        this.dataHoraFinalConversa = dataHoraFinalConversa;
    }

    public Integer getIdSMSPadrao() {
        return idSMSPadrao;
    }

    public void setIdSMSPadrao(Integer idSMSPadrao) {
        this.idSMSPadrao = idSMSPadrao;
    }

    public String getTextLivreSMS() {
        return textLivreSMS;
    }

    public void setTextLivreSMS(String textLivreSMS) {
        this.textLivreSMS = textLivreSMS;
    }

    public boolean isSucesso() {
        return sucesso;
    }

    public void setSucesso(boolean sucesso) {
        this.sucesso = sucesso;
    }

}
