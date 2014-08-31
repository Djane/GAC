package br.com.sw2.gac.modelo;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * <b>Descrição:</b> <br>
 * .
 * @author rogerio
 */
@Embeddable
public class ClienteDispositivoPK implements Serializable {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = 6662960675067111665L;

    /** Atributo nm cpf cliente. */
    @Basic(optional = false)
    @Column(name = "nmCPFCliente")
    private String nmCPFCliente;

    /** Atributo id dispositivo. */
    @Basic(optional = false)
    @Column(name = "idDispositivo")
    private String idDispositivo;

    /**
     * Construtor Padrao Instancia um novo objeto ClienteDispositivoPK.
     */
    public ClienteDispositivoPK() {
        super();
    }

    /**
     * Construtor Padrao Instancia um novo objeto ClienteDispositivoPK.
     * @param nmCPFCliente the nm cpf cliente
     * @param idDispositivo the id dispositivo
     */
    public ClienteDispositivoPK(String nmCPFCliente, String idDispositivo) {
        this.nmCPFCliente = nmCPFCliente;
        this.idDispositivo = idDispositivo;
    }

    /**
     * Nome: getNmCPFCliente Recupera o valor do atributo 'nmCPFCliente'.
     * @return valor do atributo 'nmCPFCliente'
     * @see
     */
    public String getNmCPFCliente() {
        return nmCPFCliente;
    }

    /**
     * Nome: setNmCPFCliente Registra o valor do atributo 'nmCPFCliente'.
     * @param nmCPFCliente valor do atributo nm cpf cliente
     * @see
     */
    public void setNmCPFCliente(String nmCPFCliente) {
        this.nmCPFCliente = nmCPFCliente;
    }

    /**
     * Nome: getIdDispositivo Recupera o valor do atributo 'idDispositivo'.
     * @return valor do atributo 'idDispositivo'
     * @see
     */
    public String getIdDispositivo() {
        return idDispositivo;
    }

    /**
     * Nome: setIdDispositivo Registra o valor do atributo 'idDispositivo'.
     * @param idDispositivo valor do atributo id dispositivo
     * @see
     */
    public void setIdDispositivo(String idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nmCPFCliente != null ? nmCPFCliente.hashCode() : 0);
        hash += (idDispositivo != null ? idDispositivo.hashCode() : 0);
        return hash;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ClienteDispositivoPK)) {
            return false;
        }
        ClienteDispositivoPK other = (ClienteDispositivoPK) object;
        if ((this.nmCPFCliente == null && other.nmCPFCliente != null)
            || (this.nmCPFCliente != null && !this.nmCPFCliente.equals(other.nmCPFCliente))) {
            return false;
        }
        if ((this.idDispositivo == null && other.idDispositivo != null)
            || (this.idDispositivo != null && !this.idDispositivo.equals(other.idDispositivo))) {
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
        return "entity.ClienteDispositivoPK[ nmCPFCliente=" + nmCPFCliente + ", idDispositivo="
            + idDispositivo + " ]";
    }

}
