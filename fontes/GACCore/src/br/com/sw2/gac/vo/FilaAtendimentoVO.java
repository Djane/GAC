package br.com.sw2.gac.vo;

import java.util.Date;

/**
 * VO que representa a fila de atendimento.
 * @author ddiniz
 *
 */
public class FilaAtendimentoVO {

	private Integer idAtendimento;
	private String prioridade;
	private Integer contrato;
	private Date inicioFila;
	private Date tempoDecorridoFila;
	private String contatoCliente;
	private String situacao;

	public Integer getIdAtendimento() {
		return idAtendimento;
	}
	public void setIdAtendimento(Integer idAtendimento) {
		this.idAtendimento = idAtendimento;
	}
	public String getPrioridade() {
		return prioridade;
	}
	public void setPrioridade(String prioridade) {
		this.prioridade = prioridade;
	}
	public Integer getContrato() {
		return contrato;
	}
	public void setContrato(Integer contrato) {
		this.contrato = contrato;
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
	public String getContatoCliente() {
		return contatoCliente;
	}
	public void setContatoCliente(String contatoCliente) {
		this.contatoCliente = contatoCliente;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

}
