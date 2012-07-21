package br.com.smartangel.pulseira.vo;

public class TratamentoVO {
    
    private Integer idTratamento;
    private String cpfPaciente;
    private String nomeTratamento;
    private String descricaoTratamento;
    private Integer frequenciaMinutos;
   
    public Integer getIdTratamento() {
        return idTratamento;
    }
    public void setIdTratamento(Integer idTratamento) {
        this.idTratamento = idTratamento;
    }
    public String getCpfPaciente() {
        return cpfPaciente;
    }
    public void setCpfPaciente(String cpfPaciente) {
        this.cpfPaciente = cpfPaciente;
    }
    public String getNomeTratamento() {
        return nomeTratamento;
    }
    public void setNomeTratamento(String nomeTratamento) {
        this.nomeTratamento = nomeTratamento;
    }
    public String getDescricaoTratamento() {
        return descricaoTratamento;
    }
    public void setDescricaoTratamento(String descricaoTratamento) {
        this.descricaoTratamento = descricaoTratamento;
    }
    public Integer getFrequenciaMinutos() {
        return frequenciaMinutos;
    }
    public void setFrequenciaMinutos(Integer frequenciaMinutos) {
        this.frequenciaMinutos = frequenciaMinutos;
    }
}
