package br.com.sw2.gac.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <b>Descrição: Classe que representa um tratamento de um cliente.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class TratamentoVO implements Serializable {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = -155698810640271851L;

    /** Atributo id tratamento. */
    private Integer idTratamento;

    /** Atributo nome tratamento. */
    private String nomeTratamento;

    /** Atributo descricao tratamento. */
    private String descricaoTratamento;

    /** Atributo data hora inicial. */
    private Date dataHoraInicial;

    /** Atributo frequencia. */
    private Integer frequencia = 0;

    /** Atributo lista horarios. */
    private List<String> listaHorarios;

    /**
     * Nome: getIdTratamento Recupera o valor do atributo 'idTratamento'.
     * @return valor do atributo 'idTratamento'
     * @see
     */
    public Integer getIdTratamento() {
        return idTratamento;
    }

    /**
     * Nome: setIdTratamento Registra o valor do atributo 'idTratamento'.
     * @param idTratamento valor do atributo id tratamento
     * @see
     */
    public void setIdTratamento(Integer idTratamento) {
        this.idTratamento = idTratamento;
    }

    /**
     * Nome: getNomeTratamento Recupera o valor do atributo 'nomeTratamento'.
     * @return valor do atributo 'nomeTratamento'
     * @see
     */
    public String getNomeTratamento() {
        return nomeTratamento;
    }

    /**
     * Nome: setNomeTratamento Registra o valor do atributo 'nomeTratamento'.
     * @param nomeTratamento valor do atributo nome tratamento
     * @see
     */
    public void setNomeTratamento(String nomeTratamento) {
        this.nomeTratamento = nomeTratamento;
    }

    /**
     * Nome: getDescricaoTratamento Recupera o valor do atributo 'descricaoTratamento'.
     * @return valor do atributo 'descricaoTratamento'
     * @see
     */
    public String getDescricaoTratamento() {
        return descricaoTratamento;
    }

    /**
     * Nome: setDescricaoTratamento Registra o valor do atributo 'descricaoTratamento'.
     * @param descricaoTratamento valor do atributo descricao tratamento
     * @see
     */
    public void setDescricaoTratamento(String descricaoTratamento) {
        this.descricaoTratamento = descricaoTratamento;
    }

    /**
     * Nome: getDataHoraInicial Recupera o valor do atributo 'dataHoraInicial'.
     * @return valor do atributo 'dataHoraInicial'
     * @see
     */
    public Date getDataHoraInicial() {
        return dataHoraInicial;
    }

    /**
     * Nome: setDataHoraInicial Registra o valor do atributo 'dataHoraInicial'.
     * @param dataHoraInicial valor do atributo data hora inicial
     * @see
     */
    public void setDataHoraInicial(Date dataHoraInicial) {
        this.dataHoraInicial = dataHoraInicial;
    }

    /**
     * Nome: getListaHorarios Recupera o valor do atributo 'listaHorarios'.
     * @return valor do atributo 'listaHorarios'
     * @see
     */
    public List<String> getListaHorarios() {
        return listaHorarios;
    }

    /**
     * Nome: setListaHorarios Registra o valor do atributo 'listaHorarios'.
     * @param listaHorarios valor do atributo lista horarios
     * @see
     */
    public void setListaHorarios(List<String> listaHorarios) {
        this.listaHorarios = listaHorarios;
    }

    /**
     * Nome: getFrequencia Recupera o valor do atributo 'frequencia'.
     * @return valor do atributo 'frequencia'
     * @see
     */
    public Integer getFrequencia() {
        return frequencia;
    }

    /**
     * Nome: setFrequencia Registra o valor do atributo 'frequencia'.
     * @param frequencia valor do atributo frequencia
     * @see
     */
    public void setFrequencia(Integer frequencia) {
        this.frequencia = frequencia;
    }

}
