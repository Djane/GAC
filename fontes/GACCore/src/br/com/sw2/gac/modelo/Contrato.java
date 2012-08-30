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
 * The persistent class for the TblContrato database table.
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@Entity
@Table(name = "TblContrato")
public class Contrato implements Serializable {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** Atributo nm contrato. */
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(unique = true, nullable = false)
    private String nmContrato;

    /** Atributo ds bairro contratante. */
    @Column(nullable = false)
    private String dsBairroContratante;

    /** Atributo ds cidade contratante. */
    @Column(nullable = false)
    private String dsCidadeContratante;

    /** Atributo ds e mail contratante. */
    @Column()
    private String dsEMailContratante;

    /** Atributo ds endereco contratante. */
    @Column(nullable = false)
    private String dsEnderecoContratante;

    /** Atributo ds uf contratante. */
    @Column(nullable = false)
    private String dsUFContratante;

    /** Atributo dt final validade. */
    @Temporal(TemporalType.DATE)
    private Date dtFinalValidade;

    /** Atributo dt inicio validade. */
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dtInicioValidade;

    /** Atributo dt nasc contratante. */
    @Temporal(TemporalType.DATE)
    private Date dtNascContratante;

    /** Atributo dt prox atual. */
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dtProxAtual;

    /** Atributo dt suspensao. */
    @Temporal(TemporalType.DATE)
    private Date dtSuspensao;

    /** Atributo nm cep contratante. */
    @Column(nullable = false)
    private String nmCEPContratante;

    /** Atributo nm cpf contratante. */
    @Column(nullable = false)
    private String nmCPFContratante;

    /** Atributo nm nome contratante. */
    @Column(nullable = false)
    private String nmNomeContratante;

    /** Atributo nm rg contratante. */
    @Column()
    private String nmRGContratante;

    // bi-directional many-to-one association to TblPacoteServico
    /** Atributo tbl pacote servico. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdServico", nullable = false)
    private PacoteServico tblPacoteServico;

    // bi-directional many-to-one association to TbUsuario
    /** Atributo tb usuario. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Login", nullable = false)
    private Usuario tbUsuario;

    // bi-directional many-to-one association to TblPaciente
    /** Atributo tbl pacientes. */
    @OneToMany(mappedBy = "tblContrato")
    private List<Paciente> tblPacientes;

    /**
     * Construtor Padrao Instancia um novo objeto Contrato.
     */
    public Contrato() {
    }

    /**
     * Nome: getNmContrato Recupera o valor do atributo 'nmContrato'.
     * @return valor do atributo 'nmContrato'
     * @see
     */
    public String getNmContrato() {
        return this.nmContrato;
    }

    /**
     * Nome: setNmContrato Registra o valor do atributo 'nmContrato'.
     * @param nmContrato valor do atributo nm contrato
     * @see
     */
    public void setNmContrato(String nmContrato) {
        this.nmContrato = nmContrato;
    }

    /**
     * Nome: getDsBairroContratante Recupera o valor do atributo 'dsBairroContratante'.
     * @return valor do atributo 'dsBairroContratante'
     * @see
     */
    public String getDsBairroContratante() {
        return this.dsBairroContratante;
    }

    /**
     * Nome: setDsBairroContratante Registra o valor do atributo 'dsBairroContratante'.
     * @param dsBairroContratante valor do atributo ds bairro contratante
     * @see
     */
    public void setDsBairroContratante(String dsBairroContratante) {
        this.dsBairroContratante = dsBairroContratante;
    }

    /**
     * Nome: getDsCidadeContratante Recupera o valor do atributo 'dsCidadeContratante'.
     * @return valor do atributo 'dsCidadeContratante'
     * @see
     */
    public String getDsCidadeContratante() {
        return this.dsCidadeContratante;
    }

    /**
     * Nome: setDsCidadeContratante Registra o valor do atributo 'dsCidadeContratante'.
     * @param dsCidadeContratante valor do atributo ds cidade contratante
     * @see
     */
    public void setDsCidadeContratante(String dsCidadeContratante) {
        this.dsCidadeContratante = dsCidadeContratante;
    }

    /**
     * Nome: getDsEMailContratante Recupera o valor do atributo 'dsEMailContratante'.
     * @return valor do atributo 'dsEMailContratante'
     * @see
     */
    public String getDsEMailContratante() {
        return this.dsEMailContratante;
    }

    /**
     * Nome: setDsEMailContratante Registra o valor do atributo 'dsEMailContratante'.
     * @param dsEMailContratante valor do atributo ds e mail contratante
     * @see
     */
    public void setDsEMailContratante(String dsEMailContratante) {
        this.dsEMailContratante = dsEMailContratante;
    }

