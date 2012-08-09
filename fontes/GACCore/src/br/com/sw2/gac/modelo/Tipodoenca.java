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

/**
 *
 * @author rogerio
 */
@Entity
@Table(name = "tbltipodoenca")
@NamedQueries({
    @NamedQuery(name = "Tipodoenca.findAll", query = "SELECT t FROM Tipodoenca t")})
public class Tipodoenca implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cdTipoDoenca")
    private String cdTipoDoenca;
    @Column(name = "dsTipoDoenca")
    private String dsTipoDoenca;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cdTipoDoenca")
    private List<Cid> cidList;

    public Tipodoenca() {
    }

    public Tipodoenca(String cdTipoDoenca) {
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

    public List<Cid> getCidList() {
        return cidList;
    }

    public void setCidList(List<Cid> cidList) {
        this.cidList = cidList;
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
        if (!(object instanceof Tipodoenca)) {
            return false;
        }
        Tipodoenca other = (Tipodoenca) object;
        if ((this.cdTipoDoenca == null && other.cdTipoDoenca != null) || (this.cdTipoDoenca != null && !this.cdTipoDoenca.equals(other.cdTipoDoenca))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.sw2.gac.modelo.Tipodoenca[ cdTipoDoenca=" + cdTipoDoenca + " ]";
    }
    
}
