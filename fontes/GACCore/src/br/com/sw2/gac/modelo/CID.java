package br.com.sw2.gac.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * <b>Descrição:</b> <br>
 * .
 * @author rogerio
 */
@Entity
@Table(name = "tblcid")
@NamedQueries({ @NamedQuery(name = "CID.findAll", query = "SELECT c FROM CID c") })
public class CID implements Serializable {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** Atributo cd cid. */
    @Id
    @Basic(optional = false)
    @Column(name = "cdCID")
    private String cdCID;

    /** Atributo nm doenca. */
    @Column(name = "nmDoenca")
    private String nmDoenca;

    /** Atributo cliente list. */
    @ManyToMany (mappedBy = "cIDList")
    private List<Cliente> clienteList;

    /** Atributo cd tipo doenca. */
    @JoinColumn(name = "cdTipoDoenca", referencedColumnName = "cdTipoDoenca")
    @ManyToOne(optional = false)
    private TipoDoenca cdTipoDoenca;

    /**
     * Construtor Padrao Instancia um novo objeto CID.
     */
    public CID() {
        super();
    }

    /**
     * Nome: getCdCID Recupera o valor do atributo 'cdCID'.
     * @return valor do atributo 'cdCID'
     * @see
     */
    public String getCdCID() {
        return cdCID;
    }

    /**
     * Nome: setCdCID Registra o valor do atributo 'cdCID'.
     * @param cdCID valor do atributo cd cid
     * @see
     */
    public void setCdCID(String cdCID) {
        this.cdCID = cdCID;
    }

    /**
     * Nome: getNmDoenca Recupera o valor do atributo 'nmDoenca'.
     * @return valor do atributo 'nmDoenca'
     * @see
     */
    public String getNmDoenca() {
        return nmDoenca;
    }

    /**
     * Nome: setNmDoenca Registra o valor do atributo 'nmDoenca'.
     * @param nmDoenca valor do atributo nm doenca
     * @see
     */
    public void setNmDoenca(String nmDoenca) {
        this.nmDoenca = nmDoenca;
    }

    /**
     * Nome: getClienteList Recupera o valor do atributo 'clienteList'.
     * @return valor do atributo 'clienteList'
     * @see
     */
    public List<Cliente> getClienteList() {
        return clienteList;
    }

    /**
     * Nome: setClienteList Registra o valor do atributo 'clienteList'.
     * @param clienteList valor do atributo cliente list
     * @see
     */
    public void setClienteList(List<Cliente> clienteList) {
        this.clienteList = clienteList;
    }

    /**
     * Nome: getCdTipoDoenca Recupera o valor do atributo 'cdTipoDoenca'.
     * @return valor do atributo 'cdTipoDoenca'
     * @see
     */
    public TipoDoenca getCdTipoDoenca() {
        return cdTipoDoenca;
    }

    /**
     * Nome: setCdTipoDoenca Registra o valor do atributo 'cdTipoDoenca'.
     * @param cdTipoDoenca valor do atributo cd tipo doenca
     * @see
     */
    public void setCdTipoDoenca(TipoDoenca cdTipoDoenca) {
        this.cdTipoDoenca = cdTipoDoenca;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cdCID != null ? cdCID.hashCode() : 0);
        return hash;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof CID)) {
            return false;
        }
        CID other = (CID) object;
        if ((this.cdCID == null && other.cdCID != null)
            || (this.cdCID != null && !this.cdCID.equals(other.cdCID))) {
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
        return "entity.CID[ cdCID=" + cdCID + " ]";
    }

}
