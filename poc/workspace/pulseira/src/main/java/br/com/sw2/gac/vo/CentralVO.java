package br.com.sw2.gac.vo;

/**
 * <b>Descrição:</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class CentralVO {

    /** Atributo id central. */
    private Integer idCentral;

    /** Atributo descricao central. */
    private String descricaoCentral;

    /**
     * Nome: getIdCentral Recupera o valor do atributo 'idCentral'.
     * @return valor do atributo 'idCentral'
     * @see
     */
    public Integer getIdCentral() {
        return idCentral;
    }

    /**
     * Nome: setIdCentral Registra o valor do atributo 'idCentral'.
     * @param idCentral valor do atributo id central
     * @see
     */
    public void setIdCentral(Integer idCentral) {
        this.idCentral = idCentral;
    }

    /**
     * Nome: getDescricaoCentral Recupera o valor do atributo 'descricaoCentral'.
     * @return valor do atributo 'descricaoCentral'
     * @see
     */
    public String getDescricaoCentral() {
        return descricaoCentral;
    }

    /**
     * Nome: setDescricaoCentral Registra o valor do atributo 'descricaoCentral'.
     * @param descricaoCentral valor do atributo descricao central
     * @see
     */
    public void setDescricaoCentral(String descricaoCentral) {
        this.descricaoCentral = descricaoCentral;
    }

}
