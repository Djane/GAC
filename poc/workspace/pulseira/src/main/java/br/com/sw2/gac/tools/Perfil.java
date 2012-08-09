package br.com.sw2.gac.tools;

/**
 * <b>Descrição: Enum que define os Perfils de Acesso ao Aplicativo.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public enum Perfil {

    /** Atributo Administrador. */
    Administrador(1, "Gerencial"),
    /** Atributo Gerente. */
    Gerente(2, "BackOffice"),
    /** Atributo Atendente. */
    Atendente_N1(3, "Usuário/Operador (Nivel 1)"),
    /** Atributo Atendente plus. */
    Atendente_N2(3, "Usuário/Operador (Nivel 2)");

    /** Atributo value. */
    private int value;

    /** Atributo label. */
    private String label;

    /**
     * Construtor Padrao Instancia um novo objeto Perfil.
     * @param value the value
     * @param label the label
     */
    private Perfil(int value, String label) {
        this.value = value;
        this.label = label;
    }

    /**
     * Nome: getValue Recupera o valor do atributo 'value'.
     * @return valor do atributo 'value'
     * @see
     */
    public int getValue() {
        return value;
    }

    /**
     * Nome: setValue Registra o valor do atributo 'value'.
     * @param value valor do atributo value
     * @see
     */
    public void setValue(int value) {
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
