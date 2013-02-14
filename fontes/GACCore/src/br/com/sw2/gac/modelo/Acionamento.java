/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sw2.gac.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "tblacionamento")
@NamedQueries({ @NamedQuery(name = "Acionamento.findAll", query = "SELECT a FROM Acionamento a") })
public class Acionamento implements Serializable {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** Atributo id aciona. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdAciona")
    private Integer idAciona;

    /** Atributo dta hora aciona. */
    @Column(name = "dtaHoraAciona")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtaHoraAciona;

    /** Atributo acao pedida. */
    @Lob
    @Column(name = "acaoPedida")
    private String acaoPedida;

    /** Atributo dta hora inicio. */
    @Column(name = "dtaHoraInicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtaHoraInicio;

    /** Atributo dta hora final. */
    @Column(name = "dtaHoraFinal")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtaHoraFinal;

    /** Atributo texto livre sms. */
    @Column(name = "textoLivreSMS")
    private String textoLivreSMS;

    /** Atributo sucesso. */
    @Column(name = "sucesso")
    private Character sucesso;

    /** Atributo id ocorrencia. */
    @JoinColumn(name = "idOcorrencia", referencedColumnName = "idOcorrencia", updatable = false)
    @ManyToOne(optional = false)
    private Ocorrencia idOcorrencia;

    /** Atributo id contato. */
    @JoinColumn(name = "idContato", referencedColumnName = "idContato")
    @ManyToOne(optional = false)
    private Contato idContato;

    /** Atributo id sms. */
    @JoinColumn(name = "idSMS", referencedColumnName = "idSMS")
    @ManyToOne
    private SMS idSMS;

    /**
     * Construtor Padrao Instancia um novo objeto Acionamento.
     */
    public Acionamento() {
    }

    /**
     * Construtor Padrao Instancia um novo objeto Acionamento.
     * @param idAciona the id aciona
     */
    public Acionamento(Integer idAciona) {
        this.idAciona = idAciona;
    }

    /**
     * Nome: getIdAciona Recupera o valor do atributo 'idAciona'.
     * @return valor do atributo 'idAciona'
     * @see
     */
    public Integer getIdAciona() {
        return idAciona;
    }

    /**
     * Nome: setIdAciona Registra o valor do atributo 'idAciona'.
     * @param idAciona valor do atributo id aciona
     * @see
     */
    public void setIdAciona(Integer idAciona) {
        this.idAciona = idAciona;
    }

    /**
     * Nome: getDtaHoraAciona Recupera o valor do atributo 'dtaHoraAciona'.
     * @return valor do atributo 'dtaHoraAciona'
     * @see
     */
    public Date getDtaHoraAciona() {
        return dtaHoraAciona;
    }

    /**
     * Nome: setDtaHoraAciona Registra o valor do atributo 'dtaHoraAciona'.
     * @param dtaHoraAciona valor do atributo dta hora aciona
     * @see
     */
    public void setDtaHoraAciona(Date dtaHoraAciona) {
        this.dtaHoraAciona = dtaHoraAciona;
    }

    /**
     * Nome: getAcaoPedida Recupera o valor do atributo 'acaoPedida'.
     * @return valor do atributo 'acaoPedida'
     * @see
     */
    public String getAcaoPedida() {
        return acaoPedida;
    }

    /**
     * Nome: setAcaoPedida Registra o valor do atributo 'acaoPedida'.
     * @param acaoPedida valor do atributo acao pedida
     * @see
     */
    public void setAcaoPedida(String acaoPedida) {
        this.acaoPedida = acaoPedida;
    }

    /**
     * Nome: getDtaHoraInicio Recupera o valor do atributo 'dtaHoraInicio'.
     * @return valor do atributo 'dtaHoraInicio'
     * @see
     */
    public Date getDtaHoraInicio() {
        return dtaHoraInicio;
    }

    /**
     * Nome: setDtaHoraInicio Registra o valor do atributo 'dtaHoraInicio'.
     * @param dtaHoraInicio valor do atributo dta hora inicio
     * @see
     */
    public void setDtaHoraInicio(Date dtaHoraInicio) {
        this.dtaHoraInicio = dtaHoraInicio;
    }

    /**
     * Nome: getDtaHoraFinal Recupera o valor do atributo 'dtaHoraFinal'.
     * @return valor do atributo 'dtaHoraFinal'
     * @see
     */
    public Date getDtaHoraFinal() {
        return dtaHoraFinal;
    }

    /**
     * Nome: setDtaHoraFinal Registra o valor do atributo 'dtaHoraFinal'.
     * @param dtaHoraFinal valor do atributo dta hora final
     * @see
     */
    public void setDtaHoraFinal(Date dtaHoraFinal) {
        this.dtaHoraFinal = dtaHoraFinal;
    }

    /**
     * Nome: getTextoLivreSMS Recupera o valor do atributo 'textoLivreSMS'.
     * @return valor do atributo 'textoLivreSMS'
     * @see
     */
    public String getTextoLivreSMS() {
        return textoLivreSMS;
    }

    /**
     * Nome: setTextoLivreSMS Registra o valor do atributo 'textoLivreSMS'.
     * @param textoLivreSMS valor do atributo texto livre sms
     * @see
     */
    public void setTextoLivreSMS(String textoLivreSMS) {
        this.textoLivreSMS = textoLivreSMS;
    }

    /**
     * Nome: getSucesso Recupera o valor do atributo 'sucesso'.
     * @return valor do atributo 'sucesso'
     * @see
     */
    public Character getSucesso() {
        return sucesso;
    }

    /**
     * Nome: setSucesso Registra o valor do atributo 'sucesso'.
     * @param sucesso valor do atributo sucesso
     * @see
     */
    public void setSucesso(Character sucesso) {
        this.sucesso = sucesso;
    }

    /**
     * Nome: getIdOcorrencia Recupera o valor do atributo 'idOcorrencia'.
     * @return valor do atributo 'idOcorrencia'
     * @see
     */
    public Ocorrencia getIdOcorrencia() {
        return idOcorrencia;
    }

    /**
     * Nome: setIdOcorrencia Registra o valor do atributo 'idOcorrencia'.
     * @param idOcorrencia valor do atributo id ocorrencia
     * @see
     */
    public void setIdOcorrencia(Ocorrencia idOcorrencia) {
        this.idOcorrencia = idOcorrencia;
    }

    /**
     * Nome: getIdContato Recupera o valor do atributo 'idContato'.
     * @return valor do atributo 'idContato'
     * @see
     */
    public Contato getIdContato() {
        return idContato;
    }

    /**
     * Nome: setIdContato Registra o valor do atributo 'idContato'.
     * @param idContato valor do atributo id contato
     * @see
     */
    public void setIdContato(Contato idContato) {
        this.idContato = idContato;
    }

    /**
     * Nome: getIdSMS Recupera o valor do atributo 'idSMS'.
     * @return valor do atributo 'idSMS'
     * @see
     */
    public SMS getIdSMS() {
        return idSMS;
    }

    /**
     * Nome: setIdSMS Registra o valor do atributo 'idSMS'.
     * @param idSMS valor do atributo id sms
     * @see
     */
    public void setIdSMS(SMS idSMS) {
        this.idSMS = idSMS;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int hash = 0;

        if (idAciona != null) {
            hash += idAciona.hashCode();
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
        if (!(object instanceof Acionamento)) {
            return false;
        }
        Acionamento other = (Acionamento) object;
        if ((this.idAciona == null && other.idAciona != null)
                || (this.idAciona != null && !this.idAciona.equals(other.idAciona))) {
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
        return "br.com.sw2.gac.modelo.Acionamento[ idAciona=" + idAciona + " ]";
    }

}
