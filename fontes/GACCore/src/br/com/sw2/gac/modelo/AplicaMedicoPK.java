/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sw2.gac.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author marcelo
 */
@Embeddable
public class AplicaMedicoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "hrAplicacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hrAplicacao;
    @Basic(optional = false)
    @Column(name = "idTratamento")
    private int idTratamento;
    @Basic(optional = false)
    @Column(name = "nmCPFCliente")
    private String nmCPFCliente;

    public AplicaMedicoPK() {
    }

    public AplicaMedicoPK(Date hrAplicacao, int idTratamento, String nmCPFCliente) {
        this.hrAplicacao = hrAplicacao;
        this.idTratamento = idTratamento;
        this.nmCPFCliente = nmCPFCliente;
    }

    public Date getHrAplicacao() {
        return hrAplicacao;
    }

    public void setHrAplicacao(Date hrAplicacao) {
        this.hrAplicacao = hrAplicacao;
    }

    public int getIdTratamento() {
        return idTratamento;
    }

    public void setIdTratamento(int idTratamento) {
        this.idTratamento = idTratamento;
    }

    public String getNmCPFCliente() {
        return nmCPFCliente;
    }

    public void setNmCPFCliente(String nmCPFCliente) {
        this.nmCPFCliente = nmCPFCliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hrAplicacao != null ? hrAplicacao.hashCode() : 0);
        hash += (int) idTratamento;
        hash += (nmCPFCliente != null ? nmCPFCliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AplicaMedicoPK)) {
            return false;
        }
        AplicaMedicoPK other = (AplicaMedicoPK) object;
        if ((this.hrAplicacao == null && other.hrAplicacao != null) || (this.hrAplicacao != null && !this.hrAplicacao.equals(other.hrAplicacao))) {
            return false;
        }
        if (this.idTratamento != other.idTratamento) {
            return false;
        }
        if ((this.nmCPFCliente == null && other.nmCPFCliente != null) || (this.nmCPFCliente != null && !this.nmCPFCliente.equals(other.nmCPFCliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.sw2.gac.modelo.AplicaMedicoPK[ hrAplicacao=" + hrAplicacao + ", idTratamento=" + idTratamento + ", nmCPFCliente=" + nmCPFCliente + " ]";
    }
    
}
