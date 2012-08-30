package br.com.sw2.gac.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * <b>Descrição: The primary key class for the TblFormaComunica database table.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@Embeddable
public class FormaComunicaPK implements Serializable {
    // default serial version id, required for serializable classes.
    /** Constante serialVersionUID. */
    private static final long serialVersionUID = -1491707411391881062L;

    /** Atributo id forma comunica. */
    @Column(unique = true, nullable = false)
    private int idFormaComunica;

    /** Atributo id contato. */
    @Column(name = "IdContato", unique = true, nullable = false)
    private int idContato;

    /**
     * Construtor Padrao Instancia um novo objeto FormaComunicaPK.
     */
    public FormaComunicaPK() {
    }

    /**
     * Nome: getIdFormaComunica Recupera o valor do atributo 'idFormaComunica'.
     * @return valor do atributo 'idFormaComunica'
     * @see
     */
    public int getIdFormaComunica() {
        return this.idFormaComunica;
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
        return this.idContato;
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
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FormaComunicaPK)) {
            return false;
        }
        FormaComunicaPK castOther = (FormaComunicaPK) other;
        return (this.idFormaComunica == castOther.idFormaComunica)
                && (this.idContato == castOther.idContato);
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int hash = 17;
        hash = hash * prime + this.idFormaComunica;
        hash = hash * prime + this.idContato;

        return hash;
    }
}