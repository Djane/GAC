package br.com.sw2.gac.tools;

/**
 * <b>Descrição: Classe que lista a periodicidade dos tratamentos.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public enum Periodicidade {

    /** Atributo diario. */
    DIARIO(1, "Diário"),

    /** Atributo semanal. */
    SEMANAL(2, "Semanal"),

    /** Atributo mensal. */
    MENSAL(3, "Mensal");

    /**
     * Construtor Padrao Instancia um novo objeto Periodicicade.
     * @param value the value
     * @param label the label
     */
    private Periodicidade(Integer value, String label) {
        this.value = value;
        this.label = label;
    }

    /** Atributo value. */
    private Integer value;

    /** Atributo label. */
    private String label;

    /**
     * Nome: getValue Recupera o valor do atributo 'value'.
     * @return valor do atributo 'value'
     * @see
     */
    public Integer getValue() {
        return value;
    }

    /**
     * Nome: setValue Registra o valor do atributo 'value'.
     * @param value valor do atributo value
     * @see
     */
    public void setValue(Integer value) {
        this.value = value;
    }

    /**
     * Nome: getLabel Recupera o valor do atributo 'label'.
     * @return valor do atributo 'label'
     * @see
     */
    public String getLabel() {
        return label;
    }

    /**
     * Nome: setLabel Registra o valor do atributo 'label'.
     * @param label valor do atributo label
     * @see
     */
    public void setLabel(String label) {
        this.label = label;
    }

}
