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
 *
 * @author rogerio
 */
@Embeddable
public class FormacomunicaPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idFormaComunica")
    private int idFormaComunica;
    @Basic(optional = false)
    @Column(name = "IdContato")
    private int idContato;

    public FormacomunicaPK() {
    }

    public FormacomunicaPK(int idFormaComunica, int idContato) {
        this.idFormaComunica = idFormaComunica;
        this.idContato = idContato;
    }

    public int getIdFormaComunica() {
        return idFormaComunica;
    }

    public void setIdFormaComunica(int idFormaComunica) {
        this.idFormaComunica = idFormaComunica;
    }

    public int getIdContato() {
        return idContato;
    }

    public void setIdContato(int idContato) {
        this.idContato = idContato;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idFormaComunica;
        hash += (int) idContato;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FormacomunicaPK)) {
            return false;
        }
        FormacomunicaPK other = (FormacomunicaPK) object;
        if (this.idFormaComunica != other.idFormaComunica) {
            return false;
        }
        if (this.idContato != other.idContato) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.sw2.gac.modelo.FormacomunicaPK[ idFormaComunica=" + idFormaComunica + ", idContato=" + idContato + " ]";
    }
    
}
