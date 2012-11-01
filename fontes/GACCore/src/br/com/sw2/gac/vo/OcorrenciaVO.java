package br.com.sw2.gac.vo;

import java.util.Date;

/**
 * <b>Descrição: Classe que representa uma ocorrência no sistema.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class OcorrenciaVO {

    /** Atributo id ocorrencia. */
    private Integer idOcorrencia;

    /** Atributo tipo ocorrencia. */
    private TipoOcorrenciaVO tipoOcorrencia;

    /** Atributo data abertura. */
    private Date dataAbertura;

    /** Atributo contrato. */
    private ContratoVO contrato;

    /**
     * Nome: getIdOcorrencia Recupera o valor do atributo 'idOcorrencia'.
     * @return valor do atributo 'idOcorrencia'
     * @see
     */
    public Integer getIdOcorrencia() {
        return idOcorrencia;
    }

    /**
     * Nome: setIdOcorrencia Registra o valor do atributo 'idOcorrencia'.
     * @param idOcorrencia valor do atributo id ocorrencia
     * @see
     */
    public void setIdOcorrencia(Integer idOcorrencia) {
        this.idOcorrencia = idOcorrencia;
    }

    /**
     * Nome: getTipoOcorrencia Recupera o valor do atributo 'tipoOcorrencia'.
     * @return valor do atributo 'tipoOcorrencia'
     * @see
     */
    public TipoOcorrenciaVO getTipoOcorrencia() {
        return tipoOcorrencia;
    }

    /**
     * Nome: setTipoOcorrencia Registra o valor do atributo 'tipoOcorrencia'.
     * @param tipoOcorrencia valor do atributo tipo ocorrencia
     * @see
     */
    public void setTipoOcorrencia(TipoOcorrenciaVO tipoOcorrencia) {
        this.tipoOcorrencia = tipoOcorrencia;
    }

    /**
     * Nome: getDataAbertura Recupera o valor do atributo 'dataAbertura'.
     * @return valor do atributo 'dataAbertura'
     * @see
     */
    public Date getDataAbertura() {
        return dataAbertura;
    }

    /**
     * Nome: setDataAbertura Registra o valor do atributo 'dataAbertura'.
     * @param dataAbertura valor do atributo data abertura
     * @see
     */
    public void setDataAbertura(Date dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    /**
     * Nome: getContrato Recupera o valor do atributo 'contrato'.
     * @return valor do atributo 'contrato'
     * @see
     */
    public ContratoVO getContrato() {
        return contrato;
    }

    /**
     * Nome: setContrato Registra o valor do atributo 'contrato'.
     * @param contrato valor do atributo contrato
     * @see
     */
    public void setContrato(ContratoVO contrato) {
        this.contrato = contrato;
    }
}
