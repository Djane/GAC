package br.com.sw2.gac.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import br.com.sw2.gac.business.DispositivoBusiness;
import br.com.sw2.gac.exception.BusinessException;
import br.com.sw2.gac.exception.BusinessExceptionMessages;
import br.com.sw2.gac.tools.EstadoDispositivo;
import br.com.sw2.gac.vo.RelHistDispositivoVO;

/**
 * <b>Descrição: ontroller do modal de impressão do relatório de históricos de dispositivo.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@ManagedBean
@ViewScoped
public class RelatorioHistoricoDispositivoBean extends MenuBean {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = -8881315128433101534L;

    /** Atributo relatorio. */
    private RelHistDispositivoVO relatorio;

    /** Atributo lista estado dispositivo. */
    private List<SelectItem> listaEstadoDispositivo;

    /**
     * Construtor.
     */
    public RelatorioHistoricoDispositivoBean() {
        iniciarListaDispositivos();
    }

    /**
     * Nome: iniciarListaDispositivos Iniciar lista dispositivos.
     * @see
     */
    private void iniciarListaDispositivos() {
        this.relatorio = new RelHistDispositivoVO();
        this.listaEstadoDispositivo = getSelectItems(EstadoDispositivo.class);
        // Dispositivos no estado Novo não possuem histórico
        this.listaEstadoDispositivo.remove(0);
    }

    /**
     * Imprimir relatório histórico de dispositivos.
     * @param event the event
     * @see
     */
    public void imprimirHistoricoDispositivos(ActionEvent event) {
        this.getLogger().debug(
            "***** Iniciando método imprimirHistoricoDispositivos(ActionEvent event) *****");
        // Obtem os dados que serão exibidos no relatório
        DispositivoBusiness business = new DispositivoBusiness();
        List<RelHistDispositivoVO> lista = null;
        RequestContext reqCtx = RequestContext.getCurrentInstance();
        try {
            lista = business.recuperaHistDispositivos(this.relatorio);
            iniciarListaDispositivos();
            HttpSession session = (HttpSession) this.getFacesContext().getExternalContext()
                .getSession(false);
            session.setAttribute("jasperFile", "historicodispositivo/historicoDispositivo.jasper");
            session.setAttribute("beanParameters", null);
            session.setAttribute("beanCollection", lista);
            reqCtx.addCallbackParam("validationError", false);
        } catch (BusinessException e) {
            setFacesErrorBusinessMessage(BusinessExceptionMessages.valueOf(e.getMessage()),
                "messagesHistoricoDispositivo");
            reqCtx.addCallbackParam("validationError", true);
            this.getLogger().debug(
                "Erro imprimirHistoricoDispositivos - Nenhum parâmetro preenchido!");
        }
        this.getLogger().debug(
            "***** Finalizado método imprimirHistoricoDispositivos(ActionEvent event) *****");
    }

    /**
     * Nome: getListaEstadoDispositivo Recupera o valor do atributo 'listaEstadoDispositivo'.
     * @return valor do atributo 'listaEstadoDispositivo'
     * @see
     */
    public List<SelectItem> getListaEstadoDispositivo() {
        return listaEstadoDispositivo;
    }

    /**
     * Nome: setListaEstadoDispositivo Registra o valor do atributo 'listaEstadoDispositivo'.
     * @param listaEstadoDispositivo valor do atributo lista estado dispositivo
     * @see
     */
    public void setListaEstadoDispositivo(List<SelectItem> listaEstadoDispositivo) {
        this.listaEstadoDispositivo = listaEstadoDispositivo;
    }

    /**
     * Nome: getRelatorio Recupera o valor do atributo 'relatorio'.
     * @return valor do atributo 'relatorio'
     * @see
     */
    public RelHistDispositivoVO getRelatorio() {
        return relatorio;
    }

    /**
     * Nome: setRelatorio Registra o valor do atributo 'relatorio'.
     * @param relatorio valor do atributo relatorio
     * @see
     */
    public void setRelatorio(RelHistDispositivoVO relatorio) {
        this.relatorio = relatorio;
    }

}
