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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "tbldispositivo")
@NamedQueries({ @NamedQuery(name = "Dispositivo.findAll", query = "SELECT d FROM Dispositivo d") })
public class Dispositivo implements Serializable {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** Atributo id dispositivo. */
    @Id
    @Basic(optional = false)
    @Column(name = "idDispositivo")
    private String idDispositivo;

    /** Atributo tp dispositivo. */
    @Column(name = "tpDispositivo")
    private Integer tpDispositivo;

    /** Atributo dta fabrica. */
    @Column(name = "dtaFabrica")
    @Temporal(TemporalType.DATE)
    private Date dtaFabrica;

    /** Atributo dta entrada. */
    @Column(name = "dtaEntrada")
    @Temporal(TemporalType.DATE)
    private Date dtaEntrada;

    /** Atributo tp estado. */
    @Basic(optional = false)
    @Column(name = "tpEstado")
    private Integer tpEstado;

    /** Atributo dta proxima manut. */
    @Column(name = "dtaProximaManut")
    @Temporal(TemporalType.DATE)
    private Date dtaProximaManut;

    /** Atributo dta sucata. */
    @Column(name = "dtaSucata")
    @Temporal(TemporalType.DATE)
    private Date dtaSucata;

    /** Atributo local. */
    @Column(name = "local")
    private Integer local;

    /** Atributo cliente list. */
    @JoinTable(name = "tblclientexdispositivo", joinColumns = { @JoinColumn(name = "idDispositivo", referencedColumnName = "idDispositivo") },
                inverseJoinColumns = { @JoinColumn(name = "nmCPFCliente", referencedColumnName = "nmCPFCliente") })
    @ManyToMany
    private List<Cliente> clienteList;

    /** Atributo login. */
    @JoinColumn(name = "login", referencedColumnName = "login")
    @ManyToOne(optional = false)
    private Usuario login;

    /**
     * Construtor Padrao Instancia um novo objeto Dispositivo.
     */
    public Dispositivo() {
    }

