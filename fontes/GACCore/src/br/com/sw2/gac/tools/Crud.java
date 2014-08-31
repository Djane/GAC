package br.com.sw2.gac.tools;

/**
 * <b>Descrição:</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public enum Crud {

    /** Atributo Create. */
    Create("C"),

    /** Atributo Read. */
    Read("R"),

    /** Atributo Update. */
    Update("U"),

    /** Atributo Delete. */
    Delete("D");

    /** Atributo value. */
    private String value;

    /**
     * Construtor Padrao Instancia um novo objeto Crud.
     * @param value the value
     */
    private Crud(String value) {
        this.value = value;
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

}
