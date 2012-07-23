package br.com.sw2.gac.vo;

import java.util.Date;
import java.util.List;

/**
 * <b>Descrição: Super classe para suporte aos managedBeans</b> <br>.
 *
 * @author: lucianor
 * @version 1.0
 *
 * Copyright 2012 SmartAngel.
 */
public class ContratoVO {

    /** Atributo numero contrato. */
    private String numeroContrato;

    /** Atributo dt inicio validade. */
    private Date dtInicioValidade;

    /** Atributo dt final validade. */
    private Date dtFinalValidade;

    /** Atributo nome contratante. */
    private String nomeContratante;

    /** Atributo cpf contratante. */
    private String cpfContratante;

    /** Atributo lista tratamentos. */
    private List<TratamentoVO> listaTratamentos;

    /** Atributo lista contatos. */
    private List<ContatoVO> listaContatos;

    /**
     * Nome: getNumeroContrato
     * Recupera o valor do atributo 'numeroContrato'.
     *
     * @return valor do atributo 'numeroContrato'
     * @see
     */
    public String getNumeroContrato() {
        return numeroContrato;
    }

    /**
     * Nome: setNumeroContrato
     * Registra o valor do atributo 'numeroContrato'.
     *
     * @param numeroContrato valor do atributo numero contrato
     * @see
     */
    public void setNumeroContrato(String numeroContrato) {
        this.numeroContrato = numeroContrato;
    }

    /**
     * Nome: getDtInicioValidade
     * Recupera o valor do atributo 'dtInicioValidade'.
     *
     * @return valor do atributo 'dtInicioValidade'
     * @see
     */
    public Date getDtInicioValidade() {
        return dtInicioValidade;
    }

    /**
     * Nome: setDtInicioValidade
     * Registra o valor do atributo 'dtInicioValidade'.
     *
     * @param dtInicioValidade valor do atributo dt inicio validade
     * @see
     */
    public void setDtInicioValidade(Date dtInicioValidade) {
        this.dtInicioValidade = dtInicioValidade;
    }

    /**
     * Nome: getDtFinalValidade
     * Recupera o valor do atributo 'dtFinalValidade'.
     *
     * @return valor do atributo 'dtFinalValidade'
     * @see
     */
    public Date getDtFinalValidade() {
        return dtFinalValidade;
    }

    /**
     * Nome: setDtFinalValidade
     * Registra o valor do atributo 'dtFinalValidade'.
     *
     * @param dtFinalValidade valor do atributo dt final validade
     * @see
     */
    public void setDtFinalValidade(Date dtFinalValidade) {
        this.dtFinalValidade = dtFinalValidade;
    }

    /**
     * Nome: getNomeContratante
     * Recupera o valor do atributo 'nomeContratante'.
     *
     * @return valor do atributo 'nomeContratante'
     * @see
     */
    public String getNomeContratante() {
        return nomeContratante;
    }

    /**
     * Nome: setNomeContratante
     * Registra o valor do atributo 'nomeContratante'.
     *
     * @param nomeContratante valor do atributo nome contratante
     * @see
     */
    public void setNomeContratante(String nomeContratante) {
        this.nomeContratante = nomeContratante;
    }

    /**
     * Nome: getCpfContratante
     * Recupera o valor do atributo 'cpfContratante'.
     *
     * @return valor do atributo 'cpfContratante'
     * @see
     */
    public String getCpfContratante() {
        return cpfContratante;
    }

    /**
     * Nome: setCpfContratante
     * Registra o valor do atributo 'cpfContratante'.
     *
     * @param cpfContratante valor do atributo cpf contratante
     * @see
     */
    public void setCpfContratante(String cpfContratante) {
        this.cpfContratante = cpfContratante;
    }

    /**
     * Nome: getListaTratamentos
     * Recupera o valor do atributo 'listaTratamentos'.
     *
     * @return valor do atributo 'listaTratamentos'
     * @see
     */
    public List<TratamentoVO> getListaTratamentos() {
        return listaTratamentos;
    }

    /**
     * Nome: setListaTratamentos
     * Registra o valor do atributo 'listaTratamentos'.
     *
     * @param listaTratamentos valor do atributo lista tratamentos
     * @see
     */
    public void setListaTratamentos(List<TratamentoVO> listaTratamentos) {
        this.listaTratamentos = listaTratamentos;
    }

    /**
     * Nome: getListaContatos
     * Recupera o valor do atributo 'listaContatos'.
     *
     * @return valor do atributo 'listaContatos'
     * @see
     */
    public List<ContatoVO> getListaContatos() {
        return listaContatos;
    }

    /**
     * Nome: setListaContatos
     * Registra o valor do atributo 'listaContatos'.
     *
     * @param listaContatos valor do atributo lista contatos
     * @see
     */
    public void setListaContatos(List<ContatoVO> listaContatos) {
        this.listaContatos = listaContatos;
    }

}
