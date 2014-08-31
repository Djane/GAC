package br.com.sw2.gac.vo;

/**
 * <b>Descrição: Classe que representa a movimenta��o de entrada e cancelamentos de clientes em um dia.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class MovimentacaoClienteVO {

    /** Atributo dia. */
    private Integer dia;

    /** Atributo entrante. */
    private Integer entrante;

    /** Atributo cancelado. */
    private Integer cancelado;

    /** Atributo suspenso. */
    private Integer suspenso;

    public Integer getDia() {
        return dia;
    }

    public void setDia(Integer dia) {
        this.dia = dia;
    }

    public Integer getEntrante() {
        return entrante;
    }

    public void setEntrante(Integer entrante) {
        this.entrante = entrante;
    }

    public Integer getCancelado() {
        return cancelado;
    }

    public void setCancelado(Integer cancelado) {
        this.cancelado = cancelado;
    }

    public Integer getSuspenso() {
        return suspenso;
    }

    public void setSuspenso(Integer suspenso) {
        this.suspenso = suspenso;
    }

}
