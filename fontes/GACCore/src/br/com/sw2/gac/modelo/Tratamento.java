/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sw2.gac.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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

    /** Atributo tratamento pk. */
    @EmbeddedId
    private TratamentoPK tratamentoPK;

    /** Atributo nome trata. */
    @Column(name = "NomeTrata")
    private String nomeTrata;

    /** Atributo descr trata. */
    @Column(name = "DescrTrata")
    private String descrTrata;

    /** Atributo hora inicial. */
    @Basic(optional = false)
    @Column(name = "HoraInicial")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaInicial;

    /** Atributo frequencia. */
    @Basic(optional = false)
    @Column(name = "Frequencia")
    @Temporal(TemporalType.TIMESTAMP)
    private Date frequencia;

    /** Atributo cliente. */
    @JoinColumn(name = "NmCPFCliente", referencedColumnName = "NmCPFCliente", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Cliente cliente;

    /**
     * Construtor Padrao Instancia um novo objeto Tratamento.
     */
    public Tratamento() {
    }

    /**
     * Construtor Padrao Instancia um novo objeto Tratamento.
     * @param tratamentoPK the tratamento pk
     */
    public Tratamento(TratamentoPK tratamentoPK) {
        this.tratamentoPK = tratamentoPK;
    }

    /**
     * Construtor Padrao Instancia um novo objeto Tratamento.
     * @param tratamentoPK the tratamento pk
     * @param horaInicial the hora inicial
     * @param frequencia the frequencia
     */
    public Tratamento(TratamentoPK tratamentoPK, Date horaInicial, Date frequencia) {
        this.tratamentoPK = tratamentoPK;
        this.horaInicial = horaInicial;
        this.frequencia = frequencia;
    }

    /**
     * Construtor Padrao Instancia um novo objeto Tratamento.
     * @param idTratamento the id tratamento
     * @param nmCPFCliente the nm cpf cliente
     */
    public Tratamento(int idTratamento, String nmCPFCliente) {
        this.tratamentoPK = new TratamentoPK(idTratamento, nmCPFCliente);
    }

    /**
     * Nome: getTratamentoPK Recupera o valor do atributo 'tratamentoPK'.
     * @return valor do atributo 'tratamentoPK'
     * @see
     */
    public TratamentoPK getTratamentoPK() {
        return tratamentoPK;
    }

    /**
     * Nome: setTratamentoPK Registra o valor do atributo 'tratamentoPK'.
     * @param tratamentoPK valor do atributo tratamento pk
     * @see
     */
    public void setTratamentoPK(TratamentoPK tratamentoPK) {
        this.tratamentoPK = tratamentoPK;
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
     * Nome: getFrequencia Recupera o valor do atributo 'frequencia'.
     * @return valor do atributo 'frequencia'
     * @see
     */
    public Date getFrequencia() {
        return frequencia;
    }

    /**
     * Nome: setFrequencia Registra o valor do atributo 'frequencia'.
     * @param frequencia valor do atributo frequencia
     * @see
     */
    public void setFrequencia(Date frequencia) {
        this.frequencia = frequencia;
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

        if (tratamentoPK != null) {
            hash += tratamentoPK.hashCode();
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
        if ((this.tratamentoPK == null && other.tratamentoPK != null)
                || (this.tratamentoPK != null && !this.tratamentoPK.equals(other.tratamentoPK))) {
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
        return "br.com.sw2.gac.modelo.Tratamento[ tratamentoPK=" + tratamentoPK + " ]";
    }
}
