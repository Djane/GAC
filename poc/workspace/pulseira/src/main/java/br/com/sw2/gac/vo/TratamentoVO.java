package br.com.sw2.gac.vo;

/**
 * <b>Descrição:</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class TratamentoVO {

    /** Atributo id tratamento. */
    private Integer idTratamento;

    /** Atributo cpf paciente. */
    private String cpfPaciente;

    /** Atributo nome tratamento. */
    private String nomeTratamento;

    /** Atributo descricao tratamento. */
    private String descricaoTratamento;

    /** Atributo frequencia minutos. */
    private Integer frequenciaMinutos;

    /**
     * Nome: getIdTratamento Recupera o valor do atributo 'idTratamento'.
     * @return valor do atributo 'idTratamento'
     * @see
     */
    public Integer getIdTratamento() {
        return idTratamento;
    }

    /**
     * Nome: setIdTratamento Registra o valor do atributo 'idTratamento'.
     * @param idTratamento valor do atributo id tratamento
     * @see
     */
    public void setIdTratamento(Integer idTratamento) {
        this.idTratamento = idTratamento;
    }

    /**
     * Nome: getCpfPaciente Recupera o valor do atributo 'cpfPaciente'.
     * @return valor do atributo 'cpfPaciente'
     * @see
     */
    public String getCpfPaciente() {
        return cpfPaciente;
    }

    /**
     * Nome: setCpfPaciente Registra o valor do atributo 'cpfPaciente'.
     * @param cpfPaciente valor do atributo cpf paciente
     * @see
     */
    public void setCpfPaciente(String cpfPaciente) {
        this.cpfPaciente = cpfPaciente;
    }

    /**
     * Nome: getNomeTratamento Recupera o valor do atributo 'nomeTratamento'.
     * @return valor do atributo 'nomeTratamento'
     * @see
     */
    public String getNomeTratamento() {
        return nomeTratamento;
    }

    /**
     * Nome: setNomeTratamento Registra o valor do atributo 'nomeTratamento'.
     * @param nomeTratamento valor do atributo nome tratamento
     * @see
     */
    public void setNomeTratamento(String nomeTratamento) {
        this.nomeTratamento = nomeTratamento;
    }

    /**
     * Nome: getDescricaoTratamento Recupera o valor do atributo 'descricaoTratamento'.
     * @return valor do atributo 'descricaoTratamento'
     * @see
     */
    public String getDescricaoTratamento() {
        return descricaoTratamento;
    }

    /**
     * Nome: setDescricaoTratamento Registra o valor do atributo 'descricaoTratamento'.
     * @param descricaoTratamento valor do atributo descricao tratamento
     * @see
     */
    public void setDescricaoTratamento(String descricaoTratamento) {
        this.descricaoTratamento = descricaoTratamento;
    }

    /**
     * Nome: getFrequenciaMinutos Recupera o valor do atributo 'frequenciaMinutos'.
     * @return valor do atributo 'frequenciaMinutos'
     * @see
     */
    public Integer getFrequenciaMinutos() {
        return frequenciaMinutos;
    }

    /**
     * Nome: setFrequenciaMinutos Registra o valor do atributo 'frequenciaMinutos'.
     * @param frequenciaMinutos valor do atributo frequencia minutos
     * @see
     */
    public void setFrequenciaMinutos(Integer frequenciaMinutos) {
        this.frequenciaMinutos = frequenciaMinutos;
    }
}
