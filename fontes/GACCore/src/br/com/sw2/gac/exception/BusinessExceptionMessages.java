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
    SALVAR_DISPOSITIVO_DADOS_INVALIDOS(6, "Dados do dispositivo inválidos para gravação!"),

    /** Dispositivo em uso. */
    DELETE_DISPOSITIVO_EM_USO(7, "Não é possível excluir, o dispositivo está em uso!"),

    /** Dispositivo duplicado. */
    DISPOSITIVO_DUPLICADO(8, "Já existe um dispositivo com esta identificação!"),

    /** ID Tamanho diferente de 13. */
    ID_DISPOSITIVO_TAMANHO_INVALIDO(9, "A identificação do dispositivo deve possuir 13 caracteres."),

    /** Falha na carga de dispositivos. */
    FALHA_CARGA_DISPOSITIVOS(10, "Não foi possível efetuar a carga dos dispositivos!"),

    /** Script em uso. */
    DELETE_SCRIPT_EM_USO(11, "Não é possível excluir, o script de atendimento está em uso."),

    /** Mudança de estado não permitida pela máquina de estados. */
    MUDANCA_ESTADO_NOVO_INVALIDA(12, "Não é permitido mudar para o status selecionado a partir do status Novo."),

    /** Mudança de estado não permitida pela máquina de estados. */
    MUDANCA_ESTADO_MANUTENCAO_INVALIDA(13, "Não é permitido mudar para o status selecionado a partir do staus Manutenção."),

    /** Mudança de estado não permitida pela máquina de estados. */
    MUDANCA_ESTADO_PRONTO_INVALIDA(14, "Não é permitido mudar para o status selecionado a partir do status Pronto."),

    /** Mudança de estado não permitida pela máquina de estados. */
    MUDANCA_ESTADO_DEFEITO_INVALIDA(15, "Não é permitido mudar para o status selecionado a partir do status Defeito."),

    /** Mudança de estado não permitida pela máquina de estados. */
    MUDANCA_ESTADO_FABRICA_INVALIDA(16, "Não é permitido mudar para o status selecionado a partir do status Fábrica."),

    /** Mudança de estado não permitida pela máquina de estados. */
    MUDANCA_ESTADO_USO_INVALIDA(17, "Não é permitido mudar para o status selecionado a partir do status Uso."),

    /** Mudança de estado não permitida pela máquina de estados. */
    MUDANCA_ESTADO_DEVOLVIDO_INVALIDA(18, "Não é permitido mudar para o status selecionado a partir do status Devolvido."),

    /** Mudança de estado não permitida pela máquina de estados. */
    MUDANCA_ESTADO_DESCARTE_INVALIDA(19, "Não é permitido mudar para o status selecionado a partir do status Descarte."),

    /** Mudança de estado não permitida pela máquina de estados. */
    MUDANCA_MESMO_ESTADO_INVALIDA(20, "O status selecionado para mudança já é o status atual"),

    /** Mudança de estado não permitida pela máquina de estados. */
    MUDANCA_ESTADO_INVALIDA(21, "A partir do status atual não é possível mudar pra nenhum outro status"),

    /** Id com letras não é permitido. */
    ID_DISPOSITIVO_VALOR_INVALIDO(22, "O Identificador deve possuir somente valores numéricos"),

    /** Pesquisa relatório historico dispositivo deve ter ao menos um parametro. */
    PARAMETRO_OBRIGATORIO_RELATORIO_HISTDISPOSITIVO(23, "É preciso selecionar ao menos um parâmetro para efetuar a busca."),

    /** Atributo sms ja cadastrado. */
    SMS_JA_CADASTRADO(100, "Já existe uma mensagem SMS cadstrada com este título e descrição !"),

    DELETE_SMS_EM_USO(101, "Não é possível excluir a mensagem SMS. A mensagem ja foi utilizada  !"),

    SMS_VENCIDA(102, "Mensagem com a data de validade expirada"),

    PACOTE_SERVICO_JA_CADASTRADO(200, "Já existe um pacote de serviços cadstrado com este título e/ou descrição !"),
    PACOTE_SERVICO_VENCIDO(201, "Pacote de serviços com a data de validade expirada"),
    DELETE_PACOTE_SERVICO_EM_USO(5, "Não é possível excluir, existem contratos associados a este pacote de serviços."),

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
