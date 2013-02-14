package br.com.sw2.gac.socket.constants;

/**
 * <b>Descrição: Status das ligações do software</b> <br>.
 *
 * @author: SW2
 * @version 1.0
 *
 * Copyright 2013 SmartAngel.
 */
public enum StatusLigacao {

    /** Atributo livre. */
    LIVRE(1, "Livre"), /** Atributo pausa. */
    PAUSA(2, "Pausa"), /** Atributo falando. */
    FALANDO(3, "Falanda");

    /**
     * Construtor Padrao
     * Instancia um novo objeto StatusLigacao.
     *
     * @param value the value
     * @param label the label
     */
    private StatusLigacao(Integer value, String label) {
        this.value = value;
        this.label = label;
    }

    /** Atributo value. */
    private Integer value;

    /** Atributo label. */
    private String label;

    /**
     * Nome: getValue
     * Recupera o valor do atributo 'value'.
     *
     * @return valor do atributo 'value'
     * @see
     */
    public Integer getValue() {
        return value;
    }

    /**
     * Nome: setValue
     * Registra o valor do atributo 'value'.
     *
     * @param value valor do atributo value
     * @see
     */
    public void setValue(Integer value) {
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
