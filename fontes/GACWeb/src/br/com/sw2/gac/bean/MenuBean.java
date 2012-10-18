package br.com.sw2.gac.bean;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import br.com.sw2.gac.business.ContratoBusiness;
import br.com.sw2.gac.business.DispositivoBusiness;
import br.com.sw2.gac.util.ClassLoaderUtils;
import br.com.sw2.gac.util.DateUtil;
import br.com.sw2.gac.util.MenuItem;
import br.com.sw2.gac.vo.DesempenhoComercialVO;
import br.com.sw2.gac.vo.DispositivoEstadoVO;
import br.com.sw2.gac.vo.RelContratosAVencerVO;
import br.com.sw2.gac.vo.UsuarioVO;

/**
 * <b>Descrição: controller do menu principal.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@ManagedBean
@SessionScoped
public class MenuBean extends BaseBean {

    private static final long serialVersionUID = -1506925064205437167L;

    /** Atributo filtro mes referencia. */
    private Integer filtroMesReferencia;

    /** Atributo filtro ano referencia. */
    private Integer filtroAnoReferencia;

    /** Atributo handler. */
    private NavigationHandler handler;

    private static final int TRINTA_DIAS = 30;

    /**
     * Construtor Padrao Instancia um novo objeto MenuBean.
     */
    public MenuBean() {
        this.filtroMesReferencia = 1;
        this.filtroAnoReferencia = DateUtil.getAnoAtual();
    }

    /**
     * Nome: invokarPagina Método que invoca uma página baseada em seu codigo.
     * @return string
     * @see
     */
    public String invokarPagina() {
        String toViewId = "";
        Integer codigo = Integer.parseInt(getRequestParameter("codigoModulo"));

        for (MenuItem item : MenuItem.values()) {
            if (codigo.intValue() == item.getCodigoModulo().intValue()) {
                if (!item.getScreeTitle().equals("")) {
                    setTituloCabecalho(item.getScreeTitle(), true);
                }
                toViewId = item.getViewID();
            }
        }
        return toViewId;
    }

    /**
     * Nome: verificarPermissaoPerfil Verificar permissao perfil.
     * @param codigoModulo the codigo modulo
     * @return true, se sucesso, senão false
     * @see
     */
    public boolean verificarPermissaoPerfil(Integer codigoModulo) {
        UsuarioVO usuario = getUsuarioLogado();
        boolean temPermissao = false;
        for (MenuItem item : MenuItem.values()) {
            if (item.getCodigoModulo().intValue() == codigoModulo.intValue()) {
                for (Integer codigoPeril : item.getPerfilAutorizado()) {

                    if (codigoPeril.intValue() == usuario.getPerfil().getIdPerfil().intValue()) {
                        temPermissao = true;
                        break;
                    }
                }
            }
        }
        return temPermissao;
    }

    /**
     * Nome: imprimirDispositivosPorEstado Imprimir dispositivos por estado.
     * @param event the event
     * @see
     */
    public void imprimirDispositivosPorEstado(ActionEvent event) {
        this.getLogger().debug("Iniciando imprimirDispositivosPorEstado");
        // Obtem os dados que serão exibidos no relatório
        DispositivoBusiness business = new DispositivoBusiness();
        List<DispositivoEstadoVO> lista = business.recuperaQtdeDispositivosPorEstado();
        this.imprimirRelatorioPadrao("dispositivoEstado.jasper", lista, null);
        this.getLogger().debug("Finalizado imprimirDispositivosPorEstado");
    }

    /**
     * Nome: imprimirRelatorioDesempenhoComercial Imprimir relatorio desempenho comercial.
     * @param event the event
     * @see
     */
    public void imprimirRelatorioDesempenhoComercial(ActionEvent event) {
        this.getLogger().debug("Iniciando imprimirRelatorioDesempenhoComercial **************");
        this.getLogger().debug("Mês selecionado :" + this.filtroMesReferencia);
        this.getLogger().debug("Ano selecionado :" + this.filtroAnoReferencia);
        this.getLogger().debug("Finalizado imprimirRelatorioDesempenhoComercial **************");

        ContratoBusiness contratoBusiness = new ContratoBusiness();
        DesempenhoComercialVO desempenhoComercial = contratoBusiness
                .obterDadosDesempenhoComercial(DateUtil.getDate(this.filtroAnoReferencia,
                        this.filtroMesReferencia, 01));
        this.getLogger().debug("Serviço executado...");
        Collection beanCollection = new ArrayList();
        beanCollection.add(desempenhoComercial);
        this.getLogger().debug("Chamando método de impressão...");
        this.imprimirRelatorioPadrao("desempenhocomercial.jasper", beanCollection, null);
    }

    /**
     * Nome: imprimirContratosAVencer Imprimir contratos a vencer em 30 dias.
     * @param event the event
     * @see
     */
    public void imprimirContratosAVencer(ActionEvent event) {
        this.getLogger().debug("Iniciando imprimirDispositivosPorEstado");
        // Obtem os dados que serão exibidos no relatório
        ContratoBusiness contratoBusiness = new ContratoBusiness();
        List<RelContratosAVencerVO> lista = contratoBusiness.recuperarContratosAtivosAVencerEm(TRINTA_DIAS);

        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("TOTAL_REGISTROS", lista.size());
        this.imprimirRelatorioPadrao("contratosAVencer.jasper", lista, parameters);
        this.getLogger().debug("Finalizado imprimirContratosAVencer");
    }

    /**
     * Nome: imprimirRelatorioPadrao Imprimir relatorio jasper.
     * @param jasperFile the jasper file
     * @param beanCollection the bean collection data source
     * @param beanParameters the bean parameters
     * @see
     */
    public void imprimirRelatorioPadrao(String jasperFile, Collection<?> beanCollection, Map<String, Object> beanParameters) {
        this.getLogger().debug("Iniciando imprimirRelatorioPadrao");
        FacesContext context = FacesContext.getCurrentInstance();
        this.handler = context.getApplication().getNavigationHandler();
        if (null == beanCollection || beanCollection.isEmpty()) {
            this.handler.handleNavigation(context, null, "reportBlank");
        } else {
            // Seta o dataSource
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(
                    beanCollection);
            // Abre o arquivo .jasper contendo o relatorio
            InputStream inputStream = ClassLoaderUtils.getJasperFileAsStream(jasperFile);
            try {
                Map<String, Object> parameters = new HashMap<String, Object>();
                parameters.put("LOGO_SMARTANGEL", getUrlBase()
                        + "/primefaces-smartangel/images/logo/smartangel-147x87.jpg");
				if (beanParameters != null) {
					parameters.putAll(beanParameters);
				}
                JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parameters,
                        beanCollectionDataSource);
                HttpServletResponse response = getHttpServletResponse();
                response.reset();
                response.setContentType("application/pdf");

                response.addHeader("Content-disposition", "inline; filename=relatorio.pdf");

                ServletOutputStream servletOutputStream = (ServletOutputStream) getHttpServletResponse()
                        .getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
                context.getApplication().getStateManager().saveView(context);
                // Fecha o stream do response

                response.getOutputStream().flush();
                response.getOutputStream().close();
                context.responseComplete();
                this.getLogger().debug("Finalizado imprimirRelatorioPadrao");
            } catch (Exception e) {
                this.getLogger().error(
                        "Problemas na geração da visualização do relatório " + jasperFile);
                this.getLogger().error(e);
                this.handler.handleNavigation(context, null, "jasperError");
            }
        }
    }

    /**
     * Nome: getFiltroMesReferencia Recupera o valor do atributo 'filtroMesReferencia'.
     * @return valor do atributo 'filtroMesReferencia'
     * @see
     */

    public Integer getFiltroMesReferencia() {
        return filtroMesReferencia;
    }

    /**
     * Nome: setFiltroMesReferencia Registra o valor do atributo 'filtroMesReferencia'.
     * @param filtroMesReferencia valor do atributo filtro mes referencia
     * @see
     */

    public void setFiltroMesReferencia(Integer filtroMesReferencia) {
        this.filtroMesReferencia = filtroMesReferencia;
    }

    /**
     * Nome: getFiltroAnoReferencia Recupera o valor do atributo 'filtroAnoReferencia'.
     * @return valor do atributo 'filtroAnoReferencia'
     * @see
     */

    public Integer getFiltroAnoReferencia() {
        return filtroAnoReferencia;
    }

    /**
     * Nome: setFiltroAnoReferencia Registra o valor do atributo 'filtroAnoReferencia'.
     * @param filtroAnoReferencia valor do atributo filtro ano referencia
     * @see
     */

    public void setFiltroAnoReferencia(Integer filtroAnoReferencia) {
        this.filtroAnoReferencia = filtroAnoReferencia;
    }

}
