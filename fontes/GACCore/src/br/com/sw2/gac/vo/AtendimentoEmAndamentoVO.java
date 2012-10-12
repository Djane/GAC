package br.com.sw2.gac.vo;

import java.util.Date;

/**
 * VO que representa os Atendimentos em Andamento.
 * @author ddiniz
 *
 */
public class AtendimentoEmAndamentoVO {

	private Integer idAtendimento;
	private Integer prioridade;
	private Integer contrato;
	private Date inicioAtendimento;
	private Date tempoDecorridoAtendimento;

	public Integer getIdAtendimento() {
		return idAtendimento;
	}
	public void setIdAtendimento(Integer idAtendimento) {
		this.idAtendimento = idAtendimento;
	}
	public Integer getPrioridade() {
		return prioridade;
	}
	public void setPrioridade(Integer prioridade) {
		this.prioridade = prioridade;
	}
	public Integer getContrato() {
		return contrato;
	}
	public void setContrato(Integer contrato) {
		this.contrato = contrato;
	}
	public Date getInicioAtendimento() {
		return inicioAtendimento;
	}
	public void setInicioAtendimento(Date inicioAtendimento) {
		this.inicioAtendimento = inicioAtendimento;
	}
	public Date getTempoDecorridoAtendimento() {
		return tempoDecorridoAtendimento;
	}
	public void setTempoDecorridoAtendimento(Date tempoDecorridoAtendimento) {
		this.tempoDecorridoAtendimento = tempoDecorridoAtendimento;
	}

}
