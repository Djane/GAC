package br.com.sw2.gac.vo;

import java.util.Date;


/**
 * Classe que representa o VO do relatório Histórico de Dispositivos.
 * @author ddiniz
 */
public class RelContratosAVencerVO {

	private Long nroContrato;

	private Date inicioVigencia;

	private Date fimVigencia;

	private String pacote;

	public Long getNroContrato() {
		return nroContrato;
	}

	public void setNroContrato(Long nroContrato) {
		this.nroContrato = nroContrato;
	}

	public Date getInicioVigencia() {
		return inicioVigencia;
	}

	public void setInicioVigencia(Date inicioVigencia) {
		this.inicioVigencia = inicioVigencia;
	}

	public Date getFimVigencia() {
		return fimVigencia;
	}

	public void setFimVigencia(Date fimVigencia) {
		this.fimVigencia = fimVigencia;
	}

	public String getPacote() {
		return pacote;
	}

	public void setPacote(String pacote) {
		this.pacote = pacote;
	}

}
