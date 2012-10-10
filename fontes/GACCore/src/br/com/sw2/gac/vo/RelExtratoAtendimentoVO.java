package br.com.sw2.gac.vo;

import java.util.Date;

/**
 * VO que representa o relatório de Fila de Atendimentos.
 * @author ddiniz
 *
 */
public class RelExtratoAtendimentoVO {

	private Integer idAtendimento;
	private Integer prioridade;
	private Integer contrato;
	private Date inicioAtendimento;
	private Date tempoDecorridoAtendimento;
	private Date inicioFila;
	private Date tempoDecorridoFila;
	private Integer contatoCliente;
	private Integer situacao;
	private Integer status;

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
	public Date getInicioFila() {
		return inicioFila;
	}
	public void setInicioFila(Date inicioFila) {
		this.inicioFila = inicioFila;
	}
	public Date getTempoDecorridoFila() {
		return tempoDecorridoFila;
	}
	public void setTempoDecorridoFila(Date tempoDecorridoFila) {
		this.tempoDecorridoFila = tempoDecorridoFila;
	}
	public Integer getContatoCliente() {
		return contatoCliente;
	}
	public void setContatoCliente(Integer contatoCliente) {
		this.contatoCliente = contatoCliente;
	}
	public Integer getSituacao() {
		return situacao;
	}
	public void setSituacao(Integer situacao) {
		this.situacao = situacao;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

}
