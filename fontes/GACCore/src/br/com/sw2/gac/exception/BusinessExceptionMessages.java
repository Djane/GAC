package br.com.sw2.gac.exception;

/**
 * <b>Descrição: Enum contendo códigos e mensagens de erro para BusinessException.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public enum BusinessExceptionMessages {

    /** Atributo falha autenticação. */
    FALHA_AUTENTICACAO(1, "Usuario ou senha inválidos"),

    /** Atributo senha nao informada. */
    SENHA_NAO_INFORMADA(2, "Senha não informada"),

    /** Atributo login duplicado. */
    USUARIO_DUPLICADO(3, "Usuario já existe"),

    /** Dados do usuários s�o inv�lidos. */
    SALVAR_USUARIO_DADOS_INVALIDOS(4, "Dados do usuário inválidos para gravação"),

    /** Usu�rio em uso. */
    DELETE_USUARIO_EM_USO(5, "Não é possível excluir, o usuário está em uso."),

    /** Dispositivo inv�lido. */
    SALVAR_DISPOSITIVO_DADOS_INVALIDOS(6, "Dados do dispositivo inválidos para gravação"),

    /** Dispositivo em uso. */
    DELETE_DISPOSITIVO_EM_USO(7, "Não é possível excluir, o dispositivo está em uso"),

    /** Dispositivo duplicado. */
    DISPOSITIVO_DUPLICADO(8, "Não são permitidos dispositivos com identificadores iguais, mesmo sendo de tipos diferentes."),

    /** Falha na carga de dispositivos. */
    FALHA_CARGA_DISPOSITIVOS(9, "Não é possível excluir a mensagem SMS. A mensagem ja foi utilizada  !"),

    /** Atributo sms ja cadastrado. */
    SMS_JA_CADASTRADO(100, "Já existe uma mensagem SMS cadstrada com este título e descrição !"),

    DELETE_SMS_EM_USO(101, "Não é possível excluir a mensagem SMS. A mensagem ja foi utilizada  !"),

    /** Atributo sistema indisponivel. */
    SISTEMA_INDISPONIVEL(1000, "Sistema indiponível");

    /** Atributo value. */
    private Integer value;

    /** Atributo label. */
    private String label;

    /**
     * Construtor Padrao Instancia um novo objeto BusinessMessages.
     * @param value the value
     * @param label the label
     */
    private BusinessExceptionMessages(int value, String label) {
        this.value = value;
        this.label = label;
    }

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
