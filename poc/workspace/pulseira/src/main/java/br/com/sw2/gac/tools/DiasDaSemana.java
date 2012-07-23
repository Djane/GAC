package br.com.sw2.gac.tools;

/**
 * <b>Descrição:</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public enum DiasDaSemana {

    /** Atributo Janeiro. */
    Sunday(1, "domingo"),
    Monday(2, "segunda-feira"),
    Tuesday(3, "terça-feira"),
    Wednesday(4, "quarta-Feira"),
    Thursday(5, "quinta-Feira"),
    Friday(6, "sexta-feira"),
    Saturday(7, "Sábado");

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
