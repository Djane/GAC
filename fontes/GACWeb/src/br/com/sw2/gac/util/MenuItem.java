package br.com.sw2.gac.util;

/**
 * <b>Descrição: Enum que lista os codigos e views ID das páginas.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public enum MenuItem {

    /** Atributo login. */
    LOGIN(1, "login", "", new Integer[] {}),

    /** Atributo cadastro usuario. */
    CADASTRO_USUARIO(2, "cadastrousuario", "label.telausuario.view.title", new Integer[] { 1, 2, 3, 4}),

    /** Cadastro de dispositivos. */
    CADASTRO_DISPOSITIVO(3, "cadastroDispositivo", "label.cadastrodispositivo.view.title", new Integer[] { 1, 2 }),

    /** Atributo cadastro script. */
    CADASTRO_SCRIPT(4, "scriptAtendimento", "label.scripts.view.title", new Integer[] { 1, 2 }),

    /** Movimentacao do status do dispositivo. */
    MOVIMENTACAO_DISPOSITIVO(5, "movimentacaoDispositivo", "", new Integer[] { 1, 2 }),

    /** Cadastro de Parametros. */
    CADASTRO_PARAMETROS(6, "parametros", "label.parametros.view.title", new Integer[] { 1, 2 }),

    /** Atributo cadastro sms. */
    CADASTRO_SMS(70, "cadastroSms", "label.smspadrao.view.title", new Integer[] { 1, 2 }),

    /** Atributo cadastro sms. */
    PESQUISAR_CONTRATO(7, "pesquisaContrato", "label.pesquisacontrato.view.title", new Integer[] { 1, 2 }),

    /** Carga dos dispositivos. */
    CARGA_DISPOSITIVOS(8, "cargaDispositivo", "", new Integer[] { 1, 2 }),

    /** Relatório histórico dos dispositivos. */
    REL_HIST_DISPOSITIVOS(9, "relHistDispositivo", "", new Integer[] { 1, 2 }),

    /** Carga dos dispositivos. */
    CADASTRO_PACOTES_OFERECIDOS(10, "pacotesoferecidos", "label.pacotesoferecidos.view.title", new Integer[] { 1, 2 }),

    /** Atributo contratos. */
    CONTRATOS(90, "contrato", "label.contrato.view.title", new Integer[] { 1, 2 }),

    /** Atributo atendimento ocorrencia. */
    ATENDIMENTO_OCORRENCIA(100, "atendimento", "label.atendimento.view.title", new Integer[] { 1, 2, 3, 4 }),

    /** Atributo pre ocorrencia. */
    PRE_OCORRENCIA(110, "preAtendimento", "label.atendimento.view.title", new Integer[] { 1, 2, 3, 4 });

    /** Atributo codigo modulo. */
    private Integer codigoModulo;

    /** View Id cadastrada no faces-config.xml.*/
    private String viewID;

    /** Título a ser exibido no cabeçalho da tela.*/
    private String screeTitle;

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
     *
     * @param codigoModulo the codigo modulo
     * @param viewID the view id
     * @param screenTitle the screen title
     * @param autorizacoes the autorizacoes
     */
    private MenuItem(Integer codigoModulo, String viewID, String screenTitle, Integer[] autorizacoes) {
        this.codigoModulo = codigoModulo;
        this.viewID = viewID;
        this.screeTitle = screenTitle;
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

    /**
     * Nome: getScreeTitle
     * Recupera o valor do atributo 'screeTitle'.
     *
     * @return valor do atributo 'screeTitle'
     * @see
     */
    public String getScreeTitle() {
        return screeTitle;
    }

    /**
     * Nome: setScreeTitle
     * Registra o valor do atributo 'screeTitle'.
     *
     * @param screeTitle valor do atributo scree title
     * @see
     */
    public void setScreeTitle(String screeTitle) {
        this.screeTitle = screeTitle;
    }

}
