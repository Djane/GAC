package br.com.sw2.gac.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * <b>Descrição:</b> <br>
 * .
 * @author rogerio
 */
@Entity
@Table(name = "tblcontato")
@NamedQueries({ @NamedQuery(name = "Contato.findAll", query = "SELECT c FROM Contato c") })
public class Contato implements Serializable {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** Atributo id contato. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idContato")
    private Integer idContato;

    /** Atributo nome contato. */
    @Column(name = "nomeContato")
    private String nomeContato;

    /** Atributo grau parentesco. */
    @Column(name = "grauParentesco")
    private String grauParentesco;

    /** Atributo end contato. */
    @Column(name = "endContato")
    private String endContato;

    /** Atributo bai contato. */
    @Column(name = "baiContato")
    private String baiContato;

    /** Atributo cid contato. */
    @Column(name = "cidContato")
    private String cidContato;

    /** Atributo cep contato. */
    @Column(name = "cepContato")
    private String cepContato;

    /** Atributo estado contato. */
    @Column(name = "estadoContato")
    private String estadoContato;

    /** Atributo dta nascimento. */
    @Column(name = "dtaNascimento")
    @Temporal(TemporalType.DATE)
    private Date dtaNascimento;

    /** Atributo sqa chamada. */
    @Column(name = "sqaChamada")
    private Integer sqaChamada;

    /** Atributo contratante. */
    @Column(name = "contratante")
    private String contratante;

    /** Atributo forma comunica list. */
    @OneToMany(mappedBy = "idContato", cascade = {CascadeType.PERSIST })
    private List<FormaComunica> formaComunicaList;

    /** Atributo login. */
    @JoinColumn(name = "login", referencedColumnName = "login")
    @ManyToOne(optional = false)
    private Usuario login;

    /** Atributo nm cpf cliente. */
    @JoinColumn(name = "nmCPFCliente", referencedColumnName = "nmCPFCliente")
    @ManyToOne(optional = false)
    private Cliente nmCPFCliente;

    /** Atributo acionamento list. */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idContato")
    private List<Acionamento> acionamentoList;

    /**
     * Construtor Padrao Instancia um novo objeto Contato.
     */
    public Contato() {
        super();
    }

    /**
     * Construtor Padrao Instancia um novo objeto Contato.
     * @param idContato the id contato
     */
    public Contato(Integer idContato) {
        this.idContato = idContato;
    }

    /**
     * Nome: getIdContato Recupera o valor do atributo 'idContato'.
     * @return valor do atributo 'idContato'
     * @see
     */
    public Integer getIdContato() {
        return idContato;
    }

    /**
     * Nome: setIdContato Registra o valor do atributo 'idContato'.
     * @param idContato valor do atributo id contato
     * @see
     */
    public void setIdContato(Integer idContato) {
        this.idContato = idContato;
    }

    /**
     * Nome: getNomeContato Recupera o valor do atributo 'nomeContato'.
     * @return valor do atributo 'nomeContato'
     * @see
     */
    public String getNomeContato() {
        return nomeContato;
    }

    /**
     * Nome: setNomeContato Registra o valor do atributo 'nomeContato'.
     * @param nomeContato valor do atributo nome contato
     * @see
     */
    public void setNomeContato(String nomeContato) {
        this.nomeContato = nomeContato;
    }

    /**
     * Nome: getGrauParentesco Recupera o valor do atributo 'grauParentesco'.
     * @return valor do atributo 'grauParentesco'
     * @see
     */
    public String getGrauParentesco() {
        return grauParentesco;
    }

    /**
     * Nome: setGrauParentesco Registra o valor do atributo 'grauParentesco'.
     * @param grauParentesco valor do atributo grau parentesco
     * @see
     */
    public void setGrauParentesco(String grauParentesco) {
        this.grauParentesco = grauParentesco;
    }

    /**
     * Nome: getEndContato Recupera o valor do atributo 'endContato'.
     * @return valor do atributo 'endContato'
     * @see
     */
    public String getEndContato() {
        return endContato;
    }

    /**
     * Nome: setEndContato Registra o valor do atributo 'endContato'.
     * @param endContato valor do atributo end contato
     * @see
     */
    public void setEndContato(String endContato) {
        this.endContato = endContato;
    }

    /**
     * Nome: getBaiContato Recupera o valor do atributo 'baiContato'.
     * @return valor do atributo 'baiContato'
     * @see
     */
    public String getBaiContato() {
        return baiContato;
    }

    /**
     * Nome: setBaiContato Registra o valor do atributo 'baiContato'.
     * @param baiContato valor do atributo bai contato
     * @see
     */
    public void setBaiContato(String baiContato) {
        this.baiContato = baiContato;
    }

