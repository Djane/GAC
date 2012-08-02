package br.com.sw2.gac.tools;

/**
 * <b>DescriÁ„o: ENUM respons√°vel para identificar os sinais provenientes do Dispositivo.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public enum SinalDispositivo {

    /** Atributo Convulsao. */
    Convulsao(1, "Convuls√£o"),

    /** Atributo Imobilidade. */
    Imobilidade(2, "Imobilidade"),

    /** Atributo Queda. */
    Queda(3, "Queda"),

    /** Atributo Sem pulseira. */
    SemPulseira(4, "Sem Pulseira");

    /**
     * Construtor Padrao Instancia um novo objeto SinalDispositivo.
     * @param value the value
     * @param label the label
     */
    private SinalDispositivo(Integer value, String label) {
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
