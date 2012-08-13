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
@NamedQueries({ @NamedQuery(name = "FormaComunica.findAll", query = "SELECT f FROM FormaComunica f") })
public class FormaComunica implements Serializable {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** Atributo forma comunica pk. */
    @EmbeddedId
    private FormaComunicaPK formaComunicaPK;

    /** Atributo tp contato. */
    @Column(name = "tpContato")
    private String tpContato;

    /** Atributo fone contato. */
    @Column(name = "foneContato")
    private String foneContato;

    /** Atributo mail contato. */
    @Column(name = "mailContato")
    private String mailContato;

    /** Atributo contato. */
    @JoinColumn(name = "idContato", referencedColumnName = "idContato", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Contato contato;

    /**
     * Construtor Padrao Instancia um novo objeto FormaComunica.
     */
    public FormaComunica() {
    }

    /**
     * Construtor Padrao Instancia um novo objeto FormaComunica.
     * @param formaComunicaPK the forma comunica pk
     */
    public FormaComunica(FormaComunicaPK formaComunicaPK) {
        this.formaComunicaPK = formaComunicaPK;
    }

    /**
     * Construtor Padrao Instancia um novo objeto FormaComunica.
     * @param idFormaComunica the id forma comunica
     * @param idContato the id contato
     */
    public FormaComunica(int idFormaComunica, int idContato) {
        this.formaComunicaPK = new FormaComunicaPK(idFormaComunica, idContato);
    }

    /**
     * Nome: getFormaComunicaPK Recupera o valor do atributo 'formaComunicaPK'.
     * @return valor do atributo 'formaComunicaPK'
     * @see
     */
    public FormaComunicaPK getFormaComunicaPK() {
        return formaComunicaPK;
    }

    /**
     * Nome: setFormaComunicaPK Registra o valor do atributo 'formaComunicaPK'.
     * @param formaComunicaPK valor do atributo forma comunica pk
     * @see
     */
    public void setFormaComunicaPK(FormaComunicaPK formaComunicaPK) {
        this.formaComunicaPK = formaComunicaPK;
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

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int hash = 0;
        if (formaComunicaPK != null) {
            hash += formaComunicaPK.hashCode();
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

        if (!(object instanceof FormaComunica)) {
            return false;
        }
        FormaComunica other = (FormaComunica) object;
        if ((this.formaComunicaPK == null && other.formaComunicaPK != null)
                || (this.formaComunicaPK != null && !this.formaComunicaPK
                        .equals(other.formaComunicaPK))) {
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
        return "br.com.sw2.gac.modelo.FormaComunica[ formaComunicaPK=" + formaComunicaPK + " ]";
    }

}
