package br.com.sw2.gac.tools;

/**
 * <b>Descrição:</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public enum UFBrasil {

    /** Atributo ac. */
    AC("AC", "Acre"),
    /** Atributo al. */
    AL("AL", "Alagoas"),
    /** Atributo ap. */
    AP("AP", "Amapá"),
    /** Atributo am. */
    AM("AM", "Amazonas"),
    /** Atributo ba. */
    BA("BA", "Bahia"),
    /** Atributo ce. */
    CE("CE", "Ceará"),
    /** Atributo df. */
    DF("DF", "Distrito Federal"),
    /** Atributo es. */
    ES("ES", "Espírito Santo"),
    /** Atributo go. */
    GO("GO", "Goiás"),
    /** Atributo ma. */
    MA("MA", "Maranhão"),
    /** Atributo mt. */
    MT("MT", "Mato Grosso"),
    /** Atributo ms. */
    MS("MS", "Mato Grosso do Sul"),
    /** Atributo mg. */
    MG("MG", "Minas Gerais"),
    /** Atributo pa. */
    PA("PA", "Pará"),
    /** Atributo pb. */
    PB("PB", "Paraíba"),
    /** Atributo pr. */
    PR("PR", "Paraná"),
    /** Atributo pe. */
    PE("PE", "Pernambuco"),
    /** Atributo pi. */
    PI("PI", "Piauí"),
    /** Atributo rr. */
    RR("RR", "Roraima"),
    /** Atributo ro. */
    RO("RO", "Rondônia"),
    /** Atributo rj. */
    RJ("RJ", "Rio de Janeiro"),
    /** Atributo rn. */
    RN("RN", "Rio Grande do Norte"),
    /** Atributo rs. */
    RS("RS", "Rio Grande do Sul"),
    /** Atributo sc. */
    SC("SC", "Santa Catarina"),
    /** Atributo sp. */
    SP("SP", "São Paulo"),
    /** Atributo se. */
    SE("SE", "Sergipe"),
    /** Atributo to. */
    TO("TO", "Tocantins");

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

    /**
     * Construtor Padrao Instancia um novo objeto UFBrasil.
     * @param value the value
     * @param label the label
     */
    private UFBrasil(String value, String label) {
        this.value = value;
        this.label = label;
    }

}