    /**
     * Nome: getCidContato Recupera o valor do atributo 'cidContato'.
     * @return valor do atributo 'cidContato'
     * @see
     */
    public String getCidContato() {
        return cidContato;
    }

    /**
     * Nome: setCidContato Registra o valor do atributo 'cidContato'.
     * @param cidContato valor do atributo cid contato
     * @see
     */
    public void setCidContato(String cidContato) {
        this.cidContato = cidContato;
    }

    /**
     * Nome: getCepContato Recupera o valor do atributo 'cepContato'.
     * @return valor do atributo 'cepContato'
     * @see
     */
    public String getCepContato() {
        return cepContato;
    }

    /**
     * Nome: setCepContato Registra o valor do atributo 'cepContato'.
     * @param cepContato valor do atributo cep contato
     * @see
     */
    public void setCepContato(String cepContato) {
        this.cepContato = cepContato;
    }

    /**
     * Nome: getEstadoContato Recupera o valor do atributo 'estadoContato'.
     * @return valor do atributo 'estadoContato'
     * @see
     */
    public String getEstadoContato() {
        return estadoContato;
    }

    /**
     * Nome: setEstadoContato Registra o valor do atributo 'estadoContato'.
     * @param estadoContato valor do atributo estado contato
     * @see
     */
    public void setEstadoContato(String estadoContato) {
        this.estadoContato = estadoContato;
    }

    /**
     * Nome: getDtaNascimento Recupera o valor do atributo 'dtaNascimento'.
     * @return valor do atributo 'dtaNascimento'
     * @see
     */
    public Date getDtaNascimento() {
        return dtaNascimento;
    }

    /**
     * Nome: setDtaNascimento Registra o valor do atributo 'dtaNascimento'.
     * @param dtaNascimento valor do atributo dta nascimento
     * @see
     */
    public void setDtaNascimento(Date dtaNascimento) {
        this.dtaNascimento = dtaNascimento;
    }

    /**
     * Nome: getSqaChamada Recupera o valor do atributo 'sqaChamada'.
     * @return valor do atributo 'sqaChamada'
     * @see
     */
    public Integer getSqaChamada() {
        return sqaChamada;
    }

    /**
     * Nome: setSqaChamada Registra o valor do atributo 'sqaChamada'.
     * @param sqaChamada valor do atributo sqa chamada
     * @see
     */
    public void setSqaChamada(Integer sqaChamada) {
        this.sqaChamada = sqaChamada;
    }

    /**
     * Nome: getContratante Recupera o valor do atributo 'contratante'.
     * @return valor do atributo 'contratante'
     * @see
     */
    public String getContratante() {
        return contratante;
    }

    /**
     * Nome: setContratante Registra o valor do atributo 'contratante'.
     * @param contratante valor do atributo contratante
     * @see
     */
    public void setContratante(String contratante) {
        this.contratante = contratante;
    }

    /**
     * Nome: getFormaComunicaList Recupera o valor do atributo 'formaComunicaList'.
     * @return valor do atributo 'formaComunicaList'
     * @see
     */
    public List<FormaComunica> getFormaComunicaList() {
        return formaComunicaList;
    }

    /**
     * Nome: setFormaComunicaList Registra o valor do atributo 'formaComunicaList'.
     * @param formaComunicaList valor do atributo forma comunica list
     * @see
     */
    public void setFormaComunicaList(List<FormaComunica> formaComunicaList) {
        this.formaComunicaList = formaComunicaList;
    }

    /**
     * Nome: getLogin Recupera o valor do atributo 'login'.
     * @return valor do atributo 'login'
     * @see
     */
    public Usuario getLogin() {
        return login;
    }

    /**
     * Nome: setLogin Registra o valor do atributo 'login'.
     * @param login valor do atributo login
     * @see
     */
    public void setLogin(Usuario login) {
        this.login = login;
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

    /**
     * Nome: getAcionamentoList Recupera o valor do atributo 'acionamentoList'.
     * @return valor do atributo 'acionamentoList'
     * @see
     */
    public List<Acionamento> getAcionamentoList() {
        return acionamentoList;
    }

    /**
     * Nome: setAcionamentoList Registra o valor do atributo 'acionamentoList'.
     * @param acionamentoList valor do atributo acionamento list
     * @see
     */
    public void setAcionamentoList(List<Acionamento> acionamentoList) {
        this.acionamentoList = acionamentoList;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idContato != null ? idContato.hashCode() : 0);
        return hash;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Contato)) {
            return false;
        }
        Contato other = (Contato) object;
        if ((this.idContato == null && other.idContato != null)
            || (this.idContato != null && !this.idContato.equals(other.idContato))) {
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
        return "entity.Contato[ idContato=" + idContato + " ]";
    }

}
