/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sw2.gac.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author rogerio
 */
@Entity
@Table(name = "tblpacoteservico")
@NamedQueries({
    @NamedQuery(name = "Pacoteservico.findAll", query = "SELECT p FROM Pacoteservico p")})
public class Pacoteservico implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idServico")
    private List<Contrato> contratoList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdServico")
    private Integer idServico;
    @Column(name = "dsServico")
    private String dsServico;
    @Basic(optional = false)
    @Column(name = "dtInicioValidade")
    @Temporal(TemporalType.DATE)
    private Date dtInicioValidade;
    @Column(name = "dtFinalValidade")
    @Temporal(TemporalType.DATE)
    private Date dtFinalValidade;

    public Pacoteservico() {
    }

    public Pacoteservico(Integer idServico) {
        this.idServico = idServico;
    }

    public Pacoteservico(Integer idServico, Date dtInicioValidade) {
        this.idServico = idServico;
        this.dtInicioValidade = dtInicioValidade;
    }

    public Integer getIdServico() {
        return idServico;
    }

    public void setIdServico(Integer idServico) {
        this.idServico = idServico;
    }

    public String getDsServico() {
        return dsServico;
    }

    public void setDsServico(String dsServico) {
        this.dsServico = dsServico;
    }

    public Date getDtInicioValidade() {
        return dtInicioValidade;
    }

    public void setDtInicioValidade(Date dtInicioValidade) {
        this.dtInicioValidade = dtInicioValidade;
    }

    public Date getDtFinalValidade() {
        return dtFinalValidade;
    }

    public void setDtFinalValidade(Date dtFinalValidade) {
        this.dtFinalValidade = dtFinalValidade;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idServico != null ? idServico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pacoteservico)) {
            return false;
        }
        Pacoteservico other = (Pacoteservico) object;
        if ((this.idServico == null && other.idServico != null) || (this.idServico != null && !this.idServico.equals(other.idServico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.sw2.gac.modelo.Pacoteservico[ idServico=" + idServico + " ]";
    }

    public List<Contrato> getContratoList() {
        return contratoList;
    }

    public void setContratoList(List<Contrato> contratoList) {
        this.contratoList = contratoList;
    }
    
}
