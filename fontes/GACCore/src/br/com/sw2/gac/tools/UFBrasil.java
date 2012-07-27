package br.com.sw2.gac.tools;

/**
 * <b>Descrição:</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public enum UFBrasil {

    /** Atributo ac. */
    AC("Acre"),
    /** Atributo al. */
    AL("Alagoas"),
    /** Atributo ap. */
    AP("Amapá"),
    /** Atributo am. */
    AM("Amazonas"),
    /** Atributo ba. */
    BA("Bahia"),
    /** Atributo ce. */
    CE("Ceará"),
    /** Atributo df. */
    DF("Distrito Federal"),
    /** Atributo es. */
    ES("Espírito Santo"),
    /** Atributo go. */
    GO("Goiás"),
    /** Atributo ma. */
    MA("Maranhão"),
    /** Atributo mt. */
    MT("Mato Grosso"),
    /** Atributo ms. */
    MS("Mato Grosso do Sul"),
    /** Atributo mg. */
    MG("Minas Gerais"),
    /** Atributo pa. */
    PA("Pará"),
    /** Atributo pb. */
    PB("Paraíba"),
    /** Atributo pr. */
    PR("Paraná"),
    /** Atributo pe. */
    PE("Pernambuco"),
    /** Atributo pi. */
    PI("Piauí"),
    /** Atributo rr. */
    RR("Roraima"),
    /** Atributo ro. */
    RO("Rondônia"),
    /** Atributo rj. */
    RJ("Rio de Janeiro"),
    /** Atributo rn. */
    RN("Rio Grande do Norte"),
    /** Atributo rs. */
    RS("Rio Grande do Sul"),
    /** Atributo sc. */
    SC("Santa Catarina"),
    /** Atributo sp. */
    SP("São Paulo"),
    /** Atributo se. */
    SE("Sergipe"),
    /** Atributo to. */
    TO("Tocantins");

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
     * Construtor Padrao Instancia um novo objeto EstadosBrasileiros.
     * @param value the value
     */
    private UFBrasil(String value) {
        this.value = value;
    }

}
