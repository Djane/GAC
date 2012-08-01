package br.com.sw2.gac.tools;

/**
 * <b>Descrição: Enum contendo os estados de um dispositivos.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public enum EstadoDispositivo {

    /** Atributo Descarte. */
    Descarte("1", "Descarte"), 
    /** Atributo Devolvido. */
    Devolvido("2", "Devolvido"), 
    /** Atributo Defeito. */
    Defeito("3", "Dispositivo com Defeito"), 
    /** Atributo Novo. */
    Novo("4", "Dispositivo em Estado Novo"),
    /** Atributo Instalado. */
    Instalado("5", "Instalado no cliente"),
    /** Atributo Manutencao. */
    Manutencao("6", "Manutenção"),
    /** Atributo Pronto uso. */
    ProntoUso("7", "Pronto para Uso"),
    /** Atributo Retorno fabrica. */
    RetornoFabrica("8", "Retorno para Fábrica");

    /** Atributo value. */
    private String value;

    /** Atributo label. */
    private String label;

    /**
     * Construtor Padrao Instancia um novo objeto Sexo.
     * @param value the value
     * @param label the label
     */
    private EstadoDispositivo(String value, String label) {
        this.value = value;
        this.label = label;
    }

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

}
