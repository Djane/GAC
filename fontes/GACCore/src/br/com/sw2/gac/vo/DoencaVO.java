package br.com.sw2.gac.vo;

import java.io.Serializable;

/**
 * <b>Descrição: Classe que representa uma doença no sistema.</b> <br>
 * .
 * @author: lucianor
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class DoencaVO implements Serializable {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = -4341285251531892766L;

    /** Atributo codigo cid. */
    private String codigoCID;

    /** Atributo nome doenca. */
    private String nomeDoenca;

    /** Atributo tipo doenca. */
    private TipoDoencaVO tipoDoenca;

    /**
     * Nome: getCodigoCID
     * Recupera o valor do atributo 'codigoCID'.
     *
     * @return valor do atributo 'codigoCID'
     * @see
     */
    public String getCodigoCID() {
        return codigoCID;
    }

    /**
     * Nome: setCodigoCID
     * Registra o valor do atributo 'codigoCID'.
     *
     * @param codigoCID valor do atributo codigo cid
     * @see
     */
    public void setCodigoCID(String codigoCID) {
        this.codigoCID = codigoCID;
    }

    /**
     * Nome: getNomeDoenca
     * Recupera o valor do atributo 'nomeDoenca'.
     *
     * @return valor do atributo 'nomeDoenca'
     * @see
     */
    public String getNomeDoenca() {
        return nomeDoenca;
    }

    /**
     * Nome: setNomeDoenca
     * Registra o valor do atributo 'nomeDoenca'.
     *
     * @param nomeDoenca valor do atributo nome doenca
     * @see
     */
    public void setNomeDoenca(String nomeDoenca) {
        this.nomeDoenca = nomeDoenca;
    }

    /**
     * Nome: getTipoDoenca
     * Recupera o valor do atributo 'tipoDoenca'.
     *
     * @return valor do atributo 'tipoDoenca'
     * @see
     */
    public TipoDoencaVO getTipoDoenca() {
        return tipoDoenca;
    }

    /**
     * Nome: setTipoDoenca
     * Registra o valor do atributo 'tipoDoenca'.
     *
     * @param tipoDoenca valor do atributo tipo doenca
     * @see
     */
    public void setTipoDoenca(TipoDoencaVO tipoDoenca) {
        this.tipoDoenca = tipoDoenca;
    }
}
