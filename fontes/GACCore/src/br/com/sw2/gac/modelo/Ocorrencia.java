package br.com.sw2.gac.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * <b>Descrição: The persistent class for the TblOcorrencia database table.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@Entity
@Table(name = "TblOcorrencia")
public class Ocorrencia implements Serializable {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** Atributo id ocorrencia. */
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "IdOcorrencia", unique = true, nullable = false)
    private int idOcorrencia;

    /** Atributo acao ocorrencia. */
    @Lob
    @Column(name = "AcaoOcorrencia")
    private String acaoOcorrencia;

    /** Atributo conclusao. */
    @Lob
    @Column(name = "Conclusao")
    private String conclusao;

    /** Atributo dta atend. */
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtaAtend;

    /** Atributo dta hora abertura. */
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtaHoraAbertura;

    /** Atributo dta hora fechamento. */
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtaHoraFechamento;

    /** Atributo dta hora inicio. */
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtaHoraInicio;

    /** Atributo dta hora termino. */
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtaHoraTermino;

    /** Atributo recl ocorrencia. */
    @Lob
    @Column(name = "ReclOcorrencia")
    private String reclOcorrencia;

    /** Atributo status ocorre. */
    @Column(name = "StatusOcorre", length = 1)
    private String statusOcorre;

    /** Atributo tp ocorrencia. */
    private int tpOcorrencia;

    // bi-directional many-to-one association to TblAcionamento
    /** Atributo tbl acionamentos. */
    @OneToMany(mappedBy = "tblOcorrencia")
    private List<Acionamento> tblAcionamentos;

    // bi-directional many-to-one association to TbUsuario
    /** Atributo tb usuario. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Login", nullable = false)
    private Usuario tbUsuario;

    // bi-directional many-to-one association to TblPaciente
    /** Atributo tbl paciente. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NmCPFPaciente", nullable = false)
    private Paciente tblPaciente;

    // bi-directional many-to-one association to TblScript
    /** Atributo tbl script. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdScript", nullable = false)
    private Script tblScript;

    /**
     * Construtor Padrao Instancia um novo objeto Ocorrencia.
     */
    public Ocorrencia() {
    }

    /**
     * Nome: getIdOcorrencia Recupera o valor do atributo 'idOcorrencia'.
     * @return valor do atributo 'idOcorrencia'
     * @see
     */
    public int getIdOcorrencia() {
        return this.idOcorrencia;
    }

    /**
     * Nome: setIdOcorrencia Registra o valor do atributo 'idOcorrencia'.
     * @param idOcorrencia valor do atributo id ocorrencia
     * @see
     */
    public void setIdOcorrencia(int idOcorrencia) {
        this.idOcorrencia = idOcorrencia;
    }

    /**
     * Nome: getAcaoOcorrencia Recupera o valor do atributo 'acaoOcorrencia'.
     * @return valor do atributo 'acaoOcorrencia'
     * @see
     */
    public String getAcaoOcorrencia() {
        return this.acaoOcorrencia;
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
     * Nome: getConclusao Recupera o valor do atributo 'conclusao'.
     * @return valor do atributo 'conclusao'
     * @see
     */
    public String getConclusao() {
        return this.conclusao;
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
     * Nome: getDtaAtend Recupera o valor do atributo 'dtaAtend'.
     * @return valor do atributo 'dtaAtend'
     * @see
     */
    public Date getDtaAtend() {
        return this.dtaAtend;
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
     * Nome: getDtaHoraAbertura Recupera o valor do atributo 'dtaHoraAbertura'.
     * @return valor do atributo 'dtaHoraAbertura'
     * @see
     */
    public Date getDtaHoraAbertura() {
        return this.dtaHoraAbertura;
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
        return this.dtaHoraFechamento;
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
        return this.dtaHoraInicio;
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
        return this.dtaHoraTermino;
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
     * Nome: getReclOcorrencia Recupera o valor do atributo 'reclOcorrencia'.
     * @return valor do atributo 'reclOcorrencia'
     * @see
     */
    public String getReclOcorrencia() {
        return this.reclOcorrencia;
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
     * Nome: getStatusOcorre Recupera o valor do atributo 'statusOcorre'.
     * @return valor do atributo 'statusOcorre'
     * @see
     */
    public String getStatusOcorre() {
        return this.statusOcorre;
    }

    /**
     * Nome: setStatusOcorre Registra o valor do atributo 'statusOcorre'.
     * @param statusOcorre valor do atributo status ocorre
     * @see
     */
    public void setStatusOcorre(String statusOcorre) {
        this.statusOcorre = statusOcorre;
    }

    /**
     * Nome: getTpOcorrencia Recupera o valor do atributo 'tpOcorrencia'.
     * @return valor do atributo 'tpOcorrencia'
     * @see
     */
    public int getTpOcorrencia() {
        return this.tpOcorrencia;
    }

    /**
     * Nome: setTpOcorrencia Registra o valor do atributo 'tpOcorrencia'.
     * @param tpOcorrencia valor do atributo tp ocorrencia
     * @see
     */
    public void setTpOcorrencia(int tpOcorrencia) {
        this.tpOcorrencia = tpOcorrencia;
    }

    /**
     * Nome: getTblAcionamentos Recupera o valor do atributo 'tblAcionamentos'.
     * @return valor do atributo 'tblAcionamentos'
     * @see
     */
    public List<Acionamento> getTblAcionamentos() {
        return this.tblAcionamentos;
    }

    /**
     * Nome: setTblAcionamentos Registra o valor do atributo 'tblAcionamentos'.
     * @param tblAcionamentos valor do atributo tbl acionamentos
     * @see
     */
    public void setTblAcionamentos(List<Acionamento> tblAcionamentos) {
        this.tblAcionamentos = tblAcionamentos;
    }

    /**
     * Nome: getTbUsuario Recupera o valor do atributo 'tbUsuario'.
     * @return valor do atributo 'tbUsuario'
     * @see
     */
    public Usuario getTbUsuario() {
        return this.tbUsuario;
    }

    /**
     * Nome: setTbUsuario Registra o valor do atributo 'tbUsuario'.
     * @param tbUsuario valor do atributo tb usuario
     * @see
     */
    public void setTbUsuario(Usuario tbUsuario) {
        this.tbUsuario = tbUsuario;
    }

    /**
     * Nome: getTblPaciente Recupera o valor do atributo 'tblPaciente'.
     * @return valor do atributo 'tblPaciente'
     * @see
     */
    public Paciente getTblPaciente() {
        return this.tblPaciente;
    }

    /**
     * Nome: setTblPaciente Registra o valor do atributo 'tblPaciente'.
     * @param tblPaciente valor do atributo tbl paciente
     * @see
     */
    public void setTblPaciente(Paciente tblPaciente) {
        this.tblPaciente = tblPaciente;
    }

    /**
     * Nome: getTblScript Recupera o valor do atributo 'tblScript'.
     * @return valor do atributo 'tblScript'
     * @see
     */
    public Script getTblScript() {
        return this.tblScript;
    }

    /**
     * Nome: setTblScript Registra o valor do atributo 'tblScript'.
     * @param tblScript valor do atributo tbl script
     * @see
     */
    public void setTblScript(Script tblScript) {
        this.tblScript = tblScript;
    }

}