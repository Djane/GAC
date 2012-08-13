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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "TblTratamento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tratamento.findAll", query = "SELECT t FROM Tratamento t"),
    @NamedQuery(name = "Tratamento.findByIdTratamento", query = "SELECT t FROM Tratamento t WHERE t.tratamentoPK.idTratamento = :idTratamento"),
    @NamedQuery(name = "Tratamento.findByNmCPFCliente", query = "SELECT t FROM Tratamento t WHERE t.tratamentoPK.nmCPFCliente = :nmCPFCliente"),
    @NamedQuery(name = "Tratamento.findByNomeTrata", query = "SELECT t FROM Tratamento t WHERE t.nomeTrata = :nomeTrata"),
    @NamedQuery(name = "Tratamento.findByDescrTrata", query = "SELECT t FROM Tratamento t WHERE t.descrTrata = :descrTrata"),
    @NamedQuery(name = "Tratamento.findByHoraInicial", query = "SELECT t FROM Tratamento t WHERE t.horaInicial = :horaInicial")})
public class Tratamento implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TratamentoPK tratamentoPK;
    @Column(name = "nomeTrata")
    private String nomeTrata;
    @Column(name = "descrTrata")
    private String descrTrata;
    @Basic(optional = false)
    @Column(name = "horaInicial")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaInicial;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tratamento")
    private List<AplicaMedico> aplicaMedicoList;
    @JoinColumn(name = "nmCPFCliente", referencedColumnName = "nmCPFCliente", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Cliente cliente;

    public Tratamento() {
    }

    public Tratamento(TratamentoPK tratamentoPK) {
        this.tratamentoPK = tratamentoPK;
    }

    public Tratamento(TratamentoPK tratamentoPK, Date horaInicial) {
        this.tratamentoPK = tratamentoPK;
        this.horaInicial = horaInicial;
    }

    public Tratamento(int idTratamento, String nmCPFCliente) {
        this.tratamentoPK = new TratamentoPK(idTratamento, nmCPFCliente);
    }

    public TratamentoPK getTratamentoPK() {
        return tratamentoPK;
    }

    public void setTratamentoPK(TratamentoPK tratamentoPK) {
        this.tratamentoPK = tratamentoPK;
    }

    public String getNomeTrata() {
        return nomeTrata;
    }

    public void setNomeTrata(String nomeTrata) {
        this.nomeTrata = nomeTrata;
    }

    public String getDescrTrata() {
        return descrTrata;
    }

    public void setDescrTrata(String descrTrata) {
        this.descrTrata = descrTrata;
    }

    public Date getHoraInicial() {
        return horaInicial;
    }

    public void setHoraInicial(Date horaInicial) {
        this.horaInicial = horaInicial;
    }

    @XmlTransient
    public List<AplicaMedico> getAplicaMedicoList() {
        return aplicaMedicoList;
    }

    public void setAplicaMedicoList(List<AplicaMedico> aplicaMedicoList) {
        this.aplicaMedicoList = aplicaMedicoList;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tratamentoPK != null ? tratamentoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tratamento)) {
            return false;
        }
        Tratamento other = (Tratamento) object;
        if ((this.tratamentoPK == null && other.tratamentoPK != null) || (this.tratamentoPK != null && !this.tratamentoPK.equals(other.tratamentoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.sw2.gac.modelo.Tratamento[ tratamentoPK=" + tratamentoPK + " ]";
    }
    
}
