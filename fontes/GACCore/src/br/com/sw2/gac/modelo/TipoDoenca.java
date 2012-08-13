/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sw2.gac.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author marcelo
 */
@Entity
@Table(name = "TblTipoDoenca")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoDoenca.findAll", query = "SELECT t FROM TipoDoenca t"),
    @NamedQuery(name = "TipoDoenca.findByCdTipoDoenca", query = "SELECT t FROM TipoDoenca t WHERE t.cdTipoDoenca = :cdTipoDoenca"),
    @NamedQuery(name = "TipoDoenca.findByDsTipoDoenca", query = "SELECT t FROM TipoDoenca t WHERE t.dsTipoDoenca = :dsTipoDoenca")})
public class TipoDoenca implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cdTipoDoenca")
    private String cdTipoDoenca;
    @Column(name = "dsTipoDoenca")
    private String dsTipoDoenca;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cdTipoDoenca")
    private List<CID> cIDList;

    public TipoDoenca() {
    }

    public TipoDoenca(String cdTipoDoenca) {
        this.cdTipoDoenca = cdTipoDoenca;
    }

    public String getCdTipoDoenca() {
        return cdTipoDoenca;
    }

    public void setCdTipoDoenca(String cdTipoDoenca) {
        this.cdTipoDoenca = cdTipoDoenca;
    }

    public String getDsTipoDoenca() {
        return dsTipoDoenca;
    }

    public void setDsTipoDoenca(String dsTipoDoenca) {
        this.dsTipoDoenca = dsTipoDoenca;
    }

    @XmlTransient
    public List<CID> getCIDList() {
        return cIDList;
    }

    public void setCIDList(List<CID> cIDList) {
        this.cIDList = cIDList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cdTipoDoenca != null ? cdTipoDoenca.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoDoenca)) {
            return false;
        }
        TipoDoenca other = (TipoDoenca) object;
        if ((this.cdTipoDoenca == null && other.cdTipoDoenca != null) || (this.cdTipoDoenca != null && !this.cdTipoDoenca.equals(other.cdTipoDoenca))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.sw2.gac.modelo.TipoDoenca[ cdTipoDoenca=" + cdTipoDoenca + " ]";
    }
    
}
