package br.com.sw2.gac.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * <b>Descrição: The persistent class for the TblFormaComunica database table.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@Entity
@Table(name = "TblFormaComunica")
public class FormaComunica implements Serializable {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** Atributo id. */
    @EmbeddedId
    private FormaComunicaPK id;

    /** Atributo fone contato. */
    @Column()
    private String foneContato;

    /** Atributo mail contato. */
    @Column()
    private String mailContato;

    /** Atributo tp contato. */
    @Column()
    private String tpContato;

    // bi-directional many-to-one association to TblContato
    /** Atributo tbl contato. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdContato", nullable = false, insertable = false, updatable = false)
    private Contato tblContato;

    /**
     * Construtor Padrao Instancia um novo objeto FormaComunica.
     */
    public FormaComunica() {
    }

    /**
     * Nome: getId Recupera o valor do atributo 'id'.
     * @return valor do atributo 'id'
     * @see
     */
    public FormaComunicaPK getId() {
        return this.id;
    }

    /**
     * Nome: setId Registra o valor do atributo 'id'.
     * @param id valor do atributo id
     * @see
     */
    public void setId(FormaComunicaPK id) {
        this.id = id;
    }

    /**
     * Nome: getFoneContato Recupera o valor do atributo 'foneContato'.
     * @return valor do atributo 'foneContato'
     * @see
     */
    public String getFoneContato() {
        return this.foneContato;
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
        return this.mailContato;
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
     * Nome: getTpContato Recupera o valor do atributo 'tpContato'.
     * @return valor do atributo 'tpContato'
     * @see
     */
    public String getTpContato() {
        return this.tpContato;
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
     * Nome: getTblContato Recupera o valor do atributo 'tblContato'.
     * @return valor do atributo 'tblContato'
     * @see
     */
    public Contato getTblContato() {
        return this.tblContato;
    }

    /**
     * Nome: setTblContato Registra o valor do atributo 'tblContato'.
     * @param tblContato valor do atributo tbl contato
     * @see
     */
    public void setTblContato(Contato tblContato) {
        this.tblContato = tblContato;
    }

}