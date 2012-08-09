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
 * <b>Descrição:</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@Entity
@Table(name = "tblcontato")
@NamedQueries({ @NamedQuery(name = "Contato.findAll", query = "SELECT c FROM Contato c") })
public class Contato implements Serializable {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** Atributo id contato. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdContato")
    private Integer idContato;

    /** Atributo nome contato. */
    @Column(name = "NomeContato")
    private String nomeContato;

    /** Atributo grau parentesco. */
    @Column(name = "GrauParentesco")
    private Character grauParentesco;

    /** Atributo end contato. */
    @Column(name = "EndContato")
    private String endContato;

    /** Atributo bai contato. */
    @Column(name = "BaiContato")
    private String baiContato;

    /** Atributo cid contato. */
    @Column(name = "CidContato")
    private String cidContato;

    /** Atributo c ep contato. */
    @Column(name = "CEPContato")
    private String cEPContato;

    /** Atributo estado contato. */
    @Column(name = "EstadoContato")
    private String estadoContato;

    /** Atributo dta nascimento. */
    @Column(name = "dtaNascimento")
    @Temporal(TemporalType.DATE)
    private Date dtaNascimento;

    /** Atributo sqa chamada. */
    @Column(name = "sqaChamada")
    private Integer sqaChamada;

    /** Atributo contratante. */
    @Column(name = "Contratante")
    private Character contratante;

    /** Atributo login. */
    @JoinColumn(name = "Login", referencedColumnName = "login")
    @ManyToOne(optional = false)
    private Usuario login;

    /** Atributo nm cpf cliente. */
    @JoinColumn(name = "NmCPFCliente", referencedColumnName = "NmCPFCliente")
    @ManyToOne(optional = false)
    private Cliente nmCPFCliente;

    /** Atributo formacomunica list. */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contato")
    private List<Formacomunica> formacomunicaList;

    /** Atributo acionamento list. */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idContato")
    private List<Acionamento> acionamentoList;

    /**
     * Construtor Padrao Instancia um novo objeto Contato.
     */
    public Contato() {
    }

    /**
     * Construtor Padrao Instancia um novo objeto Contato.
     * @param idContato the id contato
     */
    public Contato(Integer idContato) {
        this.idContato = idContato;
    }

    /**
     * Nome: getIdContato Recupera o valor do atributo 'idContato'.
     * @return valor do atributo 'idContato'
     * @see
     */
    public Integer getIdContato() {
        return idContato;
    }

    /**
     * Nome: setIdContato Registra o valor do atributo 'idContato'.
     * @param idContato valor do atributo id contato
     * @see
     */
    public void setIdContato(Integer idContato) {
        this.idContato = idContato;
    }

    /**
     * Nome: getNomeContato Recupera o valor do atributo 'nomeContato'.
     * @return valor do atributo 'nomeContato'
     * @see
     */
    public String getNomeContato() {
        return nomeContato;
    }

    /**
     * Nome: setNomeContato Registra o valor do atributo 'nomeContato'.
     * @param nomeContato valor do atributo nome contato
     * @see
     */
    public void setNomeContato(String nomeContato) {
        this.nomeContato = nomeContato;
    }

    /**
     * Nome: getGrauParentesco Recupera o valor do atributo 'grauParentesco'.
     * @return valor do atributo 'grauParentesco'
     * @see
     */
    public Character getGrauParentesco() {
        return grauParentesco;
    }

    /**
     * Nome: setGrauParentesco Registra o valor do atributo 'grauParentesco'.
     * @param grauParentesco valor do atributo grau parentesco
     * @see
     */
    public void setGrauParentesco(Character grauParentesco) {
        this.grauParentesco = grauParentesco;
    }

    /**
     * Nome: getEndContato Recupera o valor do atributo 'endContato'.
     * @return valor do atributo 'endContato'
     * @see
     */
    public String getEndContato() {
        return endContato;
    }

    /**
     * Nome: setEndContato Registra o valor do atributo 'endContato'.
     * @param endContato valor do atributo end contato
     * @see
     */
    public void setEndContato(String endContato) {
        this.endContato = endContato;
    }

    /**
     * Nome: getBaiContato Recupera o valor do atributo 'baiContato'.
     * @return valor do atributo 'baiContato'
     * @see
     */
    public String getBaiContato() {
        return baiContato;
    }

    /**
     * Nome: setBaiContato Registra o valor do atributo 'baiContato'.
     * @param baiContato valor do atributo bai contato
     * @see
     */
    public void setBaiContato(String baiContato) {
        this.baiContato = baiContato;
    }

    /**
     * Nome: getCidContato Recupera o valor do atributo 'cidContato'.
     * @return valor do atributo 'cidContato'
     * @see
     */
    public String getCidContato() {
        return cidContato;
    }

    /**
     * Nome: setCidContato Registra o valor do atributo 'cidContato'.
     * @param cidContato valor do atributo cid contato
     * @see
     */
    public void setCidContato(String cidContato) {
        this.cidContato = cidContato;
    }

