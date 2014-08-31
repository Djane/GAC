package br.com.sw2.gac.tools;

/**
 * <b>Descrição: ENUM que determina os tipos de Mensagens de SMS possíveis.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public enum TipoMensagemSMS {

    /** Atributo Atendimento efetuado. */
    AtendimentoEfetuado(1, "Atendimento Efetuado"),

    /** Atributo Sinal emergencia. */
    SinalEmergencia(2, "Aviso de Emergência"),

    /** Atributo Aviso pagamento. */
    AvisoPagamento(3, "Aviso de Pagamento"),

    /** Atributo Aviso festivo. */
    AvisoFestivo(4, "Mensagem Festiva");

    /**
     * Construtor Padrao Instancia um novo objeto TipoMensagemSMS.
     * @param value the value
     * @param label the label
     */
    private TipoMensagemSMS(Integer value, String label) {
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
