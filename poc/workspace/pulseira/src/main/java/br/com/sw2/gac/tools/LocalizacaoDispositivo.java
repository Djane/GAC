package br.com.sw2.gac.tools;

/**
 * <b>Descri��o: Enum que determina a localização dos Dispositivos.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public enum LocalizacaoDispositivo {

    /** Atributo Estoque interno. */
    EstoqueInterno(1, "Estoque das Dependências da SmartAngel"),

    /** Atributo Estoque externo. */
    EstoqueExterno(2, "Pertence a SmartAngel, mas está fora das dependências"),

    /** Atributo Transito. */
    Transito(3, "Em Trânsito"),

    /** Atributo Manutencao. */
    Manutencao(4, "Em Manutenção na Fábrica"),

    /** Atributo Manutencao terceiros. */
    ManutencaoTerceiros(5, "Em Manutenção em uma Assitência"),

    /** Atributo Em uso. */
    EmUso(6, "Em uso com o paciente"),

    /** Atributo Extravio. */
    Extravio(7, "Extraviada"),

    /** Atributo Descarte. */
    Descarte(8, "Descartada");

    /**
     * Construtor Padrao Instancia um novo objeto LocalizacaoDispositivo.
     * @param value the value
     * @param label the label
     */
    private LocalizacaoDispositivo(Integer value, String label) {
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
