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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author marcelo
 */
@Entity
@Table(name = "TblDispositivo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dispositivo.findAll", query = "SELECT d FROM Dispositivo d"),
    @NamedQuery(name = "Dispositivo.findByIdDispositivo", query = "SELECT d FROM Dispositivo d WHERE d.idDispositivo = :idDispositivo"),
    @NamedQuery(name = "Dispositivo.findByTpDispositivo", query = "SELECT d FROM Dispositivo d WHERE d.tpDispositivo = :tpDispositivo"),
    @NamedQuery(name = "Dispositivo.findByDtaFabrica", query = "SELECT d FROM Dispositivo d WHERE d.dtaFabrica = :dtaFabrica"),
    @NamedQuery(name = "Dispositivo.findByDtaEntrada", query = "SELECT d FROM Dispositivo d WHERE d.dtaEntrada = :dtaEntrada"),
    @NamedQuery(name = "Dispositivo.findByTpEstado", query = "SELECT d FROM Dispositivo d WHERE d.tpEstado = :tpEstado"),
    @NamedQuery(name = "Dispositivo.findByDtaProximaManut", query = "SELECT d FROM Dispositivo d WHERE d.dtaProximaManut = :dtaProximaManut"),
    @NamedQuery(name = "Dispositivo.findByDtaSucata", query = "SELECT d FROM Dispositivo d WHERE d.dtaSucata = :dtaSucata"),
    @NamedQuery(name = "Dispositivo.findByLocal", query = "SELECT d FROM Dispositivo d WHERE d.local = :local")})
public class Dispositivo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idDispositivo")
    private String idDispositivo;
    @Column(name = "tpDispositivo")
    private Integer tpDispositivo;
    @Column(name = "dtaFabrica")
    @Temporal(TemporalType.DATE)
    private Date dtaFabrica;
    @Column(name = "dtaEntrada")
    @Temporal(TemporalType.DATE)
    private Date dtaEntrada;
    @Basic(optional = false)
    @Column(name = "tpEstado")
    private int tpEstado;
    @Column(name = "dtaProximaManut")
    @Temporal(TemporalType.DATE)
    private Date dtaProximaManut;
    @Column(name = "dtaSucata")
    @Temporal(TemporalType.DATE)
    private Date dtaSucata;
    @Column(name = "local")
    private Integer local;
    @JoinTable(name = "TblClientexDispositivo", joinColumns = {
        @JoinColumn(name = "idDispositivo", referencedColumnName = "idDispositivo")}, inverseJoinColumns = {
        @JoinColumn(name = "nmCPFCliente", referencedColumnName = "nmCPFCliente")})
    @ManyToMany
    private List<Cliente> clienteList;
    @JoinColumn(name = "login", referencedColumnName = "login")
    @ManyToOne(optional = false)
    private Usuario login;

    public Dispositivo() {
    }

    public Dispositivo(String idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    public Dispositivo(String idDispositivo, int tpEstado) {
        this.idDispositivo = idDispositivo;
        this.tpEstado = tpEstado;
    }

    public String getIdDispositivo() {
        return idDispositivo;
    }

    public void setIdDispositivo(String idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    public Integer getTpDispositivo() {
        return tpDispositivo;
    }

    public void setTpDispositivo(Integer tpDispositivo) {
        this.tpDispositivo = tpDispositivo;
    }

    public Date getDtaFabrica() {
        return dtaFabrica;
    }

    public void setDtaFabrica(Date dtaFabrica) {
        this.dtaFabrica = dtaFabrica;
    }

    public Date getDtaEntrada() {
        return dtaEntrada;
    }

    public void setDtaEntrada(Date dtaEntrada) {
        this.dtaEntrada = dtaEntrada;
    }

    public int getTpEstado() {
        return tpEstado;
    }

    public void setTpEstado(int tpEstado) {
        this.tpEstado = tpEstado;
    }

    public Date getDtaProximaManut() {
        return dtaProximaManut;
    }

    public void setDtaProximaManut(Date dtaProximaManut) {
        this.dtaProximaManut = dtaProximaManut;
    }

    public Date getDtaSucata() {
        return dtaSucata;
    }

    public void setDtaSucata(Date dtaSucata) {
        this.dtaSucata = dtaSucata;
    }

    public Integer getLocal() {
        return local;
    }

    public void setLocal(Integer local) {
        this.local = local;
    }

    @XmlTransient
    public List<Cliente> getClienteList() {
        return clienteList;
    }

    public void setClienteList(List<Cliente> clienteList) {
        this.clienteList = clienteList;
    }

    public Usuario getLogin() {
        return login;
    }

    public void setLogin(Usuario login) {
        this.login = login;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDispositivo != null ? idDispositivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dispositivo)) {
            return false;
        }
        Dispositivo other = (Dispositivo) object;
        if ((this.idDispositivo == null && other.idDispositivo != null) || (this.idDispositivo != null && !this.idDispositivo.equals(other.idDispositivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.sw2.gac.modelo.Dispositivo[ idDispositivo=" + idDispositivo + " ]";
    }
    
}
