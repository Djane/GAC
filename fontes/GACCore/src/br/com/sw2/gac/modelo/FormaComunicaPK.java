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
public class FormaComunicaPK implements Serializable {

    /** Atributo id forma comunica. */
    @Basic(optional = false)
    @Column(name = "idFormaComunica")
    private int idFormaComunica;

    /** Atributo id contato. */
    @Basic(optional = false)
    @Column(name = "idContato")
    private int idContato;

    /**
     * Construtor Padrao Instancia um novo objeto FormaComunicaPK.
     */
    public FormaComunicaPK() {
    }

    /**
     * Construtor Padrao Instancia um novo objeto FormaComunicaPK.
     * @param idFormaComunica the id forma comunica
     * @param idContato the id contato
     */
    public FormaComunicaPK(int idFormaComunica, int idContato) {
        this.idFormaComunica = idFormaComunica;
        this.idContato = idContato;
    }

    /**
     * Nome: getIdFormaComunica Recupera o valor do atributo 'idFormaComunica'.
     * @return valor do atributo 'idFormaComunica'
     * @see
     */
    public int getIdFormaComunica() {
        return idFormaComunica;
    }

    /**
     * Nome: setIdFormaComunica Registra o valor do atributo 'idFormaComunica'.
     * @param idFormaComunica valor do atributo id forma comunica
     * @see
     */
    public void setIdFormaComunica(int idFormaComunica) {
        this.idFormaComunica = idFormaComunica;
    }

    /**
     * Nome: getIdContato Recupera o valor do atributo 'idContato'.
     * @return valor do atributo 'idContato'
     * @see
     */
    public int getIdContato() {
        return idContato;
    }

    /**
     * Nome: setIdContato Registra o valor do atributo 'idContato'.
     * @param idContato valor do atributo id contato
     * @see
     */
    public void setIdContato(int idContato) {
        this.idContato = idContato;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idFormaComunica;
        hash += (int) idContato;
        return hash;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object object) {

        if (!(object instanceof FormaComunicaPK)) {
            return false;
        }
        FormaComunicaPK other = (FormaComunicaPK) object;
        if (this.idFormaComunica != other.idFormaComunica) {
            return false;
        }
        if (this.idContato != other.idContato) {
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
        return "br.com.sw2.gac.modelo.FormaComunicaPK[ idFormaComunica=" + idFormaComunica
                + ", idContato=" + idContato + " ]";
    }

}
