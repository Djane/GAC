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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author marcelo
 */
@Entity
@Table(name = "TblCID")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CID.findAll", query = "SELECT c FROM CID c"),
    @NamedQuery(name = "CID.findByCdCID", query = "SELECT c FROM CID c WHERE c.cdCID = :cdCID"),
    @NamedQuery(name = "CID.findByNmDoenca", query = "SELECT c FROM CID c WHERE c.nmDoenca = :nmDoenca")})
public class CID implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cdCID")
    private String cdCID;
    @Column(name = "nmDoenca")
    private String nmDoenca;
    @JoinTable(name = "TblPacXDoenca", joinColumns = {
        @JoinColumn(name = "cdCID", referencedColumnName = "cdCID")}, inverseJoinColumns = {
        @JoinColumn(name = "nmCPFCliente", referencedColumnName = "nmCPFCliente")})
    @ManyToMany
    private List<Cliente> clienteList;
    @JoinColumn(name = "cdTipoDoenca", referencedColumnName = "cdTipoDoenca")
    @ManyToOne(optional = false)
    private TipoDoenca cdTipoDoenca;

    public CID() {
    }

    public CID(String cdCID) {
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

    @XmlTransient
    public List<Cliente> getClienteList() {
        return clienteList;
    }

    public void setClienteList(List<Cliente> clienteList) {
        this.clienteList = clienteList;
    }

    public TipoDoenca getCdTipoDoenca() {
        return cdTipoDoenca;
    }

    public void setCdTipoDoenca(TipoDoenca cdTipoDoenca) {
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
        if (!(object instanceof CID)) {
            return false;
        }
        CID other = (CID) object;
        if ((this.cdCID == null && other.cdCID != null) || (this.cdCID != null && !this.cdCID.equals(other.cdCID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.sw2.gac.modelo.CID[ cdCID=" + cdCID + " ]";
    }
    
}
