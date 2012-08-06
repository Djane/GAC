package br.com.sw2.gac.vo;

import java.util.Date;

/**
 * <b>Descrição:</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class OcorrenciaVO {

    /** Atributo id ocorrencia. */
    private Integer idOcorrencia;

    /** Atributo acao ocorrencia. */
    private String acaoOcorrencia;

    /** Atributo conclusao. */
    private String conclusao;

    /** Atributo dta atend. */
    private Date dtaAtend;

    /** Atributo dta hora abertura. */
    private Date dtaHoraAbertura;

    /** Atributo dta hora fechamento. */
    private Date dtaHoraFechamento;

    /** Atributo dta hora inicio. */
    private Date dtaHoraInicio;

    /** Atributo dta hora termino. */
    private Date dtaHoraTermino;

    /** Atributo recl ocorrencia. */
    private String reclOcorrencia;

    /** Atributo status ocorrencia. */
    private String statusOcorrencia;

    /** Atributo tp ocorrencia. */
    private int tpOcorrencia;

    /** Atributo usuario. */
    private UsuarioVO usuario;

    public Integer getIdOcorrencia() {
        return idOcorrencia;
    }

    public void setIdOcorrencia(Integer idOcorrencia) {
        this.idOcorrencia = idOcorrencia;
    }

    public String getAcaoOcorrencia() {
        return acaoOcorrencia;
    }

    public void setAcaoOcorrencia(String acaoOcorrencia) {
        this.acaoOcorrencia = acaoOcorrencia;
    }

    public String getConclusao() {
        return conclusao;
    }

    public void setConclusao(String conclusao) {
        this.conclusao = conclusao;
    }

    public Date getDtaAtend() {
        return dtaAtend;
    }

    public void setDtaAtend(Date dtaAtend) {
        this.dtaAtend = dtaAtend;
    }

    public Date getDtaHoraAbertura() {
        return dtaHoraAbertura;
    }

    public void setDtaHoraAbertura(Date dtaHoraAbertura) {
        this.dtaHoraAbertura = dtaHoraAbertura;
    }

    public Date getDtaHoraFechamento() {
        return dtaHoraFechamento;
    }

    public void setDtaHoraFechamento(Date dtaHoraFechamento) {
        this.dtaHoraFechamento = dtaHoraFechamento;
    }

    public Date getDtaHoraInicio() {
        return dtaHoraInicio;
    }

    public void setDtaHoraInicio(Date dtaHoraInicio) {
        this.dtaHoraInicio = dtaHoraInicio;
    }

    public Date getDtaHoraTermino() {
        return dtaHoraTermino;
    }

    public void setDtaHoraTermino(Date dtaHoraTermino) {
        this.dtaHoraTermino = dtaHoraTermino;
    }

    public String getReclOcorrencia() {
        return reclOcorrencia;
    }

    public void setReclOcorrencia(String reclOcorrencia) {
        this.reclOcorrencia = reclOcorrencia;
    }

    public String getStatusOcorrencia() {
        return statusOcorrencia;
    }

    public void setStatusOcorrencia(String statusOcorrencia) {
        this.statusOcorrencia = statusOcorrencia;
    }

    public int getTpOcorrencia() {
        return tpOcorrencia;
    }

    public void setTpOcorrencia(int tpOcorrencia) {
        this.tpOcorrencia = tpOcorrencia;
    }

    public UsuarioVO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioVO usuario) {
        this.usuario = usuario;
    }


}
