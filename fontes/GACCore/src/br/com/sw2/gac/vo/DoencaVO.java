package br.com.sw2.gac.vo;

/**
 * <b>Descrição:</b> <br>
 * .
 * @author: lucianor
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class DoencaVO {

    /** Atributo id doenca. */
    private Integer idDoenca;

    /** Atributo nome doenca. */
    private String nomeDoenca;

    /**
     * Nome: getIdDoenca Recupera o valor do atributo 'idDoenca'.
     * @return valor do atributo 'idDoenca'
     * @see
     */
    public Integer getIdDoenca() {
        return idDoenca;
    }

    /**
     * Nome: setIdDoenca Registra o valor do atributo 'idDoenca'.
     * @param idDoenca valor do atributo id doenca
     * @see
     */
    public void setIdDoenca(Integer idDoenca) {
        this.idDoenca = idDoenca;
    }

    /**
     * Nome: getNomeDoenca Recupera o valor do atributo 'nomeDoenca'.
     * @return valor do atributo 'nomeDoenca'
     * @see
     */
    public String getNomeDoenca() {
        return nomeDoenca;
    }

    /**
     * Nome: setNomeDoenca Registra o valor do atributo 'nomeDoenca'.
     * @param nomeDoenca valor do atributo nome doenca
     * @see
     */
    public void setNomeDoenca(String nomeDoenca) {
        this.nomeDoenca = nomeDoenca;
    }
}
