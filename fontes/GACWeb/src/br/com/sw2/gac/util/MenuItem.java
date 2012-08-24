package br.com.sw2.gac.util;

/**
 * <b>Descrição: Enum que lista os codigos e views ID das páginas.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public enum MenuItem {

    /** Atributo login. */
    LOGIN(1, "login", new Integer[] {}),

    /** Atributo cadastro usuario. */
    CADASTRO_USUARIO(2, "cadastrousuario", new Integer[] { 1 }),
    /** Cadastro de dispositivos. */
    CADASTRO_DISPOSITIVO(3, "cadastroDispositivo", new Integer[] { 1 }),

    /** Atributo cadastro script. */
    CADASTRO_SCRIPT(4, "scriptAtendimento" , new Integer[] { 1 }),
    /** Movimentacao do status do dispositivo. */
    MOVIMENTACAO_DISPOSITIVO(5, "movimentacaoDispositivo" , new Integer[] { 1 }),

    /** Cadastro de Parametros. */
    CADASTRO_PARAMETROS(6, "parametros" , new Integer[] { 1 }),

    /** Atributo cadastro sms. */
    CADASTRO_SMS(70, "cadastroSms" , new Integer[] { 1 }),

    /** Atributo cadastro sms. */
    PESQUISAR_CONTRATO(7, "pesquisaContrato" , new Integer[] { 1 }),

    /** Carga dos dispositivos. */
    CARGA_DISPOSITIVOS(8, "cargaDispositivo" , new Integer[] { 1 });

    /** Atributo codigo modulo. */
    private Integer codigoModulo;

    /** Atributo view id. */
    private String viewID;

    /** Atributo perfil autorizado. */
    private Integer[] perfilAutorizado;

    /**
     * Nome: getCodigoModulo Recupera o valor do atributo 'codigoModulo'.
     * @return valor do atributo 'codigoModulo'
     * @see
     */
    public Integer getCodigoModulo() {
        return codigoModulo;
    }

    /**
     * Nome: getPerfilAutorizado Recupera o valor do atributo 'perfilAutorizado'.
     * @return valor do atributo 'perfilAutorizado'
     * @see
     */
    public Integer[] getPerfilAutorizado() {
        return perfilAutorizado;
    }

    /**
     * Nome: setPerfilAutorizado Registra o valor do atributo 'perfilAutorizado'.
     * @param perfilAutorizado valor do atributo perfil autorizado
     * @see
     */
    public void setPerfilAutorizado(Integer[] perfilAutorizado) {
        this.perfilAutorizado = perfilAutorizado;
    }

    /**
     * Construtor Padrao Instancia um novo objeto MenuItem.
     * @param codigoModulo the codigo modulo
     * @param viewID the view id
     * @param autorizacoes the autorizacoes
     */
    private MenuItem(Integer codigoModulo, String viewID, Integer[] autorizacoes) {
        this.codigoModulo = codigoModulo;
        this.viewID = viewID;
        this.perfilAutorizado = autorizacoes;
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
