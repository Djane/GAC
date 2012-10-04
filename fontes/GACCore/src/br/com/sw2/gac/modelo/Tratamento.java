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
@Table(name = "tbltratamento")
@NamedQueries({ @NamedQuery(name = "Tratamento.findAll", query = "SELECT t FROM Tratamento t") })
public class Tratamento implements Serializable {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** Atributo id tratamento. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTratamento")
    private Integer idTratamento;

    /** Atributo nome trata. */
    @Column(name = "nomeTrata")
    private String nomeTrata;

    /** Atributo descr trata. */
    @Column(name = "descrTrata")
    private String descrTrata;

    /** Atributo hora inicial. */
    @Basic(optional = false)
    @Column(name = "horaInicial")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaInicial;

    /** Atributo tp frequencia. */
    @Column(name = "tpFrequencia")
    private Integer tpFrequencia;

    /** Atributo aplica medico list. */

    @OneToMany(mappedBy = "tratamento", cascade = CascadeType.ALL)
    private List<AplicaMedico> aplicaMedicoList;

    /** Atributo cliente. */
    @JoinColumn(name = "nmCPFCliente", referencedColumnName = "nmCPFCliente")
    @ManyToOne
    private Cliente cliente;

    /**
     * Construtor Padrao Instancia um novo objeto Tratamento.
     */
    public Tratamento() {
        super();
    }

    public Integer getIdTratamento() {
        return idTratamento;
    }

    public void setIdTratamento(Integer idTratamento) {
        this.idTratamento = idTratamento;
    }

    /**
     * Nome: getNomeTrata Recupera o valor do atributo 'nomeTrata'.
     * @return valor do atributo 'nomeTrata'
     * @see
     */
    public String getNomeTrata() {
        return nomeTrata;
    }

    /**
     * Nome: setNomeTrata Registra o valor do atributo 'nomeTrata'.
     * @param nomeTrata valor do atributo nome trata
     * @see
     */
    public void setNomeTrata(String nomeTrata) {
        this.nomeTrata = nomeTrata;
    }

    /**
     * Nome: getDescrTrata Recupera o valor do atributo 'descrTrata'.
     * @return valor do atributo 'descrTrata'
     * @see
     */
    public String getDescrTrata() {
        return descrTrata;
    }

    /**
     * Nome: setDescrTrata Registra o valor do atributo 'descrTrata'.
     * @param descrTrata valor do atributo descr trata
     * @see
     */
    public void setDescrTrata(String descrTrata) {
        this.descrTrata = descrTrata;
    }

    /**
     * Nome: getHoraInicial Recupera o valor do atributo 'horaInicial'.
     * @return valor do atributo 'horaInicial'
     * @see
     */
    public Date getHoraInicial() {
        return horaInicial;
    }

    /**
     * Nome: setHoraInicial Registra o valor do atributo 'horaInicial'.
     * @param horaInicial valor do atributo hora inicial
     * @see
     */
    public void setHoraInicial(Date horaInicial) {
        this.horaInicial = horaInicial;
    }

    /**
     * Nome: getTpFrequencia Recupera o valor do atributo 'tpFrequencia'.
     * @return valor do atributo 'tpFrequencia'
     * @see
     */
    public Integer getTpFrequencia() {
        return tpFrequencia;
    }

    /**
     * Nome: setTpFrequencia Registra o valor do atributo 'tpFrequencia'.
     * @param tpFrequencia valor do atributo tp frequencia
     * @see
     */
    public void setTpFrequencia(Integer tpFrequencia) {
        this.tpFrequencia = tpFrequencia;
    }

    /**
     * Nome: getAplicaMedicoList Recupera o valor do atributo 'aplicaMedicoList'.
     * @return valor do atributo 'aplicaMedicoList'
     * @see
     */
    public List<AplicaMedico> getAplicaMedicoList() {
        return aplicaMedicoList;
    }

    /**
     * Nome: setAplicaMedicoList Registra o valor do atributo 'aplicaMedicoList'.
     * @param aplicaMedicoList valor do atributo aplica medico list
     * @see
     */
    public void setAplicaMedicoList(List<AplicaMedico> aplicaMedicoList) {
        this.aplicaMedicoList = aplicaMedicoList;
    }

    /**
     * Nome: getCliente Recupera o valor do atributo 'cliente'.
     * @return valor do atributo 'cliente'
     * @see
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Nome: setCliente Registra o valor do atributo 'cliente'.
     * @param cliente valor do atributo cliente
     * @see
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int hash = 0;

        if (idTratamento != null) {
            hash += idTratamento.hashCode();
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

        if (!(object instanceof Tratamento)) {
            return false;
        }
        Tratamento other = (Tratamento) object;
        if ((this.idTratamento == null && other.idTratamento != null)
            || (this.idTratamento != null && !this.idTratamento.equals(other.idTratamento))) {
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
        return "br.com.sw2.gac.modelo.Tratamento[ tratamentoPK=" + idTratamento + " ]";
    }

}
