package br.com.sw2.gac.tools;

/**
 * <b>Descrição: ENUM que indica os Status possÃ­veis dos Dispositivos</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public enum EstadoDispositivo {

    /** Atributo Novo. */
    Novo(1, "Novo"),
    /** Atributo Pronto. */
    Pronto(2, "Pronto"),
    /** Atributo Uso. */
    Uso(3, "Uso"),
    /** Atributo Devolvido. */
    Devolvido(4, "Devolvido"),
    /** Atributo Manutencao. */
    Manutencao(5, "Manutenção"),
    /** Atributo Defeito. */
    Defeito(6, "Defeito"),
    /** Atributo Descarte. */
    Descarte(7, "Descarte"),
    /** Atributo Fabrica. */
    Fabrica(8, "Fabrica");

    /**
     * Construtor Padrao Instancia um novo objeto EstadoDispositivo.
     * @param value the value
     * @param label the label
     */
    private EstadoDispositivo(Integer value, String label) {
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
