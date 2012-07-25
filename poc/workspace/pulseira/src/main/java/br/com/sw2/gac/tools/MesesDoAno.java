package br.com.sw2.gac.tools;

/**
 * <b>Descrição:</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public enum MesesDoAno {

    /** Atributo Janeiro. */
    Janeiro(1, "Janeiro"),
    Fevereiro(2, "Fevereiro"),
    Marco(3, "Março"),
    Abril(4, "Abril"),
    Maio(5, "Maio"),
    Junho(6, "Junho"),
    Julho(7, "Julho"),
    Agosto(8, "Agosto"),
    Setembro(9, "Setembro"),
    Outubro(10, "Outubro"),
    Novembro(11, "Novembro"),
    Dezembro(12, "Dezembro");

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

    private MesesDoAno(int value, String label) {
        this.value = value;
        this.label = label;
    }

}
