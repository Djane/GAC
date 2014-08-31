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

    /**
     * Id do pacote
     */
    private Integer idPacote;

    /**
     * Nome/Título do pacote
     */
    private String titulo;
    
    /**
     * Descrição do pacote 
     */
    private String descricao;

    /**
     * Data do inicio de validade do pacote
     */
    private Date dataInicioValidade;

    /**
     * Data de fim da validade do pacote
     */
    private Date dataFinalValidade;

    /**
     * Valor do pacote
     */
    private BigDecimal preco;

    /**
     * Status do pacotte.
     * Ativo se estiver dentro da data de validade ou Vencido se estiver fora da data.
     */
    private String status;

    public Integer getIdPacote() {
        return idPacote;
    }

    public void setIdPacote(Integer idPacote) {
        this.idPacote = idPacote;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Date getDataInicioValidade() {
        return dataInicioValidade;
    }

    public void setDataInicioValidade(Date dataInicioValidade) {
        this.dataInicioValidade = dataInicioValidade;
    }

    public Date getDataFinalValidade() {
        return dataFinalValidade;
    }

    public void setDataFinalValidade(Date dataFinalValidade) {
        this.dataFinalValidade = dataFinalValidade;
    }

    public String getStatus() {

        if (null != dataFinalValidade && DateUtil.compareIgnoreTime(dataFinalValidade, new Date()) < 0) {
            this.status = "Vencido";
        } else {
            this.status = "Ativo";
        }
        
        return this.status;
    }
    
}
