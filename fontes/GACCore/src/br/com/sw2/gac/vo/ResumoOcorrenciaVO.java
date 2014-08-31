package br.com.sw2.gac.vo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * <b>Descrição: Classe que representa os dados do resumo de ocorrencias.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class ResumoOcorrenciaVO {

    /** Atributo tipo ocorrencia. */
    private TipoOcorrenciaVO tipoOcorrencia;

    /** Atributo quantidade ocorrencias. */
    private Integer quantidadeOcorrencias;

    /** Atributo porcentagem. */
    private BigDecimal porcentagem;

    /** Atributo lista ocorrencias. */
    private List<OcorrenciaVO> listaOcorrencias = new ArrayList<OcorrenciaVO>();

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
     * Nome: getQuantidadeOcorrencias Recupera o valor do atributo 'quantidadeOcorrencias'.
     * @return valor do atributo 'quantidadeOcorrencias'
     * @see
     */
    public Integer getQuantidadeOcorrencias() {
        return quantidadeOcorrencias;
    }

    /**
     * Nome: setQuantidadeOcorrencias Registra o valor do atributo 'quantidadeOcorrencias'.
     * @param quantidadeOcorrencias valor do atributo quantidade ocorrencias
     * @see
     */
    public void setQuantidadeOcorrencias(Integer quantidadeOcorrencias) {
        this.quantidadeOcorrencias = quantidadeOcorrencias;
    }

    /**
     * Nome: getPorcentagem Recupera o valor do atributo 'porcentagem'.
     * @return valor do atributo 'porcentagem'
     * @see
     */
    public BigDecimal getPorcentagem() {
        return porcentagem;
    }

    /**
     * Nome: setPorcentagem Registra o valor do atributo 'porcentagem'.
     * @param porcentagem valor do atributo porcentagem
     * @see
     */
    public void setPorcentagem(BigDecimal porcentagem) {
        this.porcentagem = porcentagem;
    }

    /**
     * Nome: getListaOcorrencias Recupera o valor do atributo 'listaOcorrencias'.
     * @return valor do atributo 'listaOcorrencias'
     * @see
     */
    public List<OcorrenciaVO> getListaOcorrencias() {
        return listaOcorrencias;
    }

    /**
     * Nome: setListaOcorrencias Registra o valor do atributo 'listaOcorrencias'.
     * @param listaOcorrencias valor do atributo lista ocorrencias
     * @see
     */
    public void setListaOcorrencias(List<OcorrenciaVO> listaOcorrencias) {
        this.listaOcorrencias = listaOcorrencias;
    }

}
