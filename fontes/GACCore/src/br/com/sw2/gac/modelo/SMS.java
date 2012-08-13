/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sw2.gac.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * <b>Descrição:</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@Entity
@Table(name = "tblsms")
@NamedQueries({ @NamedQuery(name = "Sms.findAll", query = "SELECT s FROM SMS s") })
public class SMS implements Serializable {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** Atributo id sms. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idSMS")
    private Integer idSMS;

    /** Atributo tp mensagem. */
    @Basic(optional = false)
    @Column(name = "tpMensagem")
    private String tpMensagem;

    /** Atributo ds mensagem. */
    @Basic(optional = false)
    @Column(name = "dsMensagem")
    private String dsMensagem;

    /** Atributo id momento. */
    @Column(name = "idMomento")
    private Integer idMomento;

    /** Atributo dt inicio validade. */
    @Basic(optional = false)
    @Column(name = "dtInicioValidade")
    @Temporal(TemporalType.DATE)
    private Date dtInicioValidade;

    /** Atributo dt termino validade. */
    @Column(name = "dtTerminoValidade")
    @Temporal(TemporalType.DATE)
    private Date dtTerminoValidade;

    /** Atributo acionamento list. */
    @OneToMany(mappedBy = "idSMS")
    private List<Acionamento> acionamentoList;

    /**
     * Construtor Padrao Instancia um novo objeto Sms.
     */
    public SMS() {
    }

    /**
     * Construtor Padrao Instancia um novo objeto Sms.
     * @param idSMS the id sms
     */
    public SMS(Integer idSMS) {
        this.idSMS = idSMS;
    }

    /**
     * Construtor Padrao Instancia um novo objeto Sms.
     * @param idSMS the id sms
     * @param tpMensagem the tp mensagem
     * @param dsMensagem the ds mensagem
     * @param dtInicioValidade the dt inicio validade
     */
    public SMS(Integer idSMS, String tpMensagem, String dsMensagem, Date dtInicioValidade) {
        this.idSMS = idSMS;
        this.tpMensagem = tpMensagem;
        this.dsMensagem = dsMensagem;
        this.dtInicioValidade = dtInicioValidade;
    }

    /**
     * Nome: getIdSMS Recupera o valor do atributo 'idSMS'.
     * @return valor do atributo 'idSMS'
     * @see
     */
    public Integer getIdSMS() {
        return idSMS;
    }

    /**
     * Nome: setIdSMS Registra o valor do atributo 'idSMS'.
     * @param idSMS valor do atributo id sms
     * @see
     */
    public void setIdSMS(Integer idSMS) {
        this.idSMS = idSMS;
    }

    /**
     * Nome: getTpMensagem Recupera o valor do atributo 'tpMensagem'.
     * @return valor do atributo 'tpMensagem'
     * @see
     */
    public String getTpMensagem() {
        return tpMensagem;
    }

    /**
     * Nome: setTpMensagem Registra o valor do atributo 'tpMensagem'.
     * @param tpMensagem valor do atributo tp mensagem
     * @see
     */
    public void setTpMensagem(String tpMensagem) {
        this.tpMensagem = tpMensagem;
    }

    /**
     * Nome: getDsMensagem Recupera o valor do atributo 'dsMensagem'.
     * @return valor do atributo 'dsMensagem'
     * @see
     */
    public String getDsMensagem() {
        return dsMensagem;
    }

    /**
     * Nome: setDsMensagem Registra o valor do atributo 'dsMensagem'.
     * @param dsMensagem valor do atributo ds mensagem
     * @see
     */
    public void setDsMensagem(String dsMensagem) {
        this.dsMensagem = dsMensagem;
    }

    /**
     * Nome: getIdMomento Recupera o valor do atributo 'idMomento'.
     * @return valor do atributo 'idMomento'
     * @see
     */
    public Integer getIdMomento() {
        return idMomento;
    }

    /**
     * Nome: setIdMomento Registra o valor do atributo 'idMomento'.
     * @param idMomento valor do atributo id momento
     * @see
     */
    public void setIdMomento(Integer idMomento) {
        this.idMomento = idMomento;
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

    /**
     * Nome: getAcionamentoList Recupera o valor do atributo 'acionamentoList'.
     * @return valor do atributo 'acionamentoList'
     * @see
     */
    public List<Acionamento> getAcionamentoList() {
        return acionamentoList;
    }

    /**
     * Nome: setAcionamentoList Registra o valor do atributo 'acionamentoList'.
     * @param acionamentoList valor do atributo acionamento list
     * @see
     */
    public void setAcionamentoList(List<Acionamento> acionamentoList) {
        this.acionamentoList = acionamentoList;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int hash = 0;

        if (idSMS != null) {
            hash += idSMS.hashCode();
        } else {
            hash += 0;
        }
        return hash;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object object) {

        if (!(object instanceof SMS)) {
            return false;
        }
        SMS other = (SMS) object;
        if ((this.idSMS == null && other.idSMS != null)
                || (this.idSMS != null && !this.idSMS.equals(other.idSMS))) {
            return false;
        }
        return true;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "br.com.sw2.gac.modelo.Sms[ idSMS=" + idSMS + " ]";
    }

}
