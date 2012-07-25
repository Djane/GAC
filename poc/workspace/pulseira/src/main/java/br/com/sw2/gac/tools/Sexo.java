package br.com.sw2.gac.tools;

/**
 * <b>Descrição:</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public enum Sexo {

    /** Atributo Masculino. */
    Masculino("M", "Masculino"),

    /** Atributo Feminino. */
    Feminino("F", "Feminino");

    /** Atributo value. */
    private String value;

    /** Atributo label. */
    private String label;

    /**
     * Construtor Padrao Instancia um novo objeto Sexo.
     * @param value the value
     * @param label the label
     */
    private Sexo(String value, String label) {
        this.value = value;
        this.label = label;
    }

    /**
     * Nome: getValue
     * Recupera o valor do atributo 'value'.
     *
     * @return valor do atributo 'value'
     * @see
     */
    public String getValue() {
        return value;
    }

    /**
     * Nome: setValue
     * Registra o valor do atributo 'value'.
     *
     * @param value valor do atributo value
     * @see
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Nome: getLabel
     * Recupera o valor do atributo 'label'.
     *
     * @return valor do atributo 'label'
     * @see
     */
    public String getLabel() {
        return label;
    }

    /**
     * Nome: setLabel
     * Registra o valor do atributo 'label'.
     *
     * @param label valor do atributo label
     * @see
     */
    public void setLabel(String label) {
        this.label = label;
    }

}
