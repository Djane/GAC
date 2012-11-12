package br.com.sw2.gac.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;

import br.com.sw2.gac.business.OcorrenciaBusiness;
import br.com.sw2.gac.exception.BusinessException;
import br.com.sw2.gac.exception.BusinessExceptionMessages;
import br.com.sw2.gac.util.DateUtil;
import br.com.sw2.gac.util.JasperHelper;
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
        List<RelChamadasPorOrigemVO> lista = business.recuperaChamadasPorOrigem(relatorio);
        RequestContext reqCtx = RequestContext.getCurrentInstance();
        if (validarDadosForm(reqCtx)) {
            try {
                lista = business.recuperaChamadasPorOrigem(this.relatorio);
                iniciarListaChamadasPorOrigem();
                JasperHelper.saveSessionAtributes(getHttpServLetRequest(),
                    "chamadaorigem/chamadaOrigem.jasper", null, lista);
                reqCtx.addCallbackParam("validationError", false);
            } catch (BusinessException e) {
                setFacesErrorBusinessMessage(BusinessExceptionMessages.valueOf(e.getMessage()),
                    "messagesChamadasOrigem");
                reqCtx.addCallbackParam("validationError", true);
                this.getLogger().error(e.getMessage());
            }
        }
        this.getLogger().debug(
            "***** Finalizado método imprimirHistoricoDispositivos(ActionEvent event) *****");
    }

    private boolean validarDadosForm(RequestContext reqCtx) {
        this.getLogger().debug("***** Iniciando método validarDadosForm()*****");
        boolean valido = true;
        if (null == this.relatorio.getPerInicio()) {
            setFacesErrorMessage("message.relatorio.chamadapororigem.field.periodoinicial.required");
            valido =  false;
            reqCtx.addCallbackParam("validationError", true);
        }

        if (null == this.relatorio.getPerFim()) {
            setFacesErrorMessage("message.relatorio.chamadapororigem.field.periodofinal.required");
            valido =  false;
            reqCtx.addCallbackParam("validationError", true);
        }

        if (DateUtil.compareIgnoreTime(this.relatorio.getPerInicio(), this.relatorio.getPerFim()) > 0) {
            setFacesErrorMessage("message.relatorio.chamadapororigem.datainiciomaior");
            valido =  false;
            reqCtx.addCallbackParam("validationError", true);
        }

        this.getLogger().debug("***** Finalizado método validarDadosForm() com retorno: " + valido + "*****");

        return valido;
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
