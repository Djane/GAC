package br.com.sw2.gac.vo;

/**
 * <b>Descrição: Classe que representa a movimentação de entrada e cancelamentos de clientes em um dia.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class MovimentacaoClienteVO {

    /** Atributo dia. */
    private int dia;

    /** Atributo entrante. */
    private int entrante;

    /** Atributo cancelado. */
    private int cancelado;

    /**
     * Nome: getDia Recupera o valor do atributo 'dia'.
     * @return valor do atributo 'dia'
     * @see
     */
    public int getDia() {
        return dia;
    }

    /**
     * Nome: setDia Registra o valor do atributo 'dia'.
     * @param dia valor do atributo dia
     * @see
     */
    public void setDia(int dia) {
        this.dia = dia;
    }

    /**
     * Nome: getEntrante Recupera o valor do atributo 'entrante'.
     * @return valor do atributo 'entrante'
     * @see
     */
    public int getEntrante() {
        return entrante;
    }

    /**
     * Nome: setEntrante Registra o valor do atributo 'entrante'.
     * @param entrante valor do atributo entrante
     * @see
     */
    public void setEntrante(int entrante) {
        this.entrante = entrante;
    }

    /**
     * Nome: getCancelado Recupera o valor do atributo 'cancelado'.
     * @return valor do atributo 'cancelado'
     * @see
     */
    public int getCancelado() {
        return cancelado;
    }

    /**
     * Nome: setCancelado Registra o valor do atributo 'cancelado'.
     * @param cancelado valor do atributo cancelado
     * @see
     */
    public void setCancelado(int cancelado) {
        this.cancelado = cancelado;
    }

}
