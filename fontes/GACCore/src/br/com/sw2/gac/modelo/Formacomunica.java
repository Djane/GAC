/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sw2.gac.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
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
@Table(name = "tblformacomunica")
@NamedQueries({ @NamedQuery(name = "Formacomunica.findAll", query = "SELECT f FROM Formacomunica f") })
public class Formacomunica implements Serializable {

    /** Atributo contato. */
    @JoinColumn(name = "IdContato", referencedColumnName = "IdContato", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Contato contato;

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** Atributo formacomunica pk. */
    @EmbeddedId
    private FormacomunicaPK formacomunicaPK;

    /** Atributo tp contato. */
    @Column(name = "tpContato")
    private String tpContato;

    /** Atributo fone contato. */
    @Column(name = "foneContato")
    private String foneContato;

    /** Atributo mail contato. */
    @Column(name = "mailContato")
    private String mailContato;

    /**
     * Construtor Padrao Instancia um novo objeto Formacomunica.
     */
    public Formacomunica() {
    }

    /**
     * Construtor Padrao Instancia um novo objeto Formacomunica.
     * @param formacomunicaPK the formacomunica pk
     */
    public Formacomunica(FormacomunicaPK formacomunicaPK) {
        this.formacomunicaPK = formacomunicaPK;
    }

    /**
     * Construtor Padrao Instancia um novo objeto Formacomunica.
     * @param idFormaComunica the id forma comunica
     * @param idContato the id contato
     */
    public Formacomunica(int idFormaComunica, int idContato) {
        this.formacomunicaPK = new FormacomunicaPK(idFormaComunica, idContato);
    }

    /**
     * Nome: getFormacomunicaPK Recupera o valor do atributo 'formacomunicaPK'.
     * @return valor do atributo 'formacomunicaPK'
     * @see
     */
    public FormacomunicaPK getFormacomunicaPK() {
        return formacomunicaPK;
    }

    /**
     * Nome: setFormacomunicaPK Registra o valor do atributo 'formacomunicaPK'.
     * @param formacomunicaPK valor do atributo formacomunica pk
     * @see
     */
    public void setFormacomunicaPK(FormacomunicaPK formacomunicaPK) {
        this.formacomunicaPK = formacomunicaPK;
    }

    /**
     * Nome: getTpContato Recupera o valor do atributo 'tpContato'.
     * @return valor do atributo 'tpContato'
     * @see
     */
    public String getTpContato() {
        return tpContato;
    }

    /**
     * Nome: setTpContato Registra o valor do atributo 'tpContato'.
     * @param tpContato valor do atributo tp contato
     * @see
     */
    public void setTpContato(String tpContato) {
        this.tpContato = tpContato;
    }

    /**
     * Nome: getFoneContato Recupera o valor do atributo 'foneContato'.
     * @return valor do atributo 'foneContato'
     * @see
     */
    public String getFoneContato() {
        return foneContato;
    }

    /**
     * Nome: setFoneContato Registra o valor do atributo 'foneContato'.
     * @param foneContato valor do atributo fone contato
     * @see
     */
    public void setFoneContato(String foneContato) {
        this.foneContato = foneContato;
    }

    /**
     * Nome: getMailContato Recupera o valor do atributo 'mailContato'.
     * @return valor do atributo 'mailContato'
     * @see
     */
    public String getMailContato() {
        return mailContato;
    }

    /**
     * Nome: setMailContato Registra o valor do atributo 'mailContato'.
     * @param mailContato valor do atributo mail contato
     * @see
     */
    public void setMailContato(String mailContato) {
        this.mailContato = mailContato;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int hash = 0;

        if (formacomunicaPK != null) {
            hash = formacomunicaPK.hashCode();
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

        if (!(object instanceof Formacomunica)) {
            return false;
        }
        Formacomunica other = (Formacomunica) object;
        if ((this.formacomunicaPK == null && other.formacomunicaPK != null)
                || (this.formacomunicaPK != null && !this.formacomunicaPK
                        .equals(other.formacomunicaPK))) {
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
        return "br.com.sw2.gac.modelo.Formacomunica[ formacomunicaPK=" + formacomunicaPK + " ]";
    }

    /**
     * Nome: getContato Recupera o valor do atributo 'contato'.
     * @return valor do atributo 'contato'
     * @see
     */
    public Contato getContato() {
        return contato;
    }

    /**
     * Nome: setContato Registra o valor do atributo 'contato'.
     * @param contato valor do atributo contato
     * @see
     */
    public void setContato(Contato contato) {
        this.contato = contato;
    }

}
