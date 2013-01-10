package br.com.sw2.gac.tools;

/**
 * <b>Descri��o:</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public enum GrauRelacao {

    /** Atributo Amigo. */
    Amigo("1", "Amigo"),
    /** Atributo Filho. */
    Filho("2", "Filho"),
    /** Atributo Irm�o. */
    Irmao("3", "Irmão"),
    /** Atributo Mae. */
    Mae("4", "Mãe"),
    /** Atributo Pai. */
    Pai("5", "Pai"),
    /** Atributo Tio. */
    Tio("6", "Tio"),
    /** Atributo Outros. */
    Outros("7", "Outros");

    /**
     * Construtor Padrao Instancia um novo objeto GrauRelacao.
     * @param value the value
     * @param label the label
     */
    private GrauRelacao(String value, String label) {
        this.value = value;
        this.label = label;
    }

    /** Atributo value. */
    private String value;

    /** Atributo label. */
    private String label;

    /**
     * Nome: getValue Recupera o valor do atributo 'value'.
     * @return valor do atributo 'value'
     * @see
     */
    public String getValue() {
        return value;
    }

    /**
     * Nome: setValue Registra o valor do atributo 'value'.
     * @param value valor do atributo value
     * @see
     */
    public void setValue(String value) {
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
