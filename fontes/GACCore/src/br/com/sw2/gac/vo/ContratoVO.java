package br.com.sw2.gac.vo;

import java.util.Date;

/**
 * <b>Descriçã: Classe que representa um contrato.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class ContratoVO {

    /** Atributo numero contrato. */
    private Integer numeroContrato;

    /** Atributo cpf contratante. */
    private String cpfContratante;

    /** Atributo rg contratante. */
    private String rgContratante;

    /** Atributo dt inicio validade. */
    private Date dtInicioValidade;

    /** Atributo dt final validade. */
    private Date dtFinalValidade;

    /** Atributo dt suspensao. */
    private Date dtSuspensao;

    /** Atributo pacote servico. */
    private PacoteServicoVO pacoteServico = new PacoteServicoVO();

    /** Atributo cliente. */
    private ClienteVO cliente = new ClienteVO();

    /** Atributo nome contratante. */
    private String nomeContratante;

    /** Atributo dt nascimento contratante. */
    private Date dtNascimentoContratante;

    /** Atributo usuario. */
    private UsuarioVO usuario;

    /** Atributo dtProxAtual. */
    private Date dtProxAtual;

    /**
     * Nome: getNumeroContrato Recupera o valor do atributo 'numeroContrato'.
     * @return valor do atributo 'numeroContrato'
     * @see
     */
    public Integer getNumeroContrato() {
        return numeroContrato;
    }

    /**
     * Nome: setNumeroContrato Registra o valor do atributo 'numeroContrato'.
     * @param numeroContrato valor do atributo numero contrato
     * @see
     */
    public void setNumeroContrato(Integer numeroContrato) {
        this.numeroContrato = numeroContrato;
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
     * Nome: getNomeContratante Recupera o valor do atributo 'nomeContratante'.
     * @return valor do atributo 'nomeContratante'
     * @see
     */
    public String getNomeContratante() {
        return nomeContratante;
    }

    /**
     * Nome: setNomeContratante Registra o valor do atributo 'nomeContratante'.
     * @param nomeContratante valor do atributo nome contratante
     * @see
     */
    public void setNomeContratante(String nomeContratante) {
        this.nomeContratante = nomeContratante;
    }

    /**
     * Nome: getCpfContratante Recupera o valor do atributo 'cpfContratante'.
     * @return valor do atributo 'cpfContratante'
     * @see
     */
    public String getCpfContratante() {
        return cpfContratante;
    }

    /**
     * Nome: setCpfContratante Registra o valor do atributo 'cpfContratante'.
     * @param cpfContratante valor do atributo cpf contratante
     * @see
     */
    public void setCpfContratante(String cpfContratante) {
        this.cpfContratante = cpfContratante;
    }

    /**
     * Nome: getRgContratante Recupera o valor do atributo 'rgContratante'.
     * @return valor do atributo 'rgContratante'
     * @see
     */
    public String getRgContratante() {
        return rgContratante;
    }

    /**
     * Nome: setRgContratante Registra o valor do atributo 'rgContratante'.
     * @param rgContratante valor do atributo rg contratante
     * @see
     */
    public void setRgContratante(String rgContratante) {
        this.rgContratante = rgContratante;
    }

    /**
     * Nome: getDtNascimentoContratante Recupera o valor do atributo 'dtNascimentoContratante'.
     * @return valor do atributo 'dtNascimentoContratante'
     * @see
     */
    public Date getDtNascimentoContratante() {
        return dtNascimentoContratante;
    }

    /**
     * Nome: setDtNascimentoContratante Registra o valor do atributo 'dtNascimentoContratante'.
     * @param dtNascimentoContratante valor do atributo dt nascimento contratante
     * @see
     */
    public void setDtNascimentoContratante(Date dtNascimentoContratante) {
        this.dtNascimentoContratante = dtNascimentoContratante;
    }

    /**
     * Nome: getCliente Recupera o valor do atributo 'cliente'.
     * @return valor do atributo 'cliente'
     * @see
     */
    public ClienteVO getCliente() {
        return cliente;
    }

    /**
     * Nome: setCliente Registra o valor do atributo 'cliente'.
     * @param cliente valor do atributo cliente
     * @see
     */
    public void setCliente(ClienteVO cliente) {
        this.cliente = cliente;
    }

    /**
     * Nome: getUsuario Recupera o valor do atributo 'usuario'.
     * @return the usuario
     * @see
     */
    public UsuarioVO getUsuario() {
        return usuario;
    }

    /**
     * Nome: setUsuario Registra o valor do atributo 'usuario'.
     * @param usuario the usuario to set
     * @see
     */
    public void setUsuario(UsuarioVO usuario) {
        this.usuario = usuario;
    }

    /**
     * Nome: getDtProxAtual Recupera o valor do atributo 'dtProxAtual'.
     * @return the dtProxAtual
     * @see
     */
    public Date getDtProxAtual() {
        return dtProxAtual;
    }

    /**
     * Nome: setDtProxAtual Registra o valor do atributo 'dtProxAtual'.
     * @param dtProxAtual the dtProxAtual to set
     * @see
     */
    public void setDtProxAtual(Date dtProxAtual) {
        this.dtProxAtual = dtProxAtual;
    }

    /**
     * Nome: getPacoteServico Recupera o valor do atributo 'pacoteServico'.
     * @return valor do atributo 'pacoteServico'
     * @see
     */
    public PacoteServicoVO getPacoteServico() {
        return pacoteServico;
    }

    /**
     * Nome: setPacoteServico Registra o valor do atributo 'pacoteServico'.
     * @param pacoteServico valor do atributo pacote servico
     * @see
     */
    public void setPacoteServico(PacoteServicoVO pacoteServico) {
        this.pacoteServico = pacoteServico;
    }

}
