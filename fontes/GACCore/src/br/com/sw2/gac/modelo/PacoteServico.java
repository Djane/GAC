package br.com.sw2.gac.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "tblpacoteservico")
@NamedQueries({ @NamedQuery(name = "PacoteServico.findAll", query = "SELECT p FROM PacoteServico p") })
public class PacoteServico implements Serializable {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** Atributo id servico. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idServico")
    private Integer idServico;

    /** Atributo ds servico. */
    @Column(name = "dsServico")
    private String dsServico;

    /** Atributo dt inicio validade. */
    @Basic(optional = false)
    @Column(name = "dtInicioValidade")
    @Temporal(TemporalType.DATE)
    private Date dtInicioValidade;

    /** Atributo dt final validade. */
    @Column(name = "dtFinalValidade")
    @Temporal(TemporalType.DATE)
    private Date dtFinalValidade;
    // @Max(value=?) @Min(value=?)//if you know range of your decimal fields consider using these
    // annotations to enforce field validation
    /** Atributo prc mensal. */
    @Column(name = "prcMensal")
    private BigDecimal prcMensal;

    /** Atributo ds titulo. */
    @Column(name = "dsTitulo")
    private String dsTitulo;

    /** Atributo contrato list. */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idServico")
    private List<Contrato> contratoList;

    /**
     * Construtor Padrao Instancia um novo objeto PacoteServico.
     */
    public PacoteServico() {
        super();
    }

    /**
     * Construtor Padrao Instancia um novo objeto PacoteServico.
     * @param idServico the id servico
     */
    public PacoteServico(Integer idServico) {
        this.idServico = idServico;
    }

    /**
     * Construtor Padrao Instancia um novo objeto PacoteServico.
     * @param idServico the id servico
     * @param dtInicioValidade the dt inicio validade
     */
    public PacoteServico(Integer idServico, Date dtInicioValidade) {
        this.idServico = idServico;
        this.dtInicioValidade = dtInicioValidade;
    }

    /**
     * Nome: getIdServico Recupera o valor do atributo 'idServico'.
     * @return valor do atributo 'idServico'
     * @see
     */
    public Integer getIdServico() {
        return idServico;
    }

    /**
     * Nome: setIdServico Registra o valor do atributo 'idServico'.
     * @param idServico valor do atributo id servico
     * @see
     */
    public void setIdServico(Integer idServico) {
        this.idServico = idServico;
    }

    /**
     * Nome: getDsServico Recupera o valor do atributo 'dsServico'.
     * @return valor do atributo 'dsServico'
     * @see
     */
    public String getDsServico() {
        return dsServico;
    }

    /**
     * Nome: setDsServico Registra o valor do atributo 'dsServico'.
     * @param dsServico valor do atributo ds servico
     * @see
     */
    public void setDsServico(String dsServico) {
        this.dsServico = dsServico;
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
     * Nome: getPrcMensal Recupera o valor do atributo 'prcMensal'.
     * @return valor do atributo 'prcMensal'
     * @see
     */
    public BigDecimal getPrcMensal() {
        return prcMensal;
    }

    /**
     * Nome: setPrcMensal Registra o valor do atributo 'prcMensal'.
     * @param prcMensal valor do atributo prc mensal
     * @see
     */
    public void setPrcMensal(BigDecimal prcMensal) {
        this.prcMensal = prcMensal;
    }

    /**
     * Nome: getDsTitulo Recupera o valor do atributo 'dsTitulo'.
     * @return valor do atributo 'dsTitulo'
     * @see
     */
    public String getDsTitulo() {
        return dsTitulo;
    }

    /**
     * Nome: setDsTitulo Registra o valor do atributo 'dsTitulo'.
     * @param dsTitulo valor do atributo ds titulo
     * @see
     */
    public void setDsTitulo(String dsTitulo) {
        this.dsTitulo = dsTitulo;
    }

    /**
     * Nome: getContratoList Recupera o valor do atributo 'contratoList'.
     * @return valor do atributo 'contratoList'
     * @see
     */
    public List<Contrato> getContratoList() {
        return contratoList;
    }

    /**
     * Nome: setContratoList Registra o valor do atributo 'contratoList'.
     * @param contratoList valor do atributo contrato list
     * @see
     */
    public void setContratoList(List<Contrato> contratoList) {
        this.contratoList = contratoList;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idServico != null ? idServico.hashCode() : 0);
        return hash;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PacoteServico)) {
            return false;
        }
        PacoteServico other = (PacoteServico) object;
        if ((this.idServico == null && other.idServico != null)
            || (this.idServico != null && !this.idServico.equals(other.idServico))) {
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
        return "entity.PacoteServico[ idServico=" + idServico + " ]";
    }

}
