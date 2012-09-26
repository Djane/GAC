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
 * @author rogerio
 */
@Entity
@Table(name = "tblclientexdispositivo")
@NamedQueries({ @NamedQuery(name = "ClienteDispositivo.findAll", query = "SELECT c FROM ClienteDispositivo c") })
public class ClienteDispositivo implements Serializable {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** Atributo cliente dispositivo pk. */
    @EmbeddedId
    private ClienteDispositivoPK clienteDispositivoPK;

    /** Atributo num dispositivo. */
    @Column(name = "numDispositivo")
    private Integer numDispositivo;

    /** Atributo dispositivo. */
    @JoinColumn(name = "idDispositivo", referencedColumnName = "idDispositivo", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Dispositivo dispositivo;

    /** Atributo cliente. */
    @JoinColumn(name = "nmCPFCliente", referencedColumnName = "nmCPFCliente", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Cliente cliente;

    /**
     * Construtor Padrao Instancia um novo objeto ClienteDispositivo.
     */
    public ClienteDispositivo() {
        super();
    }

    /**
     * Construtor Padrao Instancia um novo objeto ClienteDispositivo.
     * @param clienteDispositivoPK the cliente dispositivo pk
     */
    public ClienteDispositivo(ClienteDispositivoPK clienteDispositivoPK) {
        this.clienteDispositivoPK = clienteDispositivoPK;
    }

    /**
     * Construtor Padrao Instancia um novo objeto ClienteDispositivo.
     * @param nmCPFCliente the nm cpf cliente
     * @param idDispositivo the id dispositivo
     */
    public ClienteDispositivo(String nmCPFCliente, String idDispositivo) {
        this.clienteDispositivoPK = new ClienteDispositivoPK(nmCPFCliente, idDispositivo);
    }

    /**
     * Nome: getClienteDispositivoPK Recupera o valor do atributo 'clienteDispositivoPK'.
     * @return valor do atributo 'clienteDispositivoPK'
     * @see
     */
    public ClienteDispositivoPK getClienteDispositivoPK() {
        return clienteDispositivoPK;
    }

    /**
     * Nome: setClienteDispositivoPK Registra o valor do atributo 'clienteDispositivoPK'.
     * @param clienteDispositivoPK valor do atributo cliente dispositivo pk
     * @see
     */
    public void setClienteDispositivoPK(ClienteDispositivoPK clienteDispositivoPK) {
        this.clienteDispositivoPK = clienteDispositivoPK;
    }

    /**
     * Nome: getNumDispositivo Recupera o valor do atributo 'numDispositivo'.
     * @return valor do atributo 'numDispositivo'
     * @see
     */
    public Integer getNumDispositivo() {
        return numDispositivo;
    }

    /**
     * Nome: setNumDispositivo Registra o valor do atributo 'numDispositivo'.
     * @param numDispositivo valor do atributo num dispositivo
     * @see
     */
    public void setNumDispositivo(Integer numDispositivo) {
        this.numDispositivo = numDispositivo;
    }

    /**
     * Nome: getDispositivo Recupera o valor do atributo 'dispositivo'.
     * @return valor do atributo 'dispositivo'
     * @see
     */
    public Dispositivo getDispositivo() {
        return dispositivo;
    }

    /**
     * Nome: setDispositivo Registra o valor do atributo 'dispositivo'.
     * @param dispositivo valor do atributo dispositivo
     * @see
     */
    public void setDispositivo(Dispositivo dispositivo) {
        this.dispositivo = dispositivo;
    }

    /**
     * Nome: getCliente Recupera o valor do atributo 'cliente'.
     * @return valor do atributo 'cliente'
     * @see
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Nome: setCliente Registra o valor do atributo 'cliente'.
     * @param cliente valor do atributo cliente
     * @see
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clienteDispositivoPK != null ? clienteDispositivoPK.hashCode() : 0);
        return hash;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ClienteDispositivo)) {
            return false;
        }
        ClienteDispositivo other = (ClienteDispositivo) object;
        if ((this.clienteDispositivoPK == null && other.clienteDispositivoPK != null)
            || (this.clienteDispositivoPK != null && !this.clienteDispositivoPK
                .equals(other.clienteDispositivoPK))) {
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
        return "entity.ClienteDispositivo[ clienteDispositivoPK=" + clienteDispositivoPK + " ]";
    }

}
