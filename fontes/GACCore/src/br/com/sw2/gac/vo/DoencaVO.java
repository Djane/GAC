package br.com.sw2.gac.vo;

import java.io.Serializable;

/**
 * <b>Descrição: Classe que representa uma doença no sistema.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class DoencaVO extends BaseVO implements Serializable {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = -4341285251531892766L;

    /** 
     * Código de identificação de uma doença
     * Código internacional de doenças
     *  */
    private String codigoCID;

    /** 
     * Descrição da doença
     *  */
    private String nomeDoenca;

    /** 
     * Categoria ou grupo ao qual a doença se encaixa 
     * */
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
