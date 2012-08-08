package br.com.sw2.gac.vo;

import java.io.Serializable;
import java.util.Date;

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
    private UsuarioVO usuario;
    private String descricaoDispositivo;
    private Date dataEntrada;
    private Date dataProximaManutencao;
    private Date dataSucata;
    private String local;

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

	public UsuarioVO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioVO usuario) {
		this.usuario = usuario;
	}

    public String getDescricaoDispositivo() {
        return descricaoDispositivo;
    }

    public void setDescricaoDispositivo(String descricaoDispositivo) {
        this.descricaoDispositivo = descricaoDispositivo;
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

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }
}
