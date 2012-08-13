/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sw2.gac.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author marcelo
 */
@Entity
@Table(name = "TblAplicaMedico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AplicaMedico.findAll", query = "SELECT a FROM AplicaMedico a"),
    @NamedQuery(name = "AplicaMedico.findByHrAplicacao", query = "SELECT a FROM AplicaMedico a WHERE a.aplicaMedicoPK.hrAplicacao = :hrAplicacao"),
    @NamedQuery(name = "AplicaMedico.findByIdTratamento", query = "SELECT a FROM AplicaMedico a WHERE a.aplicaMedicoPK.idTratamento = :idTratamento"),
    @NamedQuery(name = "AplicaMedico.findByNmCPFCliente", query = "SELECT a FROM AplicaMedico a WHERE a.aplicaMedicoPK.nmCPFCliente = :nmCPFCliente")})
public class AplicaMedico implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AplicaMedicoPK aplicaMedicoPK;
    @JoinColumns({
        @JoinColumn(name = "idTratamento", referencedColumnName = "idTratamento", insertable = false, updatable = false),
        @JoinColumn(name = "nmCPFCliente", referencedColumnName = "nmCPFCliente", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Tratamento tratamento;

    public AplicaMedico() {
    }

    public AplicaMedico(AplicaMedicoPK aplicaMedicoPK) {
        this.aplicaMedicoPK = aplicaMedicoPK;
    }

    public AplicaMedico(Date hrAplicacao, int idTratamento, String nmCPFCliente) {
        this.aplicaMedicoPK = new AplicaMedicoPK(hrAplicacao, idTratamento, nmCPFCliente);
    }

    public AplicaMedicoPK getAplicaMedicoPK() {
        return aplicaMedicoPK;
    }

    public void setAplicaMedicoPK(AplicaMedicoPK aplicaMedicoPK) {
        this.aplicaMedicoPK = aplicaMedicoPK;
    }

    public Tratamento getTratamento() {
        return tratamento;
    }

    public void setTratamento(Tratamento tratamento) {
        this.tratamento = tratamento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (aplicaMedicoPK != null ? aplicaMedicoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AplicaMedico)) {
            return false;
        }
        AplicaMedico other = (AplicaMedico) object;
        if ((this.aplicaMedicoPK == null && other.aplicaMedicoPK != null) || (this.aplicaMedicoPK != null && !this.aplicaMedicoPK.equals(other.aplicaMedicoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.sw2.gac.modelo.AplicaMedico[ aplicaMedicoPK=" + aplicaMedicoPK + " ]";
    }
    
}
