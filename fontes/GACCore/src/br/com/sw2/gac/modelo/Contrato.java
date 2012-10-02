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
@Table(name = "tblcontrato")
@NamedQueries({ @NamedQuery(name = "Contrato.findAll", query = "SELECT c FROM Contrato c") })
public class Contrato implements Serializable {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** Atributo nm contrato. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "nmContrato")
    private Integer nmContrato;

    /** Atributo dt inicio validade. */
    @Basic(optional = false)
    @Column(name = "dtInicioValidade")
    @Temporal(TemporalType.DATE)
    private Date dtInicioValidade;

    /** Atributo dt final validade. */
    @Column(name = "dtFinalValidade")
    @Temporal(TemporalType.DATE)
    private Date dtFinalValidade;

    /** Atributo dt suspensao. */
    @Column(name = "dtSuspensao")
    @Temporal(TemporalType.DATE)
    private Date dtSuspensao;

    /** Atributo nm cpf contratante. */
    @Basic(optional = false)
    @Column(name = "nmCPFContratante")
    private String nmCPFContratante;

    /** Atributo nm nome contratante. */
    @Basic(optional = false)
    @Column(name = "nmNomeContratante")
    private String nmNomeContratante;

    /** Atributo dt nasc contratante. */
    @Column(name = "dtNascContratante")
    @Temporal(TemporalType.DATE)
    private Date dtNascContratante;

    /** Atributo nm rg contratante. */
    @Column(name = "nmRGContratante")
    private String nmRGContratante;

    /** Atributo dt prox atual. */
    @Basic(optional = false)
    @Column(name = "dtProxAtual")
    @Temporal(TemporalType.DATE)
    private Date dtProxAtual;

    /** Atributo cliente list. */
    @OneToMany(mappedBy = "nmContrato", cascade = CascadeType.PERSIST)
    private List<Cliente> clienteList;

    /** Atributo login. */
    @JoinColumn(name = "login", referencedColumnName = "login")
    @ManyToOne(optional = false)
    private Usuario login;

    /** Atributo id servico. */
    @JoinColumn(name = "idServico", referencedColumnName = "idServico")
    @ManyToOne(optional = false)
    private PacoteServico idServico;

    /**
     * Construtor Padrao Instancia um novo objeto Contrato.
     */
    public Contrato() {
        super();
    }

    /**
     * Construtor Padrao Instancia um novo objeto Contrato.
     * @param nmContrato the nm contrato
     */
    public Contrato(Integer nmContrato) {
        this.nmContrato = nmContrato;
    }

    /**
     * Construtor Padrao Instancia um novo objeto Contrato.
     * @param nmContrato the nm contrato
     * @param dtInicioValidade the dt inicio validade
     * @param nmCPFContratante the nm cpf contratante
     * @param nmNomeContratante the nm nome contratante
     * @param dtProxAtual the dt prox atual
     */
    public Contrato(Integer nmContrato, Date dtInicioValidade, String nmCPFContratante,
        String nmNomeContratante, Date dtProxAtual) {
        this.nmContrato = nmContrato;
        this.dtInicioValidade = dtInicioValidade;
        this.nmCPFContratante = nmCPFContratante;
        this.nmNomeContratante = nmNomeContratante;
        this.dtProxAtual = dtProxAtual;
    }

    /**
     * Nome: getNmContrato Recupera o valor do atributo 'nmContrato'.
     * @return valor do atributo 'nmContrato'
     * @see
     */
    public Integer getNmContrato() {
        return nmContrato;
    }

    /**
     * Nome: setNmContrato Registra o valor do atributo 'nmContrato'.
     * @param nmContrato valor do atributo nm contrato
     * @see
     */
    public void setNmContrato(Integer nmContrato) {
        this.nmContrato = nmContrato;
    }

    /**
     * Nome: getDtInicioValidade Recupera o valor do atributo 'dtInicioValidade'.
     * @return valor do atributo 'dtInicioValidade'
     * @see
     */
    public Date getDtInicioValidade() {
        return dtInicioValidade;
    }

    /**
     * Nome: setDtInicioValidade Registra o valor do atributo 'dtInicioValidade'.
     * @param dtInicioValidade valor do atributo dt inicio validade
     * @see
     */
    public void setDtInicioValidade(Date dtInicioValidade) {
        this.dtInicioValidade = dtInicioValidade;
    }

    /**
     * Nome: getDtFinalValidade Recupera o valor do atributo 'dtFinalValidade'.
     * @return valor do atributo 'dtFinalValidade'
     * @see
     */
    public Date getDtFinalValidade() {
        return dtFinalValidade;
    }

    /**
     * Nome: setDtFinalValidade Registra o valor do atributo 'dtFinalValidade'.
     * @param dtFinalValidade valor do atributo dt final validade
     * @see
     */
    public void setDtFinalValidade(Date dtFinalValidade) {
        this.dtFinalValidade = dtFinalValidade;
    }

    /**
     * Nome: getDtSuspensao Recupera o valor do atributo 'dtSuspensao'.
     * @return valor do atributo 'dtSuspensao'
     * @see
     */
    public Date getDtSuspensao() {
        return dtSuspensao;
    }

    /**
     * Nome: setDtSuspensao Registra o valor do atributo 'dtSuspensao'.
     * @param dtSuspensao valor do atributo dt suspensao
     * @see
     */
    public void setDtSuspensao(Date dtSuspensao) {
        this.dtSuspensao = dtSuspensao;
    }

    /**
     * Nome: getNmCPFContratante Recupera o valor do atributo 'nmCPFContratante'.
     * @return valor do atributo 'nmCPFContratante'
     * @see
     */
    public String getNmCPFContratante() {
        return nmCPFContratante;
    }

    /**
     * Nome: setNmCPFContratante Registra o valor do atributo 'nmCPFContratante'.
     * @param nmCPFContratante valor do atributo nm cpf contratante
     * @see
     */
    public void setNmCPFContratante(String nmCPFContratante) {
        this.nmCPFContratante = nmCPFContratante;
    }

    /**
     * Nome: getNmNomeContratante Recupera o valor do atributo 'nmNomeContratante'.
     * @return valor do atributo 'nmNomeContratante'
     * @see
     */
    public String getNmNomeContratante() {
        return nmNomeContratante;
    }

    /**
     * Nome: setNmNomeContratante Registra o valor do atributo 'nmNomeContratante'.
     * @param nmNomeContratante valor do atributo nm nome contratante
     * @see
     */
    public void setNmNomeContratante(String nmNomeContratante) {
        this.nmNomeContratante = nmNomeContratante;
    }

    /**
     * Nome: getDtNascContratante Recupera o valor do atributo 'dtNascContratante'.
     * @return valor do atributo 'dtNascContratante'
     * @see
     */
    public Date getDtNascContratante() {
        return dtNascContratante;
    }

    /**
     * Nome: setDtNascContratante Registra o valor do atributo 'dtNascContratante'.
     * @param dtNascContratante valor do atributo dt nasc contratante
     * @see
     */
    public void setDtNascContratante(Date dtNascContratante) {
        this.dtNascContratante = dtNascContratante;
    }

    /**
     * Nome: getNmRGContratante Recupera o valor do atributo 'nmRGContratante'.
     * @return valor do atributo 'nmRGContratante'
     * @see
     */
    public String getNmRGContratante() {
        return nmRGContratante;
    }

    /**
     * Nome: setNmRGContratante Registra o valor do atributo 'nmRGContratante'.
     * @param nmRGContratante valor do atributo nm rg contratante
     * @see
     */
    public void setNmRGContratante(String nmRGContratante) {
        this.nmRGContratante = nmRGContratante;
    }

    /**
     * Nome: getDtProxAtual Recupera o valor do atributo 'dtProxAtual'.
     * @return valor do atributo 'dtProxAtual'
     * @see
     */
    public Date getDtProxAtual() {
        return dtProxAtual;
    }

    /**
     * Nome: setDtProxAtual Registra o valor do atributo 'dtProxAtual'.
     * @param dtProxAtual valor do atributo dt prox atual
     * @see
     */
    public void setDtProxAtual(Date dtProxAtual) {
        this.dtProxAtual = dtProxAtual;
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
     * Nome: getIdServico Recupera o valor do atributo 'idServico'.
     * @return valor do atributo 'idServico'
     * @see
     */
    public PacoteServico getIdServico() {
        return idServico;
    }

    /**
     * Nome: setIdServico Registra o valor do atributo 'idServico'.
     * @param idServico valor do atributo id servico
     * @see
     */
    public void setIdServico(PacoteServico idServico) {
        this.idServico = idServico;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nmContrato != null ? nmContrato.hashCode() : 0);
        return hash;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Contrato)) {
            return false;
        }
        Contrato other = (Contrato) object;
        if ((this.nmContrato == null && other.nmContrato != null)
            || (this.nmContrato != null && !this.nmContrato.equals(other.nmContrato))) {
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
        return "entity.Contrato[ nmContrato=" + nmContrato + " ]";
    }

}
