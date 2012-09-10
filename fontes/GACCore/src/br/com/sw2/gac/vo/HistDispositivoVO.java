package br.com.sw2.gac.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ddiniz
 */
public class HistDispositivoVO implements Serializable {

	private static final long serialVersionUID = 5220607583571667161L;

	private Integer estadoAnterior;
	private DispositivoVO dispositivo;
    private Date dthrMudaEstado;
    private String idDispositivo;


	public DispositivoVO getDispositivo() {
		return dispositivo;
	}
	public void setDispositivo(DispositivoVO dispositivo) {
		this.dispositivo = dispositivo;
	}
	public Integer getEstadoAnterior() {
		return estadoAnterior;
	}
	public void setEstadoAnterior(Integer estadoAnterior) {
		this.estadoAnterior = estadoAnterior;
	}

	public Date getDthrMudaEstado() {
		return dthrMudaEstado;
	}
	public void setDthrMudaEstado(Date dthrMudaEstado) {
		this.dthrMudaEstado = dthrMudaEstado;
	}
	public String getIdDispositivo() {
		return idDispositivo;
	}
	public void setIdDispositivo(String idDispositivo) {
		this.idDispositivo = idDispositivo;
	}
}
