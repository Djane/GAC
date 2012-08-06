package br.com.sw2.gac.tools;

/**
 * <b>Descrição:</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public enum TipoOcorrencia {

    /** Atributo Emergencia. */
    Emergencia(1, "Atendimento de Emerg�ncia"),

    /** Atributo Atendimento realizado. */
    AtendimentoRealizado(2, "Atendimento Realizado"),

    /** Atributo Comercial. */
    Comercial(3, "�rea Comercial"),

    /** Atributo Tecnica. */
    Tecnica(4, "�rea T�cnica"),

    /** Atributo Financeira. */
    Financeira(5, "�rea Financeira");

    /** Atributo cod tipo ocorrencia. */
    private Integer value;

    /** Atributo des tipo ocorrencia. */
    private String label;


    /**
     * Construtor Padrao
     * Instancia um novo objeto TipoOcorrencia.
     *
     * @param value the value
     * @param label the label
     */
    private TipoOcorrencia(Integer value, String label) {
        this.value = value;
        this.label = label;
    }


    public Integer getValue() {
        return value;
    }


    public void setValue(Integer value) {
        this.value = value;
    }


    public String getLabel() {
        return label;
    }


    public void setLabel(String label) {
        this.label = label;
    }

}
