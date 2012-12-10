package br.com.sw2.gac.vo;

import br.com.sw2.gac.tools.TipoOcorrencia;

/**
 * <b>Descrição: Classe que representa um tipo de ocorrência.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class TipoOcorrenciaVO {

    /** Atributo codigo tipo ocorrencia. */
    private Integer codigoTipoOcorrencia;

    /** Atributo descricao tipo ocorrencia. */
    private String descricaoTipoOcorrencia;

    /**
     * Construtor Padrao Instancia um novo objeto TipoOcorrenciaVO.
     */
    public TipoOcorrenciaVO() {
        super();
    }

    /**
     * Construtor Padrao Instancia um novo objeto TipoOcorrenciaVO.
     * @param tipo the tipo
     */
    public TipoOcorrenciaVO(TipoOcorrencia tipo) {
        this.setCodigoTipoOcorrencia(tipo.getValue());
        this.setDescricaoTipoOcorrencia(tipo.getLabel());
    }

    /**
     * Nome: getCodigoTipoOcorrencia Recupera o valor do atributo 'codigoTipoOcorrencia'.
     * @return valor do atributo 'codigoTipoOcorrencia'
     * @see
     */
    public Integer getCodigoTipoOcorrencia() {
        return codigoTipoOcorrencia;
    }

    /**
     * Nome: setCodigoTipoOcorrencia Registra o valor do atributo 'codigoTipoOcorrencia'.
     * @param codigoTipoOcorrencia valor do atributo codigo tipo ocorrencia
     * @see
     */
    public void setCodigoTipoOcorrencia(Integer codigoTipoOcorrencia) {
        this.codigoTipoOcorrencia = codigoTipoOcorrencia;
    }

    /**
     * Nome: getDescricaoTipoOcorrencia Recupera o valor do atributo 'descricaoTipoOcorrencia'.
     * @return valor do atributo 'descricaoTipoOcorrencia'
     * @see
     */
    public String getDescricaoTipoOcorrencia() {
        return descricaoTipoOcorrencia;
    }

    /**
     * Nome: setDescricaoTipoOcorrencia Registra o valor do atributo 'descricaoTipoOcorrencia'.
     * @param descricaoTipoOcorrencia valor do atributo descricao tipo ocorrencia
     * @see
     */
    public void setDescricaoTipoOcorrencia(String descricaoTipoOcorrencia) {
        this.descricaoTipoOcorrencia = descricaoTipoOcorrencia;
    }

}
