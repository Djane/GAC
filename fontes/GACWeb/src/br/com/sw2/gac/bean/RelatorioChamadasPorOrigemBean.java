package br.com.sw2.gac.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import br.com.sw2.gac.business.OcorrenciaBusiness;
import br.com.sw2.gac.exception.BusinessException;
import br.com.sw2.gac.exception.BusinessExceptionMessages;
import br.com.sw2.gac.vo.RelChamadasPorOrigemVO;

/**
 * <b>Descrição: ontroller do modal de impressão do relatório de históricos de dispositivo.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@ManagedBean
@ViewScoped
public class RelatorioChamadasPorOrigemBean extends MenuBean {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = -8881315128433101534L;

    /** Atributo relatorio. */
    private RelChamadasPorOrigemVO relatorio;


    /**
     * Construtor.
     */
    public RelatorioChamadasPorOrigemBean() {
        iniciarListaChamadasPorOrigem();
    }

    /**
     * Nome: iniciarListaDispositivos Iniciar lista dispositivos.
     * @see
     */
    private void iniciarListaChamadasPorOrigem() {
        this.relatorio = new RelChamadasPorOrigemVO();
    }

    /**
     * Imprimir relatório histórico de dispositivos.
     * @param event the event
     * @see
     */
    public void imprimirChamadasPorOrigem(ActionEvent event) {
        this.getLogger().debug(
            "***** Iniciando método imprimirHistoricoDispositivos(ActionEvent event) *****");
        // Obtem os dados que serão exibidos no relatório
        OcorrenciaBusiness business = new OcorrenciaBusiness();
        List<RelChamadasPorOrigemVO> lista = null;
        RequestContext reqCtx = RequestContext.getCurrentInstance();
        try {
            lista = business.recuperaChamadasPorOrigem(this.relatorio);
            iniciarListaChamadasPorOrigem();
            HttpSession session = (HttpSession) this.getFacesContext().getExternalContext()
                .getSession(false);
            session.setAttribute("jasperFile", "chamadaorigem/chamadaOrigem.jasper");
            session.setAttribute("beanParameters", null);
            session.setAttribute("beanCollection", lista);
            reqCtx.addCallbackParam("validationError", false);
        } catch (BusinessException e) {
            setFacesErrorBusinessMessage(BusinessExceptionMessages.valueOf(e.getMessage()),
                "messagesChamadasOrigem");
            reqCtx.addCallbackParam("validationError", true);
            this.getLogger().debug(
                "Erro imprimirHistoricoDispositivos - Nenhum parâmetro preenchido!");
        }
        this.getLogger().debug(
            "***** Finalizado método imprimirHistoricoDispositivos(ActionEvent event) *****");
    }

    /**
     * Nome: getRelatorio Recupera o valor do atributo 'relatorio'.
     * @return valor do atributo 'relatorio'
     * @see
     */
    public RelChamadasPorOrigemVO getRelatorio() {
        return relatorio;
    }

    /**
     * Nome: setRelatorio Registra o valor do atributo 'relatorio'.
     * @param relatorio valor do atributo relatorio
     * @see
     */
    public void setRelatorio(RelChamadasPorOrigemVO relatorio) {
        this.relatorio = relatorio;
    }

}
