package br.com.sw2.gac.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.primefaces.model.DualListModel;

import br.com.sw2.gac.business.DispositivoBusiness;
import br.com.sw2.gac.tools.EstadoDispositivo;
import br.com.sw2.gac.vo.DispositivoVO;

/**
 * Classe responsável pela mudança de status do dispositivo.
 * @author ddiniz
 */
@ManagedBean
@ViewScoped
public class InventarioDispositivoBean extends BaseBean {

	private static final long serialVersionUID = -1113001600508136060L;

	private static final String INVENTARIO_DISPOSITIVO = "inventariodispositivo";

	/** Atributo id dispositivo. */
	private Integer idDispositivo;

	/** Atributo valor status atual. */
	private Integer valorStatusAtual;

	/** Atributo valor novo status. */
	private Integer valorNovoStatus;

	/** Atributo lista dispositivos. */
	private DualListModel<DispositivoVO> listaDispositivos;

	/** Atributo lista status atual. */
	private List<SelectItem> listaStatusAtual;

	/** Atributo lista novo status. */
	private List<EstadoDispositivo> listaNovoStatus;

	/**
	 * Construtor padrão.
	 */
	public InventarioDispositivoBean() {
		this.listaStatusAtual = getSelectIems(EstadoDispositivo.class);

		DispositivoBusiness business = new DispositivoBusiness();
		// Quando abre a tela, deve retornar os dispositivos que estão em estado Novo
		List<DispositivoVO> source = business.recuperaListaDispositivosPorEstado(EstadoDispositivo.Novo);
		List<DispositivoVO> target = new ArrayList<DispositivoVO>();

		this.listaDispositivos = new DualListModel<DispositivoVO>(source, target);
	}

	/**
	 * @return string
	 */
	public String iniciarPagina() {
		setTituloCabecalho("label.inventariodispositivo.view.title", true);
		return INVENTARIO_DISPOSITIVO;
	}

	 /**
     * @param actionEvent the action event
     */
    public void salvar(ActionEvent actionEvent) {
        setFacesMessage("message.inventariodispositivo.save.sucess");
    }

	public Integer getValorStatusAtual() {
		return valorStatusAtual;
	}

	public void setValorStatusAtual(Integer valorStatusAtual) {
		this.valorStatusAtual = valorStatusAtual;
	}

	public Integer getIdDispositivo() {
		return idDispositivo;
	}

	public void setIdDispositivo(Integer idDispositivo) {
		this.idDispositivo = idDispositivo;
	}

	public Integer getValorNovoStatus() {
		return valorNovoStatus;
	}

	public void setValorNovoStatus(Integer valorNovoStatus) {
		this.valorNovoStatus = valorNovoStatus;
	}

	public DualListModel<DispositivoVO> getListaDispositivos() {
		return listaDispositivos;
	}

	public void setListaDispositivos(
			DualListModel<DispositivoVO> listaDispositivos) {
		this.listaDispositivos = listaDispositivos;
	}

	public List<SelectItem> getListaStatusAtual() {
		return listaStatusAtual;
	}

	public void setListaStatusAtual(List<SelectItem> listaStatusAtual) {
		this.listaStatusAtual = listaStatusAtual;
	}

	public List<EstadoDispositivo> getListaNovoStatus() {
		return listaNovoStatus;
	}

	public void setListaNovoStatus(List<EstadoDispositivo> listaNovoStatus) {
		this.listaNovoStatus = listaNovoStatus;
	}

}
