/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sw2.gac.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author rogerio
 */
@Embeddable
public class TratamentoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "IdTratamento")
    private int idTratamento;
    @Basic(optional = false)
    @Column(name = "NmCPFCliente")
    private String nmCPFCliente;

    public TratamentoPK() {
    }

    public TratamentoPK(int idTratamento, String nmCPFCliente) {
        this.idTratamento = idTratamento;
        this.nmCPFCliente = nmCPFCliente;
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
        hash += (int) idTratamento;
        hash += (nmCPFCliente != null ? nmCPFCliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TratamentoPK)) {
            return false;
        }
        TratamentoPK other = (TratamentoPK) object;
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
        return "br.com.sw2.gac.modelo.TratamentoPK[ idTratamento=" + idTratamento + ", nmCPFCliente=" + nmCPFCliente + " ]";
    }
    
}