    /**
     * Nome: getCEPContato Recupera o valor do atributo 'CEPContato'.
     * @return valor do atributo 'CEPContato'
     * @see
     */
    public String getCEPContato() {
        return cEPContato;
    }

    /**
     * Nome: setCEPContato Registra o valor do atributo 'CEPContato'.
     * @param cEPContato valor do atributo cEP contato
     * @see
     */
    public void setCEPContato(String cEPContato) {
        this.cEPContato = cEPContato;
    }

    /**
     * Nome: getEstadoContato Recupera o valor do atributo 'estadoContato'.
     * @return valor do atributo 'estadoContato'
     * @see
     */
    public String getEstadoContato() {
        return estadoContato;
    }

    /**
     * Nome: setEstadoContato Registra o valor do atributo 'estadoContato'.
     * @param estadoContato valor do atributo estado contato
     * @see
     */
    public void setEstadoContato(String estadoContato) {
        this.estadoContato = estadoContato;
    }

    /**
     * Nome: getDtaNascimento Recupera o valor do atributo 'dtaNascimento'.
     * @return valor do atributo 'dtaNascimento'
     * @see
     */
    public Date getDtaNascimento() {
        return dtaNascimento;
    }

    /**
     * Nome: setDtaNascimento Registra o valor do atributo 'dtaNascimento'.
     * @param dtaNascimento valor do atributo dta nascimento
     * @see
     */
    public void setDtaNascimento(Date dtaNascimento) {
        this.dtaNascimento = dtaNascimento;
    }

    /**
     * Nome: getSqaChamada Recupera o valor do atributo 'sqaChamada'.
     * @return valor do atributo 'sqaChamada'
     * @see
     */
    public Integer getSqaChamada() {
        return sqaChamada;
    }

    /**
     * Nome: setSqaChamada Registra o valor do atributo 'sqaChamada'.
     * @param sqaChamada valor do atributo sqa chamada
     * @see
     */
    public void setSqaChamada(Integer sqaChamada) {
        this.sqaChamada = sqaChamada;
    }

    /**
     * Nome: getContratante Recupera o valor do atributo 'contratante'.
     * @return valor do atributo 'contratante'
     * @see
     */
    public Character getContratante() {
        return contratante;
    }

    /**
     * Nome: setContratante Registra o valor do atributo 'contratante'.
     * @param contratante valor do atributo contratante
     * @see
     */
    public void setContratante(Character contratante) {
        this.contratante = contratante;
    }

    /**
     * Nome: getLogin Recupera o valor do atributo 'login'.
     * @return valor do atributo 'login'
     * @see
     */
    public Usuario getLogin() {
        return login;
    }

    /**
     * Nome: setLogin Registra o valor do atributo 'login'.
     * @param login valor do atributo login
     * @see
     */
    public void setLogin(Usuario login) {
        this.login = login;
    }

    /**
     * Nome: getNmCPFCliente Recupera o valor do atributo 'nmCPFCliente'.
     * @return valor do atributo 'nmCPFCliente'
     * @see
     */
    public Cliente getNmCPFCliente() {
        return nmCPFCliente;
    }

    /**
     * Nome: setNmCPFCliente Registra o valor do atributo 'nmCPFCliente'.
     * @param nmCPFCliente valor do atributo nm cpf cliente
     * @see
     */
    public void setNmCPFCliente(Cliente nmCPFCliente) {
        this.nmCPFCliente = nmCPFCliente;
    }

    /**
     * Nome: getFormacomunicaList Recupera o valor do atributo 'formacomunicaList'.
     * @return valor do atributo 'formacomunicaList'
     * @see
     */
    public List<Formacomunica> getFormacomunicaList() {
        return formacomunicaList;
    }

    /**
     * Nome: setFormacomunicaList Registra o valor do atributo 'formacomunicaList'.
     * @param formacomunicaList valor do atributo formacomunica list
     * @see
     */
    public void setFormacomunicaList(List<Formacomunica> formacomunicaList) {
        this.formacomunicaList = formacomunicaList;
    }

    /**
     * Nome: getAcionamentoList Recupera o valor do atributo 'acionamentoList'.
     * @return valor do atributo 'acionamentoList'
     * @see
     */
    public List<Acionamento> getAcionamentoList() {
        return acionamentoList;
    }

    /**
     * Nome: setAcionamentoList Registra o valor do atributo 'acionamentoList'.
     * @param acionamentoList valor do atributo acionamento list
     * @see
     */
    public void setAcionamentoList(List<Acionamento> acionamentoList) {
        this.acionamentoList = acionamentoList;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int hash = 0;

        if (idContato != null) {
            hash += idContato.hashCode();
        } else {
            hash += 0;
        }

        return hash;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object object) {

        if (!(object instanceof Contato)) {
            return false;
        }
        Contato other = (Contato) object;
        if ((this.idContato == null && other.idContato != null)
                || (this.idContato != null && !this.idContato.equals(other.idContato))) {
            return false;
        }
        return true;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "br.com.sw2.gac.modelo.Contato[ idContato=" + idContato + " ]";
    }

}
