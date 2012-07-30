package br.com.sw2.gac.tools;

/**
 * <b>Descri��o: Enum contendo codigos e mensagens de erro para BusinessException.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public enum BusinessExceptionMessages {

    /** Atributo FALH a_ autentic a� ao. */
    FALHA_AUTENTICA�AO(1, "Usuario ou senha inv�lidos"),

    /** Atributo senha nao informada. */
    SENHA_NAO_INFORMADA(2, "Senha n�o informada"),

    /** Atributo login duplicado. */
    USUARIO_DUPLICADO(3, "Usuario ja existe"),

    /** Atributo login duplicado. */
    SALVAR_USUARIO_DADOS_INVALIDOS(4, "Dados do usu�rio inv�lidos para grava��o");

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
