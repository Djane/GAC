package br.com.sw2.gac.vo;

import java.util.Date;

/**
 * Classe que representa o VO do relatório Histórico de Dispositivos.
 * @author ddiniz
 */
public class RelHistDispositivoVO {

	private String estadoOrigem;
	private String estadoAtual;
	private Integer estadoAtualParam;
	private String idDispositivo;
	private Date dataMovimentacao;
	private String login;

	public String getEstadoOrigem() {
		return estadoOrigem;
	}

	public void setEstadoOrigem(String estadoOrigem) {
		this.estadoOrigem = estadoOrigem;
	}

	public String getIdDispositivo() {
		return idDispositivo;
	}

	public void setIdDispositivo(String idDispositivo) {
		this.idDispositivo = idDispositivo;
	}

	public Date getDataMovimentacao() {
		return dataMovimentacao;
	}

	public void setDataMovimentacao(Date dataMovimentacao) {
		this.dataMovimentacao = dataMovimentacao;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEstadoAtual() {
		return estadoAtual;
	}

	public void setEstadoAtual(String estadoAtual) {
		this.estadoAtual = estadoAtual;
	}

	public Integer getEstadoAtualParam() {
		return estadoAtualParam;
	}

	public void setEstadoAtualParam(Integer estadoAtualParam) {
		this.estadoAtualParam = estadoAtualParam;
	}

}
