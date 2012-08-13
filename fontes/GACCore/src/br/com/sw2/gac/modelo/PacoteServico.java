/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sw2.gac.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author marcelo
 */
@Entity
@Table(name = "TblPacoteServico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PacoteServico.findAll", query = "SELECT p FROM PacoteServico p"),
    @NamedQuery(name = "PacoteServico.findByIdServico", query = "SELECT p FROM PacoteServico p WHERE p.idServico = :idServico"),
    @NamedQuery(name = "PacoteServico.findByDsServico", query = "SELECT p FROM PacoteServico p WHERE p.dsServico = :dsServico"),
    @NamedQuery(name = "PacoteServico.findByDtInicioValidade", query = "SELECT p FROM PacoteServico p WHERE p.dtInicioValidade = :dtInicioValidade"),
    @NamedQuery(name = "PacoteServico.findByDtFinalValidade", query = "SELECT p FROM PacoteServico p WHERE p.dtFinalValidade = :dtFinalValidade"),
    @NamedQuery(name = "PacoteServico.findByPrcMensal", query = "SELECT p FROM PacoteServico p WHERE p.prcMensal = :prcMensal")})
public class PacoteServico implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idServico")
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
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "prcMensal")
    private BigDecimal prcMensal;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idServico")
    private List<Contrato> contratoList;

    public PacoteServico() {
    }

    public PacoteServico(Integer idServico) {
        this.idServico = idServico;
    }

    public PacoteServico(Integer idServico, Date dtInicioValidade) {
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

    public BigDecimal getPrcMensal() {
        return prcMensal;
    }

    public void setPrcMensal(BigDecimal prcMensal) {
        this.prcMensal = prcMensal;
    }

    @XmlTransient
    public List<Contrato> getContratoList() {
        return contratoList;
    }

    public void setContratoList(List<Contrato> contratoList) {
        this.contratoList = contratoList;
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
        if (!(object instanceof PacoteServico)) {
            return false;
        }
        PacoteServico other = (PacoteServico) object;
        if ((this.idServico == null && other.idServico != null) || (this.idServico != null && !this.idServico.equals(other.idServico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.sw2.gac.modelo.PacoteServico[ idServico=" + idServico + " ]";
    }
    
}
