package br.com.sw2.gac.vo;

import java.math.BigDecimal;

/**
 * <b>Descrição: Classe que representa os clientes ativos de um pacote. </b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class ClientesAtivosVO {

    /** Atributo nome pacote. */
    private String nomePacote;

    /** Atributo qtde cliente. */
    private Long qtdeCliente;

    /** Atributo porc cliente. */
    private BigDecimal porcCliente;

    /**
     * Nome: getNomePacote Recupera o valor do atributo 'nomePacote'.
     * @return valor do atributo 'nomePacote'
     * @see
     */
    public String getNomePacote() {
        return nomePacote;
    }

    /**
     * Nome: setNomePacote Registra o valor do atributo 'nomePacote'.
     * @param nomePacote valor do atributo nome pacote
     * @see
     */
    public void setNomePacote(String nomePacote) {
        this.nomePacote = nomePacote;
    }

    /**
     * Nome: getQtdeCliente Recupera o valor do atributo 'qtdeCliente'.
     * @return valor do atributo 'qtdeCliente'
     * @see
     */
    public Long getQtdeCliente() {
        return qtdeCliente;
    }

    /**
     * Nome: setQtdeCliente Registra o valor do atributo 'qtdeCliente'.
     * @param qtdeCliente valor do atributo qtde cliente
     * @see
     */
    public void setQtdeCliente(Long qtdeCliente) {
        this.qtdeCliente = qtdeCliente;
    }

    /**
     * Nome: getPorcCliente Recupera o valor do atributo 'porcCliente'.
     * @return valor do atributo 'porcCliente'
     * @see
     */
    public BigDecimal getPorcCliente() {
        return porcCliente;
    }

    /**
     * Nome: setPorcCliente Registra o valor do atributo 'porcCliente'.
     * @param porcCliente valor do atributo porc cliente
     * @see
     */
    public void setPorcCliente(BigDecimal porcCliente) {
        this.porcCliente = porcCliente;
    }

}
