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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "tblmonitoramento")
@NamedQueries({ @NamedQuery(name = "Monitoramento.findAll", query = "SELECT m FROM Monitoramento m") })
public class Monitoramento implements Serializable {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** Atributo dta inicio monitora. */
    @Id
    @Basic(optional = false)
    @Column(name = "dtaInicioMonitora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtaInicioMonitora;

    /** Atributo tp monitora. */
    @Column(name = "tpMonitora")
    private Integer tpMonitora;

    /** Atributo acontecimento. */
    @Column(name = "acontecimento")
    private Integer acontecimento;

    /** Atributo nm cpf cliente. */
    @JoinColumn(name = "nmCPFCliente", referencedColumnName = "nmCPFCliente")
    @ManyToOne(optional = false)
    private Cliente nmCPFCliente;

    /**
     * Construtor Padrao Instancia um novo objeto Monitoramento.
     */
    public Monitoramento() {
    }

    /**
     * Construtor Padrao Instancia um novo objeto Monitoramento.
     * @param dtaInicioMonitora the dta inicio monitora
     */
    public Monitoramento(Date dtaInicioMonitora) {
        this.dtaInicioMonitora = dtaInicioMonitora;
    }

    /**
     * Nome: getDtaInicioMonitora Recupera o valor do atributo 'dtaInicioMonitora'.
     * @return valor do atributo 'dtaInicioMonitora'
     * @see
     */
    public Date getDtaInicioMonitora() {
        return dtaInicioMonitora;
    }

    /**
     * Nome: setDtaInicioMonitora Registra o valor do atributo 'dtaInicioMonitora'.
     * @param dtaInicioMonitora valor do atributo dta inicio monitora
     * @see
     */
    public void setDtaInicioMonitora(Date dtaInicioMonitora) {
        this.dtaInicioMonitora = dtaInicioMonitora;
    }

    /**
     * Nome: getTpMonitora Recupera o valor do atributo 'tpMonitora'.
     * @return valor do atributo 'tpMonitora'
     * @see
     */
    public Integer getTpMonitora() {
        return tpMonitora;
    }

    /**
     * Nome: setTpMonitora Registra o valor do atributo 'tpMonitora'.
     * @param tpMonitora valor do atributo tp monitora
     * @see
     */
    public void setTpMonitora(Integer tpMonitora) {
        this.tpMonitora = tpMonitora;
    }

    /**
     * Nome: getAcontecimento Recupera o valor do atributo 'acontecimento'.
     * @return valor do atributo 'acontecimento'
     * @see
     */
    public Integer getAcontecimento() {
        return acontecimento;
    }

    /**
     * Nome: setAcontecimento Registra o valor do atributo 'acontecimento'.
     * @param acontecimento valor do atributo acontecimento
     * @see
     */
    public void setAcontecimento(Integer acontecimento) {
        this.acontecimento = acontecimento;
    }

    /**
     * Nome: getNmCPFCliente Recupera o valor do atributo 'nmCPFCliente'.
     * @return valor do atributo 'nmCPFCliente'
     * @see
     */
    public Cliente getNmCPFCliente() {
        return nmCPFCliente;
    }

    /**
     * Nome: setNmCPFCliente Registra o valor do atributo 'nmCPFCliente'.
     * @param nmCPFCliente valor do atributo nm cpf cliente
     * @see
     */
    public void setNmCPFCliente(Cliente nmCPFCliente) {
        this.nmCPFCliente = nmCPFCliente;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int hash = 0;

        if (dtaInicioMonitora != null) {
            hash += dtaInicioMonitora.hashCode();
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

        if (!(object instanceof Monitoramento)) {
            return false;
        }
        Monitoramento other = (Monitoramento) object;
        if ((this.dtaInicioMonitora == null && other.dtaInicioMonitora != null)
                || (this.dtaInicioMonitora != null && !this.dtaInicioMonitora
                        .equals(other.dtaInicioMonitora))) {
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
        return "br.com.sw2.gac.modelo.Monitoramento[ dtaInicioMonitora=" + dtaInicioMonitora + " ]";
    }

}
