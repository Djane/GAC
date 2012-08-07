package br.com.sw2.gac.util;

/**
 * <b>Descrição: Enum cuq elista os codigos e views ID das páginas.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public enum MenuItem {

    /** Atributo login. */
    LOGIN(1, "login"),

    /** Atributo cadastro usuario. */
    CADASTRO_USUARIO(2, "cadastrousuario");

    /** Atributo codigo modulo. */
    private Integer codigoModulo;

    /** Atributo view id. */
    private String viewID;

    /**
     * Nome: getCodigoModulo Recupera o valor do atributo 'codigoModulo'.
     * @return valor do atributo 'codigoModulo'
     * @see
     */
    public Integer getCodigoModulo() {
        return codigoModulo;
    }

    /**
     * Construtor Padrao Instancia um novo objeto MenuItem.
     * @param codigoModulo the codigo modulo
     * @param viewID the view id
     */
    private MenuItem(Integer codigoModulo, String viewID) {
        this.codigoModulo = codigoModulo;
        this.viewID = viewID;
    }

    /**
     * Nome: setCodigoModulo Registra o valor do atributo 'codigoModulo'.
     * @param codigoModulo valor do atributo codigo modulo
     * @see
     */
    public void setCodigoModulo(Integer codigoModulo) {
        this.codigoModulo = codigoModulo;
    }

    /**
     * Nome: getViewID Recupera o valor do atributo 'viewID'.
     * @return valor do atributo 'viewID'
     * @see
     */
    public String getViewID() {
        return viewID;
    }

    /**
     * Nome: setViewID Registra o valor do atributo 'viewID'.
     * @param viewID valor do atributo view id
     * @see
     */
    public void setViewID(String viewID) {
        this.viewID = viewID;
    }

}
