package br.com.sw2.gac.tools;

/**
 * <b>Descrição:</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public enum GrauRelacao {

    /** Atributo Amigo. */
    Amigo("1"),
    /** Atributo Filho. */
    Filho("2"),
    /** Atributo Irmão. */
    Irmão("3"),
    /** Atributo Mae. */
    Mae("4"),
    /** Atributo Pai. */
    Pai("5"),
    /** Atributo Tio. */
    Tio("6"),
    /** Atributo Outros. */
    Outros("7");

    /** Atributo value. */
    private String value;

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
     * Construtor Padrao Instancia um novo objeto GrauRelacao.
     * @param value the value
     */
    private GrauRelacao(String value) {
        this.value = value;
    }

}
