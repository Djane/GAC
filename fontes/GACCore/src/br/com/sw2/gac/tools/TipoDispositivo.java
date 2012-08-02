package br.com.sw2.gac.tools;

/**
 * <b>Descrição: ENUM que define os Tipos de Dispositivos que podem ser utilizados na
 * AplicaÃ§Ã£o.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public enum TipoDispositivo {

    /** Atributo Pulseira. */
    Pulseira(1, "Pulseira"),

    /** Atributo Central eletronica. */
    CentralEletronica(2, "Central Eletronica"),

    /** Atributo Relogio. */
    Relogio(3, "Relógio"),

    /** Atributo Pingente. */
    Pingente(4, "Pingente");

    /**
     * Construtor Padrao Instancia um novo objeto TipoDispositivo.
     * @param value the value
     * @param label the label
     */
    private TipoDispositivo(Integer value, String label) {
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
