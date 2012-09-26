package br.com.sw2.gac.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "tblformacomunica")
@NamedQueries({ @NamedQuery(name = "FormaComunica.findAll", query = "SELECT f FROM FormaComunica f") })
public class FormaComunica implements Serializable {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** Atributo id forma comunica. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idFormaComunica")
    private Integer idFormaComunica;

    /** Atributo tp contato. */
    @Column(name = "tpContato")
    private String tpContato;

    /** Atributo fone contato. */
    @Column(name = "foneContato")
    private String foneContato;

    /** Atributo mail contato. */
    @Column(name = "mailContato")
    private String mailContato;

    /** Atributo id contato. */
    @JoinColumn(name = "idContato", referencedColumnName = "idContato")
    @ManyToOne
    private Contato idContato;

    /** Atributo nm cpf cliente. */
    @JoinColumn(name = "nmCPFCliente", referencedColumnName = "nmCPFCliente")
    @ManyToOne
    private Cliente nmCPFCliente;

    /**
     * Construtor Padrao Instancia um novo objeto FormaComunica.
     */
    public FormaComunica() {
        super();
    }

    /**
     * Construtor Padrao Instancia um novo objeto FormaComunica.
     * @param idFormaComunica the id forma comunica
     */
    public FormaComunica(Integer idFormaComunica) {
        this.idFormaComunica = idFormaComunica;
    }

    /**
     * Nome: getIdFormaComunica Recupera o valor do atributo 'idFormaComunica'.
     * @return valor do atributo 'idFormaComunica'
     * @see
     */
    public Integer getIdFormaComunica() {
        return idFormaComunica;
    }

    /**
     * Nome: setIdFormaComunica Registra o valor do atributo 'idFormaComunica'.
     * @param idFormaComunica valor do atributo id forma comunica
     * @see
     */
    public void setIdFormaComunica(Integer idFormaComunica) {
        this.idFormaComunica = idFormaComunica;
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
     * Nome: getIdContato Recupera o valor do atributo 'idContato'.
     * @return valor do atributo 'idContato'
     * @see
     */
    public Contato getIdContato() {
        return idContato;
    }

    /**
     * Nome: setIdContato Registra o valor do atributo 'idContato'.
     * @param idContato valor do atributo id contato
     * @see
     */
    public void setIdContato(Contato idContato) {
        this.idContato = idContato;
    }

    /**
     * Nome: getNmCPFCliente Recupera o valor do atributo 'nmCPFCliente'.
     * @return valor do atributo 'nmCPFCliente'
     * @see
     */
    public Cliente getNmCPFCliente() {
        return nmCPFCliente;
    }

    /**
     * Nome: setNmCPFCliente Registra o valor do atributo 'nmCPFCliente'.
     * @param nmCPFCliente valor do atributo nm cpf cliente
     * @see
     */
    public void setNmCPFCliente(Cliente nmCPFCliente) {
        this.nmCPFCliente = nmCPFCliente;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFormaComunica != null ? idFormaComunica.hashCode() : 0);
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
        if ((this.idFormaComunica == null && other.idFormaComunica != null)
            || (this.idFormaComunica != null && !this.idFormaComunica.equals(other.idFormaComunica))) {
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
        return "entity.FormaComunica[ idFormaComunica=" + idFormaComunica + " ]";
    }

}
