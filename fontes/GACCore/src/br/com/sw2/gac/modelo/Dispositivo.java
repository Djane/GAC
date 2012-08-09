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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author rogerio
 */
@Entity
@Table(name = "tbldispositivo")
@NamedQueries({
    @NamedQuery(name = "Dispositivo.findAll", query = "SELECT d FROM Dispositivo d")})
public class Dispositivo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IdDispositivo")
    private String idDispositivo;
    @Column(name = "tpDispositivo")
    private Integer tpDispositivo;
    @Column(name = "dtaFabrica")
    @Temporal(TemporalType.DATE)
    private Date dtaFabrica;
    @Column(name = "dtaEntrada")
    @Temporal(TemporalType.DATE)
    private Date dtaEntrada;
    @Column(name = "tpEstado")
    private Integer tpEstado;
    @Column(name = "dtaProximaManut")
    @Temporal(TemporalType.DATE)
    private Date dtaProximaManut;
    @Column(name = "dtaSucata")
    @Temporal(TemporalType.DATE)
    private Date dtaSucata;
    @Column(name = "Local")
    private Character local;
    @ManyToMany(mappedBy = "dispositivoList")
    private List<Cliente> clienteList;
    @JoinColumn(name = "Login", referencedColumnName = "login")
    @ManyToOne(optional = false)
    private Usuario login;

    public Dispositivo() {
    }

    public Dispositivo(String idDispositivo) {
        this.idDispositivo = idDispositivo;
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

    public Integer getTpEstado() {
        return tpEstado;
    }

    public void setTpEstado(Integer tpEstado) {
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

    public Character getLocal() {
        return local;
    }

    public void setLocal(Character local) {
        this.local = local;
    }

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
