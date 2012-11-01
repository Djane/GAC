package br.com.sw2.gac.vo;

import java.util.List;

/**
 * <b>Descrição: Classe que representa o relatório de ocorrências em aberto.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class RelOcorrenciasAbertoVO {

    /** Atributo resumo. */
    private List<ResumoOcorrenciaVO> resumo;

    /** Atributo ocorrencias. */
    private List<OcorrenciaVO> ocorrencias;

    /**
     * Nome: getResumo Recupera o valor do atributo 'resumo'.
     * @return valor do atributo 'resumo'
     * @see
     */
    public List<ResumoOcorrenciaVO> getResumo() {
        return resumo;
    }

    /**
     * Nome: setResumo Registra o valor do atributo 'resumo'.
     * @param resumo valor do atributo resumo
     * @see
     */
    public void setResumo(List<ResumoOcorrenciaVO> resumo) {
        this.resumo = resumo;
    }

    /**
     * Nome: getOcorrencias Recupera o valor do atributo 'ocorrencias'.
     * @return valor do atributo 'ocorrencias'
     * @see
     */
    public List<OcorrenciaVO> getOcorrencias() {
        return ocorrencias;
    }

    /**
     * Nome: setOcorrencias Registra o valor do atributo 'ocorrencias'.
     * @param ocorrencias valor do atributo ocorrencias
     * @see
     */
    public void setOcorrencias(List<OcorrenciaVO> ocorrencias) {
        this.ocorrencias = ocorrencias;
    }

}
