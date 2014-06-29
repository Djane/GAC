package br.com.sw2.gac.tools;

/**
 * <b>Descrição: Enum com os tipos de atendimentos disponíveis.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public enum TipoOcorrencia {
    
    Emergencia(1, "Atendimento de Emergência"),    
    AtendimentoRealizado(2, "Atendimento Realizado"),    
    Comercial(3, "Área Comercial"),    
    Tecnica(4, "Área Técnica"),    
    Financeira(5, "Área Financeira"),    
    AtendimentoManual(6, "Atendimento Manual"),    
    Outros(7, "Outros"),    
    KeepAlive(8, "KeepAlive");
    

    private Integer value;
    private String label;


    /**
     * Construtor Padrao
     * Instancia um novo objeto TipoOcorrencia.
     *
     * @param value the value
     * @param label the label
     */
    private TipoOcorrencia(Integer value, String label) {
        this.value = value;
        this.label = label;
    }

    /**
     * Nome: getValue
     * Recupera o valor do atributo 'value'.
     *
     * @return valor do atributo 'value'
     * @see
     */
    public Integer getValue() {
        return value;
    }

    /**
     * Nome: getLabel
     * Recupera o valor do atributo 'label'.
     *
     * @return valor do atributo 'label'
     * @see
     */
    public String getLabel() {
        return label;
    }
}
