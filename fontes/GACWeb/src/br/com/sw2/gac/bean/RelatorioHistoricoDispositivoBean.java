package br.com.sw2.gac.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import br.com.sw2.gac.business.DispositivoBusiness;
import br.com.sw2.gac.tools.EstadoDispositivo;
import br.com.sw2.gac.vo.RelHistDispositivoVO;

/**
 * Bean para tratar relatórios.
 * @author ddiniz
 *
 */
@ManagedBean
@ViewScoped
public class RelatorioHistoricoDispositivoBean extends MenuBean {

	private static final String HISTORICO_DISPOSITIVO = "historicoDispositivo.jasper";
	private static final long serialVersionUID = -8881315128433101534L;
	private RelHistDispositivoVO relatorio;
	private List<SelectItem> listaEstadoDispositivo;

	/**
	 * Construtor.
	 */
	public RelatorioHistoricoDispositivoBean() {
		this.relatorio = new RelHistDispositivoVO();
		this.listaEstadoDispositivo = getSelectItems(EstadoDispositivo.class);
	}

	/**
     * Imprimir relatório histórico de dispositivos.
     * @param event the event
     */
    public void imprimirHistoricoDispositivos(ActionEvent event) {
    	this.getLogger().debug("Iniciando imprimirHistoricoDispositivos");
        //Obtem os dados que serão exibidos no relatório
        DispositivoBusiness business = new DispositivoBusiness();
        List<RelHistDispositivoVO> lista = business.recuperaHistDispositivos(relatorio.getIdDispositivo(),
        		relatorio.getEstadoDestino(), relatorio.getDataMovimentacao(), relatorio.getLogin());
        super.imprimirRelatorioPadrao(HISTORICO_DISPOSITIVO, lista);
    }

	public List<SelectItem> getListaEstadoDispositivo() {
		return listaEstadoDispositivo;
	}

	public void setListaEstadoDispositivo(List<SelectItem> listaEstadoDispositivo) {
		this.listaEstadoDispositivo = listaEstadoDispositivo;
	}

	public RelHistDispositivoVO getRelatorio() {
		return relatorio;
	}

	public void setRelatorio(RelHistDispositivoVO relatorio) {
		this.relatorio = relatorio;
	}

}
