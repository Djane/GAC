package br.com.sw2.gac.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ddiniz
 *
 */
public class DispositivoVO extends BaseVO implements Serializable {

	private static final long serialVersionUID = -6630857372872795756L;

	private String idDispositivo;
	private Integer tipoDispositivo;
    private Date dataFabricacao;
    private Integer estadoAtual;
    private UsuarioVO usuario;
    private Date dataEntrada;
    private Date dataProximaManutencao;
    private Date dataSucata;
    private Integer local;

	public void setIdDispositivo(String id) {
		this.idDispositivo = id;
	}

	public String getIdDispositivo() {
		return idDispositivo;
	}

	public Integer getTipoDispositivo() {
		return tipoDispositivo;
	}

	public void setTipoDispositivo(Integer tipoDispositivo) {
		this.tipoDispositivo = tipoDispositivo;
	}

	public Date getDataFabricacao() {
		return dataFabricacao;
	}

	public void setDataFabricacao(Date dataFabricacao) {
		this.dataFabricacao = dataFabricacao;
	}

	public Integer getEstadoAtual() {
		return estadoAtual;
	}

	public void setEstadoAtual(Integer novo) {
		this.estadoAtual = novo;
	}

	public UsuarioVO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioVO usuario) {
		this.usuario = usuario;
	}

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Date getDataProximaManutencao() {
        return dataProximaManutencao;
    }

    public void setDataProximaManutencao(Date dataProximaManutencao) {
        this.dataProximaManutencao = dataProximaManutencao;
    }

    public Date getDataSucata() {
        return dataSucata;
    }

    public void setDataSucata(Date dataSucata) {
        this.dataSucata = dataSucata;
    }

	public Integer getLocal() {
		return local;
	}

	public void setLocal(Integer local) {
		this.local = local;
	}

}
