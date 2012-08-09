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
 * <b>Descrição:</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@Embeddable
public class TratamentoPK implements Serializable {

    /** Atributo id tratamento. */
    @Basic(optional = false)
    @Column(name = "IdTratamento")
    private int idTratamento;

    /** Atributo nm cpf cliente. */
    @Basic(optional = false)
    @Column(name = "NmCPFCliente")
    private String nmCPFCliente;

    /**
     * Construtor Padrao Instancia um novo objeto TratamentoPK.
     */
    public TratamentoPK() {
    }

    /**
     * Construtor Padrao Instancia um novo objeto TratamentoPK.
     * @param idTratamento the id tratamento
     * @param nmCPFCliente the nm cpf cliente
     */
    public TratamentoPK(int idTratamento, String nmCPFCliente) {
        this.idTratamento = idTratamento;
        this.nmCPFCliente = nmCPFCliente;
    }

    /**
     * Nome: getIdTratamento Recupera o valor do atributo 'idTratamento'.
     * @return valor do atributo 'idTratamento'
     * @see
     */
    public int getIdTratamento() {
        return idTratamento;
    }

    /**
     * Nome: setIdTratamento Registra o valor do atributo 'idTratamento'.
     * @param idTratamento valor do atributo id tratamento
     * @see
     */
    public void setIdTratamento(int idTratamento) {
        this.idTratamento = idTratamento;
    }

    /**
     * Nome: getNmCPFCliente Recupera o valor do atributo 'nmCPFCliente'.
     * @return valor do atributo 'nmCPFCliente'
     * @see
     */
    public String getNmCPFCliente() {
        return nmCPFCliente;
    }

    /**
     * Nome: setNmCPFCliente Registra o valor do atributo 'nmCPFCliente'.
     * @param nmCPFCliente valor do atributo nm cpf cliente
     * @see
     */
    public void setNmCPFCliente(String nmCPFCliente) {
        this.nmCPFCliente = nmCPFCliente;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idTratamento;

        if (nmCPFCliente != null) {
            hash += nmCPFCliente.hashCode();
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

        if (!(object instanceof TratamentoPK)) {
            return false;
        }
        TratamentoPK other = (TratamentoPK) object;
        if (this.idTratamento != other.idTratamento) {
            return false;
        }
        if ((this.nmCPFCliente == null && other.nmCPFCliente != null)
                || (this.nmCPFCliente != null && !this.nmCPFCliente.equals(other.nmCPFCliente))) {
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
        return "br.com.sw2.gac.modelo.TratamentoPK[ idTratamento=" + idTratamento
                + ", nmCPFCliente=" + nmCPFCliente + " ]";
    }

}