    /**
     * Construtor Padrao Instancia um novo objeto Dispositivo.
     * @param idDispositivo the id dispositivo
     */
    public Dispositivo(String idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    /**
     * Construtor Padrao Instancia um novo objeto Dispositivo.
     * @param idDispositivo the id dispositivo
     * @param tpEstado the tp estado
     */
    public Dispositivo(String idDispositivo, Integer tpEstado) {
        this.idDispositivo = idDispositivo;
        this.tpEstado = tpEstado;
    }

    /**
     * Nome: getIdDispositivo Recupera o valor do atributo 'idDispositivo'.
     * @return valor do atributo 'idDispositivo'
     * @see
     */
    public String getIdDispositivo() {
        return idDispositivo;
    }

    /**
     * Nome: setIdDispositivo Registra o valor do atributo 'idDispositivo'.
     * @param idDispositivo valor do atributo id dispositivo
     * @see
     */
    public void setIdDispositivo(String idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    /**
     * Nome: getTpDispositivo Recupera o valor do atributo 'tpDispositivo'.
     * @return valor do atributo 'tpDispositivo'
     * @see
     */
    public Integer getTpDispositivo() {
        return tpDispositivo;
    }

    /**
     * Nome: setTpDispositivo Registra o valor do atributo 'tpDispositivo'.
     * @param tpDispositivo valor do atributo tp dispositivo
     * @see
     */
    public void setTpDispositivo(Integer tpDispositivo) {
        this.tpDispositivo = tpDispositivo;
    }

    /**
     * Nome: getDtaFabrica Recupera o valor do atributo 'dtaFabrica'.
     * @return valor do atributo 'dtaFabrica'
     * @see
     */
    public Date getDtaFabrica() {
        return dtaFabrica;
    }

    /**
     * Nome: setDtaFabrica Registra o valor do atributo 'dtaFabrica'.
     * @param dtaFabrica valor do atributo dta fabrica
     * @see
     */
    public void setDtaFabrica(Date dtaFabrica) {
        this.dtaFabrica = dtaFabrica;
    }

    /**
     * Nome: getDtaEntrada Recupera o valor do atributo 'dtaEntrada'.
     * @return valor do atributo 'dtaEntrada'
     * @see
     */
    public Date getDtaEntrada() {
        return dtaEntrada;
    }

    /**
     * Nome: setDtaEntrada Registra o valor do atributo 'dtaEntrada'.
     * @param dtaEntrada valor do atributo dta entrada
     * @see
     */
    public void setDtaEntrada(Date dtaEntrada) {
        this.dtaEntrada = dtaEntrada;
    }

    /**
     * Nome: getTpEstado Recupera o valor do atributo 'tpEstado'.
     * @return valor do atributo 'tpEstado'
     * @see
     */
    public Integer getTpEstado() {
        return tpEstado;
    }

    /**
     * Nome: setTpEstado Registra o valor do atributo 'tpEstado'.
     * @param tpEstado valor do atributo tp estado
     * @see
     */
    public void setTpEstado(Integer tpEstado) {
        this.tpEstado = tpEstado;
    }

    /**
     * Nome: getDtaProximaManut Recupera o valor do atributo 'dtaProximaManut'.
     * @return valor do atributo 'dtaProximaManut'
     * @see
     */
    public Date getDtaProximaManut() {
        return dtaProximaManut;
    }

    /**
     * Nome: setDtaProximaManut Registra o valor do atributo 'dtaProximaManut'.
     * @param dtaProximaManut valor do atributo dta proxima manut
     * @see
     */
    public void setDtaProximaManut(Date dtaProximaManut) {
        this.dtaProximaManut = dtaProximaManut;
    }

    /**
     * Nome: getDtaSucata Recupera o valor do atributo 'dtaSucata'.
     * @return valor do atributo 'dtaSucata'
     * @see
     */
    public Date getDtaSucata() {
        return dtaSucata;
    }

    /**
     * Nome: setDtaSucata Registra o valor do atributo 'dtaSucata'.
     * @param dtaSucata valor do atributo dta sucata
     * @see
     */
    public void setDtaSucata(Date dtaSucata) {
        this.dtaSucata = dtaSucata;
    }

    /**
     * Nome: getLocal Recupera o valor do atributo 'local'.
     * @return valor do atributo 'local'
     * @see
     */
    public Integer getLocal() {
        return local;
    }

    /**
     * Nome: setLocal Registra o valor do atributo 'local'.
     * @param local valor do atributo local
     * @see
     */
    public void setLocal(Integer local) {
        this.local = local;
    }

    /**
     * Nome: getClienteList Recupera o valor do atributo 'clienteList'.
     * @return valor do atributo 'clienteList'
     * @see
     */
    public List<Cliente> getClienteList() {
        return clienteList;
    }

    /**
     * Nome: setClienteList Registra o valor do atributo 'clienteList'.
     * @param clienteList valor do atributo cliente list
     * @see
     */
    public void setClienteList(List<Cliente> clienteList) {
        this.clienteList = clienteList;
    }

    /**
     * Nome: getLogin Recupera o valor do atributo 'login'.
     * @return valor do atributo 'login'
     * @see
     */
    public Usuario getLogin() {
        return login;
    }

    /**
     * Nome: setLogin Registra o valor do atributo 'login'.
     * @param login valor do atributo login
     * @see
     */
    public void setLogin(Usuario login) {
        this.login = login;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int hash = 0;
        if (idDispositivo != null) {
            hash += idDispositivo.hashCode();
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

        if (!(object instanceof Dispositivo)) {
            return false;
        }
        Dispositivo other = (Dispositivo) object;
        if ((this.idDispositivo == null && other.idDispositivo != null)
                || (this.idDispositivo != null && !this.idDispositivo.equals(other.idDispositivo))) {
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
        return "br.com.sw2.gac.modelo.Dispositivo[ idDispositivo=" + idDispositivo + " ]";
    }

}
