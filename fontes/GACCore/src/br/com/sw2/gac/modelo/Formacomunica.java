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
 *
 * @author rogerio
 */
@Entity
@Table(name = "tblformacomunica")
@NamedQueries({
    @NamedQuery(name = "Formacomunica.findAll", query = "SELECT f FROM Formacomunica f")})
public class Formacomunica implements Serializable {
    @JoinColumn(name = "IdContato", referencedColumnName = "IdContato", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Contato contato;
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FormacomunicaPK formacomunicaPK;
    @Column(name = "tpContato")
    private String tpContato;
    @Column(name = "foneContato")
    private String foneContato;
    @Column(name = "mailContato")
    private String mailContato;

    public Formacomunica() {
    }

    public Formacomunica(FormacomunicaPK formacomunicaPK) {
        this.formacomunicaPK = formacomunicaPK;
    }

    public Formacomunica(int idFormaComunica, int idContato) {
        this.formacomunicaPK = new FormacomunicaPK(idFormaComunica, idContato);
    }

    public FormacomunicaPK getFormacomunicaPK() {
        return formacomunicaPK;
    }

    public void setFormacomunicaPK(FormacomunicaPK formacomunicaPK) {
        this.formacomunicaPK = formacomunicaPK;
    }

    public String getTpContato() {
        return tpContato;
    }

    public void setTpContato(String tpContato) {
        this.tpContato = tpContato;
    }

    public String getFoneContato() {
        return foneContato;
    }

    public void setFoneContato(String foneContato) {
        this.foneContato = foneContato;
    }

    public String getMailContato() {
        return mailContato;
    }

    public void setMailContato(String mailContato) {
        this.mailContato = mailContato;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (formacomunicaPK != null ? formacomunicaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Formacomunica)) {
            return false;
        }
        Formacomunica other = (Formacomunica) object;
        if ((this.formacomunicaPK == null && other.formacomunicaPK != null) || (this.formacomunicaPK != null && !this.formacomunicaPK.equals(other.formacomunicaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.sw2.gac.modelo.Formacomunica[ formacomunicaPK=" + formacomunicaPK + " ]";
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }
    
}
