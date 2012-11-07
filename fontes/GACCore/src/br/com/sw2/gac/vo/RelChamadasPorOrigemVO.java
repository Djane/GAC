package br.com.sw2.gac.vo;

import java.util.Date;

/**
 * <b>Descrição: Classe que representa o VO do relatório Chamadas por Origem.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class RelChamadasPorOrigemVO {

    /** Atributo dia. */
    private Date dia;

    /** Atributo per inicio. */
    private Date perInicio;

    /** Atributo per fim. */
    private Date perFim;

    /** Atributo recebidas. */
    private Integer recebidas;

    /** Atributo efet progr. */
    private Integer efetProgr;

    /** Atributo efet emerg. */
    private Integer efetEmerg;

    /**
     * Nome: getDia Recupera o valor do atributo 'dia'.
     * @return valor do atributo 'dia'
     * @see
     */
    public Date getDia() {
        return dia;
    }

    /**
     * Nome: setDia Registra o valor do atributo 'dia'.
     * @param dia valor do atributo dia
     * @see
     */
    public void setDia(Date dia) {
        this.dia = dia;
    }

    /**
     * Nome: getPerInicio Recupera o valor do atributo 'perInicio'.
     * @return valor do atributo 'perInicio'
     * @see
     */
    public Date getPerInicio() {
        return perInicio;
    }

    /**
     * Nome: setPerInicio Registra o valor do atributo 'perInicio'.
     * @param perInicio valor do atributo per inicio
     * @see
     */
    public void setPerInicio(Date perInicio) {
        this.perInicio = perInicio;
    }

    /**
     * Nome: getPerFim Recupera o valor do atributo 'perFim'.
     * @return valor do atributo 'perFim'
     * @see
     */
    public Date getPerFim() {
        return perFim;
    }

    /**
     * Nome: setPerFim Registra o valor do atributo 'perFim'.
     * @param perFim valor do atributo per fim
     * @see
     */
    public void setPerFim(Date perFim) {
        this.perFim = perFim;
    }

    /**
     * Nome: getRecebidas Recupera o valor do atributo 'recebidas'.
     * @return valor do atributo 'recebidas'
     * @see
     */
    public Integer getRecebidas() {
        return recebidas;
    }

    /**
     * Nome: setRecebidas Registra o valor do atributo 'recebidas'.
     * @param recebidas valor do atributo recebidas
     * @see
     */
    public void setRecebidas(Integer recebidas) {
        this.recebidas = recebidas;
    }

    /**
     * Nome: getEfetProgr Recupera o valor do atributo 'efetProgr'.
     * @return valor do atributo 'efetProgr'
     * @see
     */
    public Integer getEfetProgr() {
        return efetProgr;
    }

    /**
     * Nome: setEfetProgr Registra o valor do atributo 'efetProgr'.
     * @param efetProgr valor do atributo efet progr
     * @see
     */
    public void setEfetProgr(Integer efetProgr) {
        this.efetProgr = efetProgr;
    }

    /**
     * Nome: getEfetEmerg Recupera o valor do atributo 'efetEmerg'.
     * @return valor do atributo 'efetEmerg'
     * @see
     */
    public Integer getEfetEmerg() {
        return efetEmerg;
    }

    /**
     * Nome: setEfetEmerg Registra o valor do atributo 'efetEmerg'.
     * @param efetEmerg valor do atributo efet emerg
     * @see
     */
    public void setEfetEmerg(Integer efetEmerg) {
        this.efetEmerg = efetEmerg;
    }

}
