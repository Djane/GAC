/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sw2.gac.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * <b>Descrição:</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@Entity
@Table(name = "tbltipodoenca")
@NamedQueries({ @NamedQuery(name = "TipoDoenca.findAll", query = "SELECT t FROM TipoDoenca t") })
public class TipoDoenca implements Serializable {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** Atributo cd tipo doenca. */
    @Id
    @Basic(optional = false)
    @Column(name = "cdTipoDoenca")
    private String cdTipoDoenca;

    /** Atributo ds tipo doenca. */
    @Column(name = "dsTipoDoenca")
    private String dsTipoDoenca;

    /** Atributo cid list. */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cdTipoDoenca")
    private List<CID> cidList;

    /**
     * Construtor Padrao Instancia um novo objeto TipoDoenca.
     */
    public TipoDoenca() {
    }

    /**
     * Construtor Padrao Instancia um novo objeto TipoDoenca.
     * @param cdTipoDoenca the cd tipo doenca
     */
    public TipoDoenca(String cdTipoDoenca) {
        this.cdTipoDoenca = cdTipoDoenca;
    }

    /**
     * Nome: getCdTipoDoenca Recupera o valor do atributo 'cdTipoDoenca'.
     * @return valor do atributo 'cdTipoDoenca'
     * @see
     */
    public String getCdTipoDoenca() {
        return cdTipoDoenca;
    }

    /**
     * Nome: setCdTipoDoenca Registra o valor do atributo 'cdTipoDoenca'.
     * @param cdTipoDoenca valor do atributo cd tipo doenca
     * @see
     */
    public void setCdTipoDoenca(String cdTipoDoenca) {
        this.cdTipoDoenca = cdTipoDoenca;
    }

    /**
     * Nome: getDsTipoDoenca Recupera o valor do atributo 'dsTipoDoenca'.
     * @return valor do atributo 'dsTipoDoenca'
     * @see
     */
    public String getDsTipoDoenca() {
        return dsTipoDoenca;
    }

    /**
     * Nome: setDsTipoDoenca Registra o valor do atributo 'dsTipoDoenca'.
     * @param dsTipoDoenca valor do atributo ds tipo doenca
     * @see
     */
    public void setDsTipoDoenca(String dsTipoDoenca) {
        this.dsTipoDoenca = dsTipoDoenca;
    }

    /**
     * Nome: getCidList Recupera o valor do atributo 'cidList'.
     * @return valor do atributo 'cidList'
     * @see
     */
    public List<CID> getCidList() {
        return cidList;
    }

    /**
     * Nome: setCidList Registra o valor do atributo 'cidList'.
     * @param cidList valor do atributo cid list
     * @see
     */
    public void setCidList(List<CID> cidList) {
        this.cidList = cidList;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int hash = 0;

        if (cdTipoDoenca != null) {
            hash += cdTipoDoenca.hashCode();
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

        if (!(object instanceof TipoDoenca)) {
            return false;
        }
        TipoDoenca other = (TipoDoenca) object;
        if ((this.cdTipoDoenca == null && other.cdTipoDoenca != null)
                || (this.cdTipoDoenca != null && !this.cdTipoDoenca.equals(other.cdTipoDoenca))) {
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
        return "br.com.sw2.gac.modelo.TipoDoenca[ cdTipoDoenca=" + cdTipoDoenca + " ]";
    }

}
