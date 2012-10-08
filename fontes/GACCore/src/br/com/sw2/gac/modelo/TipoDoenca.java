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

    /** Atributo nm capitulo. */
    @Column(name = "nmCapitulo")
    private Integer nmCapitulo;

    /** Atributo cat inic. */
    @Column(name = "catInic")
    private String catInic;

    /** Atributo cat final. */
    @Column(name = "catFinal")
    private String catFinal;

    /** Atributo cd tipo doenca. */
    @Id
    @Basic(optional = false)
    @Column(name = "cdTipoDoenca")
    private String cdTipoDoenca;

    /** Atributo ds tipo doenca. */
    @Column(name = "dsTipoDoenca")
    private String dsTipoDoenca;

    /** Atributo c id list. */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cdTipoDoenca")
    private List<CID> cIDList;

    /**
     * Construtor Padrao Instancia um novo objeto TipoDoenca.
     */
    public TipoDoenca() {
        super();
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
     * Nome: getCIDList Recupera o valor do atributo 'CIDList'.
     * @return valor do atributo 'CIDList'
     * @see
     */
    public List<CID> getCIDList() {
        return cIDList;
    }

    /**
     * Nome: setCIDList Registra o valor do atributo 'CIDList'.
     * @param cIDList valor do atributo cID list
     * @see
     */
    public void setCIDList(List<CID> cIDList) {
        this.cIDList = cIDList;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cdTipoDoenca != null ? cdTipoDoenca.hashCode() : 0);
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
        return "entity.TipoDoenca[ cdTipoDoenca=" + cdTipoDoenca + " ]";
    }

    /**
     * Nome: getNmCapitulo Recupera o valor do atributo 'nmCapitulo'.
     * @return valor do atributo 'nmCapitulo'
     * @see
     */
    public Integer getNmCapitulo() {
        return nmCapitulo;
    }

    /**
     * Nome: setNmCapitulo Registra o valor do atributo 'nmCapitulo'.
     * @param nmCapitulo valor do atributo nm capitulo
     * @see
     */
    public void setNmCapitulo(Integer nmCapitulo) {
        this.nmCapitulo = nmCapitulo;
    }

    /**
     * Nome: getCatInic Recupera o valor do atributo 'catInic'.
     * @return valor do atributo 'catInic'
     * @see
     */
    public String getCatInic() {
        return catInic;
    }

    /**
     * Nome: setCatInic Registra o valor do atributo 'catInic'.
     * @param catInic valor do atributo cat inic
     * @see
     */
    public void setCatInic(String catInic) {
        this.catInic = catInic;
    }

    /**
     * Nome: getCatFinal Recupera o valor do atributo 'catFinal'.
     * @return valor do atributo 'catFinal'
     * @see
     */
    public String getCatFinal() {
        return catFinal;
    }

    /**
     * Nome: setCatFinal Registra o valor do atributo 'catFinal'.
     * @param catFinal valor do atributo cat final
     * @see
     */
    public void setCatFinal(String catFinal) {
        this.catFinal = catFinal;
    }

}
