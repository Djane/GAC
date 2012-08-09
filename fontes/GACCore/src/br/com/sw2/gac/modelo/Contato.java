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

/**
 *
 * @author rogerio
 */
@Entity
@Table(name = "tblcontato")
@NamedQueries({
    @NamedQuery(name = "Contato.findAll", query = "SELECT c FROM Contato c")})
public class Contato implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdContato")
    private Integer idContato;
    @Column(name = "NomeContato")
    private String nomeContato;
    @Column(name = "GrauParentesco")
    private Character grauParentesco;
    @Column(name = "EndContato")
    private String endContato;
    @Column(name = "BaiContato")
    private String baiContato;
    @Column(name = "CidContato")
    private String cidContato;
    @Column(name = "CEPContato")
    private String cEPContato;
    @Column(name = "EstadoContato")
    private String estadoContato;
    @Column(name = "dtaNascimento")
    @Temporal(TemporalType.DATE)
    private Date dtaNascimento;
    @Column(name = "sqaChamada")
    private Integer sqaChamada;
    @Column(name = "Contratante")
    private Character contratante;
    @JoinColumn(name = "Login", referencedColumnName = "login")
    @ManyToOne(optional = false)
    private Usuario login;
    @JoinColumn(name = "NmCPFCliente", referencedColumnName = "NmCPFCliente")
    @ManyToOne(optional = false)
    private Cliente nmCPFCliente;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contato")
    private List<Formacomunica> formacomunicaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idContato")
    private List<Acionamento> acionamentoList;

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

    public String getCEPContato() {
        return cEPContato;
    }

    public void setCEPContato(String cEPContato) {
        this.cEPContato = cEPContato;
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

    public List<Formacomunica> getFormacomunicaList() {
        return formacomunicaList;
    }

    public void setFormacomunicaList(List<Formacomunica> formacomunicaList) {
        this.formacomunicaList = formacomunicaList;
    }

    public List<Acionamento> getAcionamentoList() {
        return acionamentoList;
    }

    public void setAcionamentoList(List<Acionamento> acionamentoList) {
        this.acionamentoList = acionamentoList;
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
