package br.com.sw2.gac.vo;

import java.math.BigDecimal;

/**
 * <b>Descrição :</b> <br>
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

    /** Atributo preco. */
    private BigDecimal preco;

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

}
