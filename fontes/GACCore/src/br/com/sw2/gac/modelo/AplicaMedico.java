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

/**
 * <b>Descrição:</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@Entity
@Table(name = "tblaplicamedico")
@NamedQueries({ @NamedQuery(name = "AplicaMedico.findAll", query = "SELECT a FROM AplicaMedico a") })
public class AplicaMedico implements Serializable {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** Atributo aplica medico pk. */
    @EmbeddedId
    private AplicaMedicoPK aplicaMedicoPK;

    /** Atributo tratamento. */
    @JoinColumns({
            @JoinColumn(name = "idTratamento", referencedColumnName = "idTratamento", insertable = false, updatable = false),
            @JoinColumn(name = "nmCPFCliente", referencedColumnName = "nmCPFCliente", insertable = false, updatable = false) })
    @ManyToOne(optional = false)
    private Tratamento tratamento;

    /**
     * Construtor Padrao Instancia um novo objeto AplicaMedico.
     */
    public AplicaMedico() {
    }

    /**
     * Construtor Padrao Instancia um novo objeto AplicaMedico.
     * @param aplicaMedicoPK the aplica medico pk
     */
    public AplicaMedico(AplicaMedicoPK aplicaMedicoPK) {
        this.aplicaMedicoPK = aplicaMedicoPK;
    }

    /**
     * Construtor Padrao Instancia um novo objeto AplicaMedico.
     * @param hrAplicacao the hr aplicacao
     * @param idTratamento the id tratamento
     * @param nmCPFCliente the nm cpf cliente
     */
    public AplicaMedico(Date hrAplicacao, int idTratamento, String nmCPFCliente) {
        this.aplicaMedicoPK = new AplicaMedicoPK(hrAplicacao, idTratamento, nmCPFCliente);
    }

    /**
     * Nome: getAplicaMedicoPK Recupera o valor do atributo 'aplicaMedicoPK'.
     * @return valor do atributo 'aplicaMedicoPK'
     * @see
     */
    public AplicaMedicoPK getAplicaMedicoPK() {
        return aplicaMedicoPK;
    }

    /**
     * Nome: setAplicaMedicoPK Registra o valor do atributo 'aplicaMedicoPK'.
     * @param aplicaMedicoPK valor do atributo aplica medico pk
     * @see
     */
    public void setAplicaMedicoPK(AplicaMedicoPK aplicaMedicoPK) {
        this.aplicaMedicoPK = aplicaMedicoPK;
    }

    /**
     * Nome: getTratamento Recupera o valor do atributo 'tratamento'.
     * @return valor do atributo 'tratamento'
     * @see
     */
    public Tratamento getTratamento() {
        return tratamento;
    }

    /**
     * Nome: setTratamento Registra o valor do atributo 'tratamento'.
     * @param tratamento valor do atributo tratamento
     * @see
     */
    public void setTratamento(Tratamento tratamento) {
        this.tratamento = tratamento;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int hash = 0;
        if (aplicaMedicoPK != null) {
            hash += aplicaMedicoPK.hashCode();
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
        if (!(object instanceof AplicaMedico)) {
            return false;
        }
        AplicaMedico other = (AplicaMedico) object;
        if ((this.aplicaMedicoPK == null && other.aplicaMedicoPK != null)
                || (this.aplicaMedicoPK != null && !this.aplicaMedicoPK
                        .equals(other.aplicaMedicoPK))) {
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
        return "br.com.sw2.gac.modelo.AplicaMedico[ aplicaMedicoPK=" + aplicaMedicoPK + " ]";
    }

}
