/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sw2.gac.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * <b>Descrição:</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@Entity
@Table(name = "tblhistdispositivo")
@NamedQueries({ @NamedQuery(name = "Tblhistdispositivo.findAll", query = "SELECT t FROM HistDispositivo t") })
public class HistDispositivo implements Serializable {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** Atributo tblhistdispositivo pk. */
    @EmbeddedId
    private HistDispositivoPK tblhistdispositivoPK;

    /** Atributo cd estado anterior. */
    @Column(name = "cdEstadoAnterior")
    private Integer cdEstadoAnterior;

    /** Atributo dispositivo. */
    @JoinColumn(name = "idDispositivo", referencedColumnName = "idDispositivo", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Dispositivo dispositivo;

    /** Atributo login. */
    private String login;

	/**
     * Construtor Padrao Instancia um novo objeto Tblhistdispositivo.
     */
    public HistDispositivo() {
    }

    /**
     * Construtor Padrao Instancia um novo objeto Tblhistdispositivo.
     * @param tblhistdispositivoPK the tblhistdispositivo pk
     */
    public HistDispositivo(HistDispositivoPK tblhistdispositivoPK) {
        this.tblhistdispositivoPK = tblhistdispositivoPK;
    }

    /**
     * Construtor Padrao Instancia um novo objeto Tblhistdispositivo.
     * @param dthrMudaEstado the dthr muda estado
     * @param idDispositivo the id dispositivo
     */
    public HistDispositivo(Date dthrMudaEstado, String idDispositivo) {
        this.tblhistdispositivoPK = new HistDispositivoPK(dthrMudaEstado, idDispositivo);
    }

    /**
     * Nome: getTblhistdispositivoPK Recupera o valor do atributo 'tblhistdispositivoPK'.
     * @return valor do atributo 'tblhistdispositivoPK'
     * @see
     */
    public HistDispositivoPK getTblhistdispositivoPK() {
        return tblhistdispositivoPK;
    }

    /**
     * Nome: setTblhistdispositivoPK Registra o valor do atributo 'tblhistdispositivoPK'.
     * @param tblhistdispositivoPK valor do atributo tblhistdispositivo pk
     * @see
     */
    public void setTblhistdispositivoPK(HistDispositivoPK tblhistdispositivoPK) {
        this.tblhistdispositivoPK = tblhistdispositivoPK;
    }

    /**
     * Nome: getCdEstadoAnterior Recupera o valor do atributo 'cdEstadoAnterior'.
     * @return valor do atributo 'cdEstadoAnterior'
     * @see
     */
    public Integer getCdEstadoAnterior() {
        return cdEstadoAnterior;
    }

    /**
     * Nome: setCdEstadoAnterior Registra o valor do atributo 'cdEstadoAnterior'.
     * @param cdEstadoAnterior valor do atributo cd estado anterior
     * @see
     */
    public void setCdEstadoAnterior(Integer cdEstadoAnterior) {
        this.cdEstadoAnterior = cdEstadoAnterior;
    }

    /**
     * Nome: getDispositivo Recupera o valor do atributo 'dispositivo'.
     * @return valor do atributo 'dispositivo'
     * @see
     */
    public Dispositivo getDispositivo() {
        return dispositivo;
    }

    /**
     * Nome: setDispositivo Registra o valor do atributo 'dispositivo'.
     * @param dispositivo valor do atributo dispositivo
     * @see
     */
    public void setDispositivo(Dispositivo dispositivo) {
        this.dispositivo = dispositivo;
    }

    public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	/*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tblhistdispositivoPK != null ? tblhistdispositivoPK.hashCode() : 0);
        return hash;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof HistDispositivo)) {
            return false;
        }
        HistDispositivo other = (HistDispositivo) object;
        if ((this.tblhistdispositivoPK == null && other.tblhistdispositivoPK != null)
                || (this.tblhistdispositivoPK != null && !this.tblhistdispositivoPK
                        .equals(other.tblhistdispositivoPK))) {
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
        return "br.com.sw2.gac.modelo.Tblhistdispositivo[ tblhistdispositivoPK="
                + tblhistdispositivoPK + " ]";
    }

}
