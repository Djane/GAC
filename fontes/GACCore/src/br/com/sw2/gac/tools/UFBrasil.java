package br.com.sw2.gac.tools;

/**
 * <b>Descri��o:</b> <br>
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
    AP("Amap�"),
    /** Atributo am. */
    AM("Amazonas"),
    /** Atributo ba. */
    BA("Bahia"),
    /** Atributo ce. */
    CE("Cear�"),
    /** Atributo df. */
    DF("Distrito Federal"),
    /** Atributo es. */
    ES("Esp�rito Santo"),
    /** Atributo go. */
    GO("Goi�s"),
    /** Atributo ma. */
    MA("Maranh�o"),
    /** Atributo mt. */
    MT("Mato Grosso"),
    /** Atributo ms. */
    MS("Mato Grosso do Sul"),
    /** Atributo mg. */
    MG("Minas Gerais"),
    /** Atributo pa. */
    PA("Par�"),
    /** Atributo pb. */
    PB("Para�ba"),
    /** Atributo pr. */
    PR("Paran�"),
    /** Atributo pe. */
    PE("Pernambuco"),
    /** Atributo pi. */
    PI("Piau�"),
    /** Atributo rr. */
    RR("Roraima"),
    /** Atributo ro. */
    RO("Rond�nia"),
    /** Atributo rj. */
    RJ("Rio de Janeiro"),
    /** Atributo rn. */
    RN("Rio Grande do Norte"),
    /** Atributo rs. */
    RS("Rio Grande do Sul"),
    /** Atributo sc. */
    SC("Santa Catarina"),
    /** Atributo sp. */
    SP("S�o Paulo"),
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
