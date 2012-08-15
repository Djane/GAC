package br.com.sw2.gac.vo;

import java.io.Serializable;

/**
 * <b>Descrição:</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class ParametroVO implements Serializable {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = -5777458164226753051L;

    /** Atributo id parametro. */
    private Integer idParametro;

    /** Atributo dias dados. */
    private Integer diasDados;

    /** Atributo dias bem estar. */
    private Integer diasBemEstar;

    /** Atributo tolera rotina cliente. */
    private Integer toleraRotinaCliente;

    /**
     * Nome: getIdParametro Recupera o valor do atributo 'idParametro'.
     * @return valor do atributo 'idParametro'
     * @see
     */
    public Integer getIdParametro() {
        return idParametro;
    }

    /**
     * Nome: setIdParametro Registra o valor do atributo 'idParametro'.
     * @param idParametro valor do atributo id parametro
     * @see
     */
    public void setIdParametro(Integer idParametro) {
        this.idParametro = idParametro;
    }

    /**
     * Nome: getDiasDados Recupera o valor do atributo 'diasDados'.
     * @return valor do atributo 'diasDados'
     * @see
     */
    public Integer getDiasDados() {
        return diasDados;
    }

    /**
     * Nome: setDiasDados Registra o valor do atributo 'diasDados'.
     * @param diasDados valor do atributo dias dados
     * @see
     */
    public void setDiasDados(Integer diasDados) {
        this.diasDados = diasDados;
    }

    /**
     * Nome: getDiasBemEstar Recupera o valor do atributo 'diasBemEstar'.
     * @return valor do atributo 'diasBemEstar'
     * @see
     */
    public Integer getDiasBemEstar() {
        return diasBemEstar;
    }

    /**
     * Nome: setDiasBemEstar Registra o valor do atributo 'diasBemEstar'.
     * @param diasBemEstar valor do atributo dias bem estar
     * @see
     */
    public void setDiasBemEstar(Integer diasBemEstar) {
        this.diasBemEstar = diasBemEstar;
    }

    /**
     * Nome: getToleraRotinaCliente Recupera o valor do atributo 'toleraRotinaCliente'.
     * @return valor do atributo 'toleraRotinaCliente'
     * @see
     */
    public Integer getToleraRotinaCliente() {
        return toleraRotinaCliente;
    }

    /**
     * Nome: setToleraRotinaCliente Registra o valor do atributo 'toleraRotinaCliente'.
     * @param toleraRotinaCliente valor do atributo tolera rotina cliente
     * @see
     */
    public void setToleraRotinaCliente(Integer toleraRotinaCliente) {
        this.toleraRotinaCliente = toleraRotinaCliente;
    }

    /**
     * Nome: getSerialversionuid Recupera o valor do atributo 'serialversionuid'.
     * @return valor do atributo 'serialversionuid'
     * @see
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}
