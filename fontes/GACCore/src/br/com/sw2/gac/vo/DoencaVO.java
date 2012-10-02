package br.com.sw2.gac.vo;

/**
 * <b>Descrição: Classe que representa uma doença no sistema.</b> <br>
 * .
 * @author: lucianor
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class DoencaVO {

    /** Atributo codigo cid. */
    private String codigoCID;

    /** Atributo nome doenca. */
    private String nomeDoenca;

    /** Atributo tipo doenca. */
    private TipoDoencaVO tipoDoenca;

    public String getCodigoCID() {
        return codigoCID;
    }

    public void setCodigoCID(String codigoCID) {
        this.codigoCID = codigoCID;
    }

    public String getNomeDoenca() {
        return nomeDoenca;
    }

    public void setNomeDoenca(String nomeDoenca) {
        this.nomeDoenca = nomeDoenca;
    }

    public TipoDoencaVO getTipoDoenca() {
        return tipoDoenca;
    }

    public void setTipoDoenca(TipoDoencaVO tipoDoenca) {
        this.tipoDoenca = tipoDoenca;
    }
}
