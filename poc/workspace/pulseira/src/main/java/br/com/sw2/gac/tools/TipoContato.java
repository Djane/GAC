package br.com.sw2.gac.tools;


/**
 * <b>Descrição:</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public enum TipoContato {

    /** Atributo telefone celular. */
    TELEFONE_CELULAR("1", "Telefone Celular"),

    /** Atributo telefone comercial. */
    TELEFONE_COMERCIAL("2", "Telefone Comercial"),

    /** Atributo telefone residencial. */
    TELEFONE_RESIDENCIAL("3", "Telefone Residencial"),

    /** Atributo email. */
    EMAIL("4", "Email");

    /** Atributo value. */
    private String value;

    /** Atributo label. */
    private String label;

    /**
     * Construtor Padrao Instancia um novo objeto Sexo.
     * @param value the value
     * @param label the label
     */
    private TipoContato(String value, String label) {
        this.value = value;
        this.label = label;
    }

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
