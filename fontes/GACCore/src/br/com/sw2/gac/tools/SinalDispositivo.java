package br.com.sw2.gac.tools;

/**
 * <b>Descricao: ENUM responsável para identificar os sinais provenientes do Dispositivo.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public enum SinalDispositivo {


    BotaoEmergencia(1, "Botão de Emergência"),

    Queda(2, "Detecção de Queda Livre"),

    Imobilidade(3, "Imobilidade/Desmaio"),

    Batida(4, "Choque ou Batida"),

    ForaAlcance(5, "Fora da área de Alcance"),

    BateriaFraca(6, "Bateria Fraca"),
    
    DispositivoNaoProgramado(90, "Emergencia sem dispositivo programado"),
    
    BotaoEmergenciaComDispositivo(91, "Botão de emergencia Acionado"),
    
    EventoPeriodico(92, "Evento Periódico"),
    
    FaltaDeAlimentacaoEnergia(93, "Falta de alimentação da central. Operando em bateria."),
    
    VoltaDeAlimentacaoEnergia(94, "Volta de alimentação da central"),
    
    BateriaFracaCentral(95, "Bateria fraca."),
    
    CaboTelefoneConectado(96, "Cabo telefone Conectado"),
    
    CaboTelefoneDesconectado(97, "Cabo de telefone desconectado.");
    
    

    /**
     * Construtor Padrao Instancia um novo objeto SinalDispositivo.
     * @param value the value
     * @param label the label
     */
    private SinalDispositivo(Integer value, String label) {
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
