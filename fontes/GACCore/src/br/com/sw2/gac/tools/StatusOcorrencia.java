package src.br.com.sw2.gac.tools;

/**
 * ENum responsável por definir os Status da Ocorrência
 * 
 * 
 * @author marcelo
 *
 */
public enum StatusOcorrencia {
    /** Atributo Convulsao. */
    NAtendida(1, "Não Atendida"),

    /** Atributo Imobilidade. */
    EmAtendimento(2, "Em Atendimento"),

    /** Atributo Queda. */
    EmEspera(3, "Em Espera"),


     
    private StatusOcorrencia(Integer value, String label) {
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
