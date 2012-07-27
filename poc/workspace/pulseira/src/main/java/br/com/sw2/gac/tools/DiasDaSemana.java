package br.com.sw2.gac.tools;

/**
 * <b>Descrição:</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public enum DiasDaSemana {

    /** Atributo. */
    Domingo(1, "domingo"),
    Segunda(2, "segunda-feira"),
    Terca(3, "terça-feira"),
    Quarta(4, "quarta-Feira"),
    Quinta(5, "quinta-Feira"),
    Sexta(6, "sexta-feira"),
    Sabado(7, "Sábado");

    /** Atributo value. */
    private Integer value;

    /** Atributo label. */
    private String label;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    private DiasDaSemana(int value, String label) {
        this.value = value;
        this.label = label;
    }

}
