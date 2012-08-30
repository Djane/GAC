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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * <b>Descrição: The persistent class for the TblContato database table.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@Entity
@Table(name = "TblContato")
public class Contato implements Serializable {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** Atributo id contato. */
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "IdContato", unique = true, nullable = false)
    private int idContato;

    /** Atributo bai contato. */
    @Column(name = "BaiContato", length = 60)
    private String baiContato;

    /** Atributo CEP contato. */
    @Column(length = 10)
    private String CEPContato;

    /** Atributo cid contato. */
    @Column(name = "CidContato", length = 60)
    private String cidContato;

    /** Atributo contratante. */
    @Column(name = "Contratante", length = 1)
    private String contratante;

    /** Atributo dta nascimento. */
    @Temporal(TemporalType.DATE)
    private Date dtaNascimento;

    /** Atributo end contato. */
    @Column(name = "EndContato", length = 60)
    private String endContato;

    /** Atributo estado contato. */
    @Column(name = "EstadoContato", length = 2)
    private String estadoContato;

    /** Atributo grau parentesco. */
    @Column(name = "GrauParentesco", length = 1)
    private String grauParentesco;

    /** Atributo nome contato. */
    @Column(name = "NomeContato", length = 60)
    private String nomeContato;

    /** Atributo sqa chamada. */
    private int sqaChamada;

    // bi-directional many-to-one association to TblAcionamento
    /** Atributo tbl acionamentos. */
    @OneToMany(mappedBy = "tblContato")
    private List<Acionamento> tblAcionamentos;

    // bi-directional many-to-one association to TblPaciente
    /** Atributo tbl paciente. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NmCPFPaciente", nullable = false)
    private Paciente tblPaciente;

    // bi-directional many-to-one association to TbUsuario
    /** Atributo tb usuario. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Login", nullable = false)
    private Usuario tbUsuario;

    // bi-directional many-to-one association to TblFormaComunica
    /** Atributo tbl forma comunicas. */
    @OneToMany(mappedBy = "tblContato")
    private List<FormaComunica> tblFormaComunicas;

    /**
     * Construtor Padrao Instancia um novo objeto Contato.
     */
    public Contato() {
    }

    /**
     * Nome: getIdContato Recupera o valor do atributo 'idContato'.
     * @return valor do atributo 'idContato'
     * @see
     */
    public int getIdContato() {
        return this.idContato;
    }

    /**
     * Nome: setIdContato Registra o valor do atributo 'idContato'.
     * @param idContato valor do atributo id contato
     * @see
     */
    public void setIdContato(int idContato) {
        this.idContato = idContato;
    }

    /**
     * Nome: getBaiContato Recupera o valor do atributo 'baiContato'.
     * @return valor do atributo 'baiContato'
     * @see
     */
    public String getBaiContato() {
        return this.baiContato;
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
     * Nome: getCEPContato Recupera o valor do atributo 'CEPContato'.
     * @return valor do atributo 'CEPContato'
     * @see
     */
    public String getCEPContato() {
        return this.CEPContato;
    }

    /**
     * Nome: setCEPContato Registra o valor do atributo 'CEPContato'.
     * @param CEPContato valor do atributo cEP contato
     * @see
     */
    public void setCEPContato(String CEPContato) {
        this.CEPContato = CEPContato;
    }

    /**
     * Nome: getCidContato Recupera o valor do atributo 'cidContato'.
     * @return valor do atributo 'cidContato'
     * @see
     */
    public String getCidContato() {
        return this.cidContato;
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
     * Nome: getContratante Recupera o valor do atributo 'contratante'.
     * @return valor do atributo 'contratante'
     * @see
     */
    public String getContratante() {
        return this.contratante;
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
     * Nome: getDtaNascimento Recupera o valor do atributo 'dtaNascimento'.
     * @return valor do atributo 'dtaNascimento'
     * @see
     */
    public Date getDtaNascimento() {
        return this.dtaNascimento;
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
     * Nome: getEndContato Recupera o valor do atributo 'endContato'.
     * @return valor do atributo 'endContato'
     * @see
     */
    public String getEndContato() {
        return this.endContato;
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
     * Nome: getEstadoContato Recupera o valor do atributo 'estadoContato'.
     * @return valor do atributo 'estadoContato'
     * @see
     */
    public String getEstadoContato() {
        return this.estadoContato;
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
     * Nome: getGrauParentesco Recupera o valor do atributo 'grauParentesco'.
     * @return valor do atributo 'grauParentesco'
     * @see
     */
    public String getGrauParentesco() {
        return this.grauParentesco;
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
     * Nome: getNomeContato Recupera o valor do atributo 'nomeContato'.
     * @return valor do atributo 'nomeContato'
     * @see
     */
    public String getNomeContato() {
        return this.nomeContato;
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
     * Nome: getSqaChamada Recupera o valor do atributo 'sqaChamada'.
     * @return valor do atributo 'sqaChamada'
     * @see
     */
    public int getSqaChamada() {
        return this.sqaChamada;
    }

    /**
     * Nome: setSqaChamada Registra o valor do atributo 'sqaChamada'.
     * @param sqaChamada valor do atributo sqa chamada
     * @see
     */
    public void setSqaChamada(int sqaChamada) {
        this.sqaChamada = sqaChamada;
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
     * Nome: getTblFormaComunicas Recupera o valor do atributo 'tblFormaComunicas'.
     * @return valor do atributo 'tblFormaComunicas'
     * @see
     */
    public List<FormaComunica> getTblFormaComunicas() {
        return this.tblFormaComunicas;
    }

    /**
     * Nome: setTblFormaComunicas Registra o valor do atributo 'tblFormaComunicas'.
     * @param tblFormaComunicas valor do atributo tbl forma comunicas
     * @see
     */
    public void setTblFormaComunicas(List<FormaComunica> tblFormaComunicas) {
        this.tblFormaComunicas = tblFormaComunicas;
    }

}