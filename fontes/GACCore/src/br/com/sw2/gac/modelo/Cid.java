/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sw2.gac.modelo;

import java.io.Serializable;
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

/**
 *
 * @author rogerio
 */
@Entity
@Table(name = "tblcid")
@NamedQueries({
    @NamedQuery(name = "Cid.findAll", query = "SELECT c FROM Cid c")})
public class Cid implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CdCID")
    private String cdCID;
    @Column(name = "nmDoenca")
    private String nmDoenca;
    @JoinTable(name = "tblpacxdoenca", joinColumns = {
        @JoinColumn(name = "CdCID", referencedColumnName = "CdCID")}, inverseJoinColumns = {
        @JoinColumn(name = "NmCPFCliente", referencedColumnName = "NmCPFCliente")})
    @ManyToMany
    private List<Cliente> clienteList;
    @JoinColumn(name = "cdTipoDoenca", referencedColumnName = "cdTipoDoenca")
    @ManyToOne(optional = false)
    private Tipodoenca cdTipoDoenca;

    public Cid() {
    }

    public Cid(String cdCID) {
        this.cdCID = cdCID;
    }

    public String getCdCID() {
        return cdCID;
    }

    public void setCdCID(String cdCID) {
        this.cdCID = cdCID;
    }

    public String getNmDoenca() {
        return nmDoenca;
    }

    public void setNmDoenca(String nmDoenca) {
        this.nmDoenca = nmDoenca;
    }

    public List<Cliente> getClienteList() {
        return clienteList;
    }

    public void setClienteList(List<Cliente> clienteList) {
        this.clienteList = clienteList;
    }

    public Tipodoenca getCdTipoDoenca() {
        return cdTipoDoenca;
    }

    public void setCdTipoDoenca(Tipodoenca cdTipoDoenca) {
        this.cdTipoDoenca = cdTipoDoenca;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cdCID != null ? cdCID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cid)) {
            return false;
        }
        Cid other = (Cid) object;
        if ((this.cdCID == null && other.cdCID != null) || (this.cdCID != null && !this.cdCID.equals(other.cdCID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.sw2.gac.modelo.Cid[ cdCID=" + cdCID + " ]";
    }
    
}
