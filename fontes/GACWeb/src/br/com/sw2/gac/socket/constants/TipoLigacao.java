package br.com.sw2.gac.socket.constants;

/**
 * <b>Descrição: Enum que classifica os tipos de ligações</b> <br>.
 *
 * @author: SW2
 * @version 1.0
 *
 * Copyright 2013 SmartAngel.
 */
public enum TipoLigacao {

    /** Atributo indefinido. */
    INDEFINIDO(0, "Indefinido"),

    /** Ligação feita para o cliente.*/
    COM_CLIENTE(1, "Cliente"),

    /** Ligação feita para o contato.*/
    COM_CONTATO(2, "Contato"),

    /** Atributo outros. */
    OUTROS(3, "Outros");



    /**
     * Construtor Padrao
     * Instancia um novo objeto TipoLigacao.
     *
     * @param value the value
     * @param label the label
     */
    private TipoLigacao(Integer value, String label) {
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
