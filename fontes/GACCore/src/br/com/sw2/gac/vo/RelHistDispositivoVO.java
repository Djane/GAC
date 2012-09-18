package br.com.sw2.gac.vo;

import java.util.Date;

/**
 * Classe que representa o VO do relatório Histórico de Dispositivos.
 * @author ddiniz
 */
public class RelHistDispositivoVO {

	private Integer estadoOrigem;
	private Integer estadoDestino;
	private String idDispositivo;
	private Date dataMovimentacao;
	private String login;

	public Integer getEstadoOrigem() {
		return estadoOrigem;
	}

	public void setEstadoOrigem(Integer estadoOrigem) {
		this.estadoOrigem = estadoOrigem;
	}

	public Integer getEstadoDestino() {
		return estadoDestino;
	}

	public void setEstadoDestino(Integer estadoDestino) {
		this.estadoDestino = estadoDestino;
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

}
