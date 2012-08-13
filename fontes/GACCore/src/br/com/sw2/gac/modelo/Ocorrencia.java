/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.persistence.Lob;
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
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@Entity
@Table(name = "tblocorrencia")
@NamedQueries({ @NamedQuery(name = "Ocorrencia.findAll", query = "SELECT o FROM Ocorrencia o") })
public class Ocorrencia implements Serializable {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** Atributo id ocorrencia. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idOcorrencia")
    private Integer idOcorrencia;

    /** Atributo tp ocorrencia. */
    @Column(name = "tpOcorrencia")
    private Integer tpOcorrencia;

    /** Atributo status ocorre. */
    @Column(name = "statusOcorre")
    private Integer statusOcorre;

    /** Atributo dta atend. */
    @Column(name = "dtaAtend")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtaAtend;

    /** Atributo acao ocorrencia. */
    @Lob
    @Column(name = "acaoOcorrencia")
    private String acaoOcorrencia;

    /** Atributo recl ocorrencia. */
    @Lob
    @Column(name = "reclOcorrencia")
    private String reclOcorrencia;

    /** Atributo dta hora abertura. */
    @Column(name = "dtaHoraAbertura")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtaHoraAbertura;

    /** Atributo dta hora fechamento. */
    @Column(name = "dtaHoraFechamento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtaHoraFechamento;

    /** Atributo dta hora inicio. */
    @Column(name = "dtaHoraInicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtaHoraInicio;

    /** Atributo dta hora termino. */
    @Column(name = "dtaHoraTermino")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtaHoraTermino;

    /** Atributo conclusao. */
    @Lob
    @Column(name = "conclusao")
    private String conclusao;

    /** Atributo id script. */
    @JoinColumn(name = "idScript", referencedColumnName = "idScript")
    @ManyToOne(optional = false)
    private Script idScript;

    /** Atributo nm cpf cliente. */
    @JoinColumn(name = "nmCPFCliente", referencedColumnName = "nmCPFCliente")
    @ManyToOne(optional = false)
    private Cliente nmCPFCliente;

    /** Atributo login. */
    @JoinColumn(name = "login", referencedColumnName = "login")
    @ManyToOne(optional = false)
    private Usuario login;

    /** Atributo acionamento list. */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idOcorrencia")
    private List<Acionamento> acionamentoList;

    /**
     * Construtor Padrao Instancia um novo objeto Ocorrencia.
     */
    public Ocorrencia() {
    }

    /**
     * Construtor Padrao Instancia um novo objeto Ocorrencia.
     * @param idOcorrencia the id ocorrencia
     */
    public Ocorrencia(Integer idOcorrencia) {
        this.idOcorrencia = idOcorrencia;
    }

    /**
     * Nome: getIdOcorrencia Recupera o valor do atributo 'idOcorrencia'.
     * @return valor do atributo 'idOcorrencia'
     * @see
     */
    public Integer getIdOcorrencia() {
        return idOcorrencia;
    }

    /**
     * Nome: setIdOcorrencia Registra o valor do atributo 'idOcorrencia'.
     * @param idOcorrencia valor do atributo id ocorrencia
     * @see
     */
    public void setIdOcorrencia(Integer idOcorrencia) {
        this.idOcorrencia = idOcorrencia;
    }

    /**
     * Nome: getTpOcorrencia Recupera o valor do atributo 'tpOcorrencia'.
     * @return valor do atributo 'tpOcorrencia'
     * @see
     */
    public Integer getTpOcorrencia() {
        return tpOcorrencia;
    }

    /**
     * Nome: setTpOcorrencia Registra o valor do atributo 'tpOcorrencia'.
     * @param tpOcorrencia valor do atributo tp ocorrencia
     * @see
     */
    public void setTpOcorrencia(Integer tpOcorrencia) {
        this.tpOcorrencia = tpOcorrencia;
    }

    /**
     * Nome: getStatusOcorre Recupera o valor do atributo 'statusOcorre'.
     * @return valor do atributo 'statusOcorre'
     * @see
     */
    public Integer getStatusOcorre() {
        return statusOcorre;
    }

    /**
     * Nome: setStatusOcorre Registra o valor do atributo 'statusOcorre'.
     * @param statusOcorre valor do atributo status ocorre
     * @see
     */
    public void setStatusOcorre(Integer statusOcorre) {
        this.statusOcorre = statusOcorre;
    }

    /**
     * Nome: getDtaAtend Recupera o valor do atributo 'dtaAtend'.
     * @return valor do atributo 'dtaAtend'
     * @see
     */
    public Date getDtaAtend() {
        return dtaAtend;
    }

    /**
     * Nome: setDtaAtend Registra o valor do atributo 'dtaAtend'.
     * @param dtaAtend valor do atributo dta atend
     * @see
     */
    public void setDtaAtend(Date dtaAtend) {
        this.dtaAtend = dtaAtend;
    }

    /**
     * Nome: getAcaoOcorrencia Recupera o valor do atributo 'acaoOcorrencia'.
     * @return valor do atributo 'acaoOcorrencia'
     * @see
     */
    public String getAcaoOcorrencia() {
        return acaoOcorrencia;
    }

    /**
     * Nome: setAcaoOcorrencia Registra o valor do atributo 'acaoOcorrencia'.
     * @param acaoOcorrencia valor do atributo acao ocorrencia
     * @see
     */
    public void setAcaoOcorrencia(String acaoOcorrencia) {
        this.acaoOcorrencia = acaoOcorrencia;
    }

    /**
     * Nome: getReclOcorrencia Recupera o valor do atributo 'reclOcorrencia'.
     * @return valor do atributo 'reclOcorrencia'
     * @see
     */
    public String getReclOcorrencia() {
        return reclOcorrencia;
    }

    /**
     * Nome: setReclOcorrencia Registra o valor do atributo 'reclOcorrencia'.
     * @param reclOcorrencia valor do atributo recl ocorrencia
     * @see
     */
    public void setReclOcorrencia(String reclOcorrencia) {
        this.reclOcorrencia = reclOcorrencia;
    }

    /**
     * Nome: getDtaHoraAbertura Recupera o valor do atributo 'dtaHoraAbertura'.
     * @return valor do atributo 'dtaHoraAbertura'
     * @see
     */
    public Date getDtaHoraAbertura() {
        return dtaHoraAbertura;
    }

    /**
     * Nome: setDtaHoraAbertura Registra o valor do atributo 'dtaHoraAbertura'.
     * @param dtaHoraAbertura valor do atributo dta hora abertura
     * @see
     */
    public void setDtaHoraAbertura(Date dtaHoraAbertura) {
        this.dtaHoraAbertura = dtaHoraAbertura;
    }

    /**
     * Nome: getDtaHoraFechamento Recupera o valor do atributo 'dtaHoraFechamento'.
     * @return valor do atributo 'dtaHoraFechamento'
     * @see
     */
    public Date getDtaHoraFechamento() {
        return dtaHoraFechamento;
    }

    /**
     * Nome: setDtaHoraFechamento Registra o valor do atributo 'dtaHoraFechamento'.
     * @param dtaHoraFechamento valor do atributo dta hora fechamento
     * @see
     */
    public void setDtaHoraFechamento(Date dtaHoraFechamento) {
        this.dtaHoraFechamento = dtaHoraFechamento;
    }

    /**
     * Nome: getDtaHoraInicio Recupera o valor do atributo 'dtaHoraInicio'.
     * @return valor do atributo 'dtaHoraInicio'
     * @see
     */
    public Date getDtaHoraInicio() {
        return dtaHoraInicio;
    }

    /**
     * Nome: setDtaHoraInicio Registra o valor do atributo 'dtaHoraInicio'.
     * @param dtaHoraInicio valor do atributo dta hora inicio
     * @see
     */
    public void setDtaHoraInicio(Date dtaHoraInicio) {
        this.dtaHoraInicio = dtaHoraInicio;
    }

    /**
     * Nome: getDtaHoraTermino Recupera o valor do atributo 'dtaHoraTermino'.
     * @return valor do atributo 'dtaHoraTermino'
     * @see
     */
    public Date getDtaHoraTermino() {
        return dtaHoraTermino;
    }

    /**
     * Nome: setDtaHoraTermino Registra o valor do atributo 'dtaHoraTermino'.
     * @param dtaHoraTermino valor do atributo dta hora termino
     * @see
     */
    public void setDtaHoraTermino(Date dtaHoraTermino) {
        this.dtaHoraTermino = dtaHoraTermino;
    }

    /**
     * Nome: getConclusao Recupera o valor do atributo 'conclusao'.
     * @return valor do atributo 'conclusao'
     * @see
     */
    public String getConclusao() {
        return conclusao;
    }

    /**
     * Nome: setConclusao Registra o valor do atributo 'conclusao'.
     * @param conclusao valor do atributo conclusao
     * @see
     */
    public void setConclusao(String conclusao) {
        this.conclusao = conclusao;
    }

    /**
     * Nome: getIdScript Recupera o valor do atributo 'idScript'.
     * @return valor do atributo 'idScript'
     * @see
     */
    public Script getIdScript() {
        return idScript;
    }

    /**
     * Nome: setIdScript Registra o valor do atributo 'idScript'.
     * @param idScript valor do atributo id script
     * @see
     */
    public void setIdScript(Script idScript) {
        this.idScript = idScript;
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
        if (idOcorrencia != null) {
            hash += idOcorrencia.hashCode();
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

        if (!(object instanceof Ocorrencia)) {
            return false;
        }
        Ocorrencia other = (Ocorrencia) object;
        if ((this.idOcorrencia == null && other.idOcorrencia != null)
                || (this.idOcorrencia != null && !this.idOcorrencia.equals(other.idOcorrencia))) {
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
        return "br.com.sw2.gac.modelo.Ocorrencia[ idOcorrencia=" + idOcorrencia + " ]";
    }

}
