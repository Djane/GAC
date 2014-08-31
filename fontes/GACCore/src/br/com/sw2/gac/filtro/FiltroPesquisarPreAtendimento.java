package br.com.sw2.gac.filtro;

/**
 * <b>Descrição: Classe que representa o filtro de pesquisa para identificação do cliente no pre
 * atendimento.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class FiltroPesquisarPreAtendimento {

    /** Atributo numero contrato. */
    private Integer numeroContrato;

    /** Atributo telefone cliente. */
    private String telefone;

    /** Atributo nome cliente. */
    private String nomeCliente;

    /** Atributo CPF cliente. */
    private String numeroCPFCliente;

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
     * Nome: getTelefone
     * Recupera o valor do atributo 'telefone'.
     *
     * @return valor do atributo 'telefone'
     * @see
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * Nome: setTelefone
     * Registra o valor do atributo 'telefone'.
     *
     * @param telefone valor do atributo telefone
     * @see
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * Nome: getNomeCliente Recupera o valor do atributo 'nomeCliente'.
     * @return valor do atributo 'nomeCliente'
     * @see
     */
    public String getNomeCliente() {
        return nomeCliente;
    }

    /**
     * Nome: setNomeCliente Registra o valor do atributo 'nomeCliente'.
     * @param nomeCliente valor do atributo nome cliente
     * @see
     */
    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    /**
     * Nome: getNumeroCPFCliente
     * Recupera o valor do atributo 'numeroCPFCliente'.
     *
     * @return valor do atributo 'numeroCPFCliente'
     * @see
     */
    public String getNumeroCPFCliente() {
        return numeroCPFCliente;
    }

    /**
     * Nome: setNumeroCPFCliente
     * Registra o valor do atributo 'numeroCPFCliente'.
     *
     * @param numeroCPFCliente valor do atributo numero cpf cliente
     * @see
     */
    public void setNumeroCPFCliente(String numeroCPFCliente) {
        this.numeroCPFCliente = numeroCPFCliente;
    }

}
