package br.com.sw2.gac.vo;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * <b>Descrição:</b> <br>
 * .
 * @author: lucianor
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@ManagedBean
@ViewScoped
public class StatusDispositivoVO {

    /** Atributo id status. */
    private Integer idStatus;

    /** Atributo descricao status. */
    private String descricaoStatus;

    /**
     * Nome: getIdStatus Recupera o valor do atributo 'idStatus'.
     * @return valor do atributo 'idStatus'
     * @see
     */
    public Integer getIdStatus() {
        return idStatus;
    }

    /**
     * Nome: setIdStatus Registra o valor do atributo 'idStatus'.
     * @param idStatus valor do atributo id status
     * @see
     */
    public void setIdStatus(Integer idStatus) {
        this.idStatus = idStatus;
    }

    /**
     * Nome: getDescricaoStatus Recupera o valor do atributo 'descricaoStatus'.
     * @return valor do atributo 'descricaoStatus'
     * @see
     */
    public String getDescricaoStatus() {
        return descricaoStatus;
    }

    /**
     * Nome: setDescricaoStatus Registra o valor do atributo 'descricaoStatus'.
     * @param descricaoStatus valor do atributo descricao status
     * @see
     */
    public void setDescricaoStatus(String descricaoStatus) {
        this.descricaoStatus = descricaoStatus;
    }

}
