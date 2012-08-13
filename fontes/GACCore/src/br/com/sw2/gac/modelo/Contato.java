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
@Table(name = "TblContato")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contato.findAll", query = "SELECT c FROM Contato c"),
    @NamedQuery(name = "Contato.findByIdContato", query = "SELECT c FROM Contato c WHERE c.idContato = :idContato"),
    @NamedQuery(name = "Contato.findByNomeContato", query = "SELECT c FROM Contato c WHERE c.nomeContato = :nomeContato"),
    @NamedQuery(name = "Contato.findByGrauParentesco", query = "SELECT c FROM Contato c WHERE c.grauParentesco = :grauParentesco"),
    @NamedQuery(name = "Contato.findByEndContato", query = "SELECT c FROM Contato c WHERE c.endContato = :endContato"),
    @NamedQuery(name = "Contato.findByBaiContato", query = "SELECT c FROM Contato c WHERE c.baiContato = :baiContato"),
    @NamedQuery(name = "Contato.findByCidContato", query = "SELECT c FROM Contato c WHERE c.cidContato = :cidContato"),
    @NamedQuery(name = "Contato.findByCepContato", query = "SELECT c FROM Contato c WHERE c.cepContato = :cepContato"),
    @NamedQuery(name = "Contato.findByEstadoContato", query = "SELECT c FROM Contato c WHERE c.estadoContato = :estadoContato"),
    @NamedQuery(name = "Contato.findByDtaNascimento", query = "SELECT c FROM Contato c WHERE c.dtaNascimento = :dtaNascimento"),
    @NamedQuery(name = "Contato.findBySqaChamada", query = "SELECT c FROM Contato c WHERE c.sqaChamada = :sqaChamada"),
    @NamedQuery(name = "Contato.findByContratante", query = "SELECT c FROM Contato c WHERE c.contratante = :contratante")})
public class Contato implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idContato")
    private Integer idContato;
    @Column(name = "nomeContato")
    private String nomeContato;
    @Column(name = "grauParentesco")
    private Character grauParentesco;
    @Column(name = "endContato")
    private String endContato;
    @Column(name = "baiContato")
    private String baiContato;
    @Column(name = "cidContato")
    private String cidContato;
    @Column(name = "cepContato")
    private String cepContato;
    @Column(name = "estadoContato")
    private String estadoContato;
    @Column(name = "dtaNascimento")
    @Temporal(TemporalType.DATE)
    private Date dtaNascimento;
    @Column(name = "sqaChamada")
    private Integer sqaChamada;
    @Column(name = "contratante")
    private Character contratante;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contato")
    private List<FormaComunica> formaComunicaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idContato")
    private List<Acionamento> acionamentoList;
    @JoinColumn(name = "login", referencedColumnName = "login")
    @ManyToOne(optional = false)
    private Usuario login;
    @JoinColumn(name = "nmCPFCliente", referencedColumnName = "nmCPFCliente")
    @ManyToOne(optional = false)
    private Cliente nmCPFCliente;

    public Contato() {
    }

    public Contato(Integer idContato) {
        this.idContato = idContato;
    }

    public Integer getIdContato() {
        return idContato;
    }

    public void setIdContato(Integer idContato) {
        this.idContato = idContato;
    }

    public String getNomeContato() {
        return nomeContato;
    }

    public void setNomeContato(String nomeContato) {
        this.nomeContato = nomeContato;
    }

    public Character getGrauParentesco() {
        return grauParentesco;
    }

    public void setGrauParentesco(Character grauParentesco) {
        this.grauParentesco = grauParentesco;
    }

    public String getEndContato() {
        return endContato;
    }

    public void setEndContato(String endContato) {
        this.endContato = endContato;
    }

    public String getBaiContato() {
        return baiContato;
    }

    public void setBaiContato(String baiContato) {
        this.baiContato = baiContato;
    }

    public String getCidContato() {
        return cidContato;
    }

    public void setCidContato(String cidContato) {
        this.cidContato = cidContato;
    }

    public String getCepContato() {
        return cepContato;
    }

    public void setCepContato(String cepContato) {
        this.cepContato = cepContato;
    }

    public String getEstadoContato() {
        return estadoContato;
    }

    public void setEstadoContato(String estadoContato) {
        this.estadoContato = estadoContato;
    }

    public Date getDtaNascimento() {
        return dtaNascimento;
    }

    public void setDtaNascimento(Date dtaNascimento) {
        this.dtaNascimento = dtaNascimento;
    }

    public Integer getSqaChamada() {
        return sqaChamada;
    }

    public void setSqaChamada(Integer sqaChamada) {
        this.sqaChamada = sqaChamada;
    }

    public Character getContratante() {
        return contratante;
    }

    public void setContratante(Character contratante) {
        this.contratante = contratante;
    }

    @XmlTransient
    public List<FormaComunica> getFormaComunicaList() {
        return formaComunicaList;
    }

    public void setFormaComunicaList(List<FormaComunica> formaComunicaList) {
        this.formaComunicaList = formaComunicaList;
    }

    @XmlTransient
    public List<Acionamento> getAcionamentoList() {
        return acionamentoList;
    }

    public void setAcionamentoList(List<Acionamento> acionamentoList) {
        this.acionamentoList = acionamentoList;
    }

    public Usuario getLogin() {
        return login;
    }

    public void setLogin(Usuario login) {
        this.login = login;
    }

    public Cliente getNmCPFCliente() {
        return nmCPFCliente;
    }

    public void setNmCPFCliente(Cliente nmCPFCliente) {
        this.nmCPFCliente = nmCPFCliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idContato != null ? idContato.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contato)) {
            return false;
        }
        Contato other = (Contato) object;
        if ((this.idContato == null && other.idContato != null) || (this.idContato != null && !this.idContato.equals(other.idContato))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.sw2.gac.modelo.Contato[ idContato=" + idContato + " ]";
    }
    
}
