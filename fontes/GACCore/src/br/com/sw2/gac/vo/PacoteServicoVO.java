package br.com.sw2.gac.vo;

import java.math.BigDecimal;
import java.util.Date;

import br.com.sw2.gac.util.DateUtil;

/**
 * <b>Descrição: Classe que representa um pacote de serviços no sistema.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class PacoteServicoVO {

    /** Atributo id pacote. */
    private Integer idPacote;

    /** Atributo titulo. */
    private String titulo;

    /** Atributo descricao. */
    private String descricao;

    /** Atributo data inicio validade. */
    private Date dataInicioValidade;

    /** Atributo data final validade. */
    private Date dataFinalValidade;

    /** Atributo preco. */
    private BigDecimal preco;

    private String status;
    
    /**
     * Nome: getIdPacote Recupera o valor do atributo 'idPacote'.
     * @return valor do atributo 'idPacote'
     * @see
     */
    public Integer getIdPacote() {
        return idPacote;
    }

    /**
     * Nome: setIdPacote Registra o valor do atributo 'idPacote'.
     * @param idPacote valor do atributo id pacote
     * @see
     */
    public void setIdPacote(Integer idPacote) {
        this.idPacote = idPacote;
    }

    /**
     * Nome: getTitulo Recupera o valor do atributo 'titulo'.
     * @return valor do atributo 'titulo'
     * @see
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Nome: setTitulo Registra o valor do atributo 'titulo'.
     * @param titulo valor do atributo titulo
     * @see
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Nome: getDescricao Recupera o valor do atributo 'descricao'.
     * @return valor do atributo 'descricao'
     * @see
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Nome: setDescricao Registra o valor do atributo 'descricao'.
     * @param descricao valor do atributo descricao
     * @see
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Nome: getPreco Recupera o valor do atributo 'preco'.
     * @return valor do atributo 'preco'
     * @see
     */
    public BigDecimal getPreco() {
        return preco;
    }

    /**
     * Nome: setPreco Registra o valor do atributo 'preco'.
     * @param preco valor do atributo preco
     * @see
     */
    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    /**
     * Nome: getDataInicioValidade
     * Recupera o valor do atributo 'dataInicioValidade'.
     *
     * @return valor do atributo 'dataInicioValidade'
     * @see
     */
    public Date getDataInicioValidade() {
        return dataInicioValidade;
    }

    /**
     * Nome: setDataInicioValidade
     * Registra o valor do atributo 'dataInicioValidade'.
     *
     * @param dataInicioValidade valor do atributo data inicio validade
     * @see
     */
    public void setDataInicioValidade(Date dataInicioValidade) {
        this.dataInicioValidade = dataInicioValidade;
    }

    /**
     * Nome: getDataFinalValidade
     * Recupera o valor do atributo 'dataFinalValidade'.
     *
     * @return valor do atributo 'dataFinalValidade'
     * @see
     */
    public Date getDataFinalValidade() {
        return dataFinalValidade;
    }

    /**
     * Nome: setDataFinalValidade
     * Registra o valor do atributo 'dataFinalValidade'.
     *
     * @param dataFinalValidade valor do atributo data final validade
     * @see
     */
    public void setDataFinalValidade(Date dataFinalValidade) {
        this.dataFinalValidade = dataFinalValidade;
    }

    public String getStatus() {

        if (DateUtil.compareIgnoreTime(dataFinalValidade, new Date()) < 0) {
            this.status = "Vencido";
        } else {
            this.status = "Ativo";
        }
        
        return this.status;
    }
    
}
