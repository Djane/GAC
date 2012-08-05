package br.com.sw2.gac.vo;

import java.io.Serializable;
import java.util.Date;

import br.com.sw2.gac.modelo.Usuario;
import br.com.sw2.gac.tools.EstadoDispositivo;
import br.com.sw2.gac.tools.TipoDispositivo;

/**
 * @author ddiniz
 *
 */
public class DispositivoVO implements Serializable {

	private static final long serialVersionUID = -6630857372872795756L;

	private String id;
	private TipoDispositivo tipoDispositivo;
    private Date dataFabricacao;
    private EstadoDispositivo estadoAtual;
    private Usuario login;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public TipoDispositivo getTipoDispositivo() {
		return tipoDispositivo;
	}

	public void setTipoDispositivo(TipoDispositivo tipoDispositivo) {
		this.tipoDispositivo = tipoDispositivo;
	}

	public Date getDataFabricacao() {
		return dataFabricacao;
	}

	public void setDataFabricacao(Date dataFabricacao) {
		this.dataFabricacao = dataFabricacao;
	}

	public EstadoDispositivo getEstadoAtual() {
		return estadoAtual;
	}

	public void setEstadoAtual(EstadoDispositivo novo) {
		this.estadoAtual = novo;
	}

	public Usuario getLogin() {
		return login;
	}

	public void setLogin(Usuario login) {
		this.login = login;
	}
}
