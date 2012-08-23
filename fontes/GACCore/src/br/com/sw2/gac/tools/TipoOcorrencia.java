package br.com.sw2.gac.tools;

/**
 * <b>Descrição:</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public enum TipoOcorrencia {

    /** Atributo Emergencia. */
    Emergencia("AT1", "Atendimento de Emergência"),

    /** Atributo Atendimento realizado. */
    AtendimentoRealizado("AT2", "Atendimento Realizado"),

    /** Atributo Comercial. */
    Comercial("CO1", "Área Comercial"),

    /** Atributo Tecnica. */
    Tecnica("TE1", "Área Técnica"),

    /** Atributo Financeira. */
    Financeira("FI1", "Área Financeira");

    /** Atributo cod tipo ocorrencia. */
    private String value;

    /** Atributo des tipo ocorrencia. */
    private String label;


    /**
     * Construtor Padrao
     * Instancia um novo objeto TipoOcorrencia.
     *
     * @param value the value
     * @param label the label
     */
    private TipoOcorrencia(String value, String label) {
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
     * Nome: getLabel
     * Recupera o valor do atributo 'label'.
     *
     * @return valor do atributo 'label'
     * @see
     */
    public String getLabel() {
        return label;
    }
}
