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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author marcelo
 */
@Entity
@Table(name = "TblFormaComunica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FormaComunica.findAll", query = "SELECT f FROM FormaComunica f"),
    @NamedQuery(name = "FormaComunica.findByIdFormaComunica", query = "SELECT f FROM FormaComunica f WHERE f.formaComunicaPK.idFormaComunica = :idFormaComunica"),
    @NamedQuery(name = "FormaComunica.findByIdContato", query = "SELECT f FROM FormaComunica f WHERE f.formaComunicaPK.idContato = :idContato"),
    @NamedQuery(name = "FormaComunica.findByTpContato", query = "SELECT f FROM FormaComunica f WHERE f.tpContato = :tpContato"),
    @NamedQuery(name = "FormaComunica.findByFoneContato", query = "SELECT f FROM FormaComunica f WHERE f.foneContato = :foneContato"),
    @NamedQuery(name = "FormaComunica.findByMailContato", query = "SELECT f FROM FormaComunica f WHERE f.mailContato = :mailContato")})
public class FormaComunica implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FormaComunicaPK formaComunicaPK;
    @Column(name = "tpContato")
    private String tpContato;
    @Column(name = "foneContato")
    private String foneContato;
    @Column(name = "mailContato")
    private String mailContato;
    @JoinColumn(name = "idContato", referencedColumnName = "idContato", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Contato contato;

    public FormaComunica() {
    }

    public FormaComunica(FormaComunicaPK formaComunicaPK) {
        this.formaComunicaPK = formaComunicaPK;
    }

    public FormaComunica(int idFormaComunica, int idContato) {
        this.formaComunicaPK = new FormaComunicaPK(idFormaComunica, idContato);
    }

    public FormaComunicaPK getFormaComunicaPK() {
        return formaComunicaPK;
    }

    public void setFormaComunicaPK(FormaComunicaPK formaComunicaPK) {
        this.formaComunicaPK = formaComunicaPK;
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

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (formaComunicaPK != null ? formaComunicaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FormaComunica)) {
            return false;
        }
        FormaComunica other = (FormaComunica) object;
        if ((this.formaComunicaPK == null && other.formaComunicaPK != null) || (this.formaComunicaPK != null && !this.formaComunicaPK.equals(other.formaComunicaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.sw2.gac.modelo.FormaComunica[ formaComunicaPK=" + formaComunicaPK + " ]";
    }
    
}
