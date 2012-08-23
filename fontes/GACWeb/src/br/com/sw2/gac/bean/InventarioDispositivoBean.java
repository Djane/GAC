package br.com.sw2.gac.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

import org.primefaces.component.selectonemenu.SelectOneMenu;
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
	private List<SelectItem> listaNovoStatus;

	private DispositivoBusiness business = new DispositivoBusiness();

	/**
	 * Construtor padrão.
	 */
	public InventarioDispositivoBean() {
		this.listaStatusAtual = getSelectIems(EstadoDispositivo.class);
		// Quando abre a tela, deve retornar os dispositivos que estão em estado Novo
		atualizarListaDispositivos(EstadoDispositivo.Novo.getValue());
	}

	private void atualizarListaDispositivos(Integer status) {
		List<DispositivoVO> source = business.recuperaListaPulseiraECentralPorEstado(status);
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

    /**
     * Atualiza a lista de dispositivos e a lista de Novo status, de acordo com o Status Atual.
     * @param event Evento
     */
    public void statusAtualChangeListener(AjaxBehaviorEvent event) {
    	SelectOneMenu selectMenu = ((SelectOneMenu) event.getSource());
    	// Recupera o value correspondente ao EstadoDispositivo selecionado
    	Integer statusValue = (Integer) selectMenu.getValue();
    	// Atualiza a lista de dispositivos que estão no estado selecionado
    	atualizarListaDispositivos(statusValue);

    	// TODO novo status de acordo com status atual
    	this.listaNovoStatus = getSelectIems(EstadoDispositivo.class);
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

	public List<SelectItem> getListaNovoStatus() {
		return listaNovoStatus;
	}

	public void setListaNovoStatus(List<SelectItem> listaNovoStatus) {
		this.listaNovoStatus = listaNovoStatus;
	}

}