    /**
     * Nome: getDsEnderecoContratante Recupera o valor do atributo 'dsEnderecoContratante'.
     * @return valor do atributo 'dsEnderecoContratante'
     * @see
     */
    public String getDsEnderecoContratante() {
        return this.dsEnderecoContratante;
    }

    /**
     * Nome: setDsEnderecoContratante Registra o valor do atributo 'dsEnderecoContratante'.
     * @param dsEnderecoContratante valor do atributo ds endereco contratante
     * @see
     */
    public void setDsEnderecoContratante(String dsEnderecoContratante) {
        this.dsEnderecoContratante = dsEnderecoContratante;
    }

    /**
     * Nome: getDsUFContratante Recupera o valor do atributo 'dsUFContratante'.
     * @return valor do atributo 'dsUFContratante'
     * @see
     */
    public String getDsUFContratante() {
        return this.dsUFContratante;
    }

    /**
     * Nome: setDsUFContratante Registra o valor do atributo 'dsUFContratante'.
     * @param dsUFContratante valor do atributo ds uf contratante
     * @see
     */
    public void setDsUFContratante(String dsUFContratante) {
        this.dsUFContratante = dsUFContratante;
    }

    /**
     * Nome: getDtFinalValidade Recupera o valor do atributo 'dtFinalValidade'.
     * @return valor do atributo 'dtFinalValidade'
     * @see
     */
    public Date getDtFinalValidade() {
        return this.dtFinalValidade;
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
     * Nome: getDtInicioValidade Recupera o valor do atributo 'dtInicioValidade'.
     * @return valor do atributo 'dtInicioValidade'
     * @see
     */
    public Date getDtInicioValidade() {
        return this.dtInicioValidade;
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
     * Nome: getDtNascContratante Recupera o valor do atributo 'dtNascContratante'.
     * @return valor do atributo 'dtNascContratante'
     * @see
     */
    public Date getDtNascContratante() {
        return this.dtNascContratante;
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
     * Nome: getDtProxAtual Recupera o valor do atributo 'dtProxAtual'.
     * @return valor do atributo 'dtProxAtual'
     * @see
     */
    public Date getDtProxAtual() {
        return this.dtProxAtual;
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
     * Nome: getDtSuspensao Recupera o valor do atributo 'dtSuspensao'.
     * @return valor do atributo 'dtSuspensao'
     * @see
     */
    public Date getDtSuspensao() {
        return this.dtSuspensao;
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
     * Nome: getNmCEPContratante Recupera o valor do atributo 'nmCEPContratante'.
     * @return valor do atributo 'nmCEPContratante'
     * @see
     */
    public String getNmCEPContratante() {
        return this.nmCEPContratante;
    }

    /**
     * Nome: setNmCEPContratante Registra o valor do atributo 'nmCEPContratante'.
     * @param nmCEPContratante valor do atributo nm cep contratante
     * @see
     */
    public void setNmCEPContratante(String nmCEPContratante) {
        this.nmCEPContratante = nmCEPContratante;
    }

    /**
     * Nome: getNmCPFContratante Recupera o valor do atributo 'nmCPFContratante'.
     * @return valor do atributo 'nmCPFContratante'
     * @see
     */
    public String getNmCPFContratante() {
        return this.nmCPFContratante;
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
        return this.nmNomeContratante;
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
     * Nome: getNmRGContratante Recupera o valor do atributo 'nmRGContratante'.
     * @return valor do atributo 'nmRGContratante'
     * @see
     */
    public String getNmRGContratante() {
        return this.nmRGContratante;
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
     * Nome: getTblPacoteServico Recupera o valor do atributo 'tblPacoteServico'.
     * @return valor do atributo 'tblPacoteServico'
     * @see
     */
    public PacoteServico getTblPacoteServico() {
        return this.tblPacoteServico;
    }

    /**
     * Nome: setTblPacoteServico Registra o valor do atributo 'tblPacoteServico'.
     * @param tblPacoteServico valor do atributo tbl pacote servico
     * @see
     */
    public void setTblPacoteServico(PacoteServico tblPacoteServico) {
        this.tblPacoteServico = tblPacoteServico;
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
     * Nome: getTblPacientes Recupera o valor do atributo 'tblPacientes'.
     * @return valor do atributo 'tblPacientes'
     * @see
     */
    public List<Paciente> getTblPacientes() {
        return this.tblPacientes;
    }

    /**
     * Nome: setTblPacientes Registra o valor do atributo 'tblPacientes'.
     * @param tblPacientes valor do atributo tbl pacientes
     * @see
     */
    public void setTblPacientes(List<Paciente> tblPacientes) {
        this.tblPacientes = tblPacientes;
    }

}