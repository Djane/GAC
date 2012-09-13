package br.com.sw2.gac.view;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import br.com.sw2.gac.jasper.DataSourceDesempenhoComercial;
import br.com.sw2.gac.jasper.JasperBeanDataSource;
import br.com.sw2.gac.tools.EstadoDispositivo;
import br.com.sw2.gac.util.ClassLoaderUtils;

/**
 * <b>Descrição : Contreller do menu e relatórios.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@ManagedBean
@ViewScoped
public class PrincipalBean extends BaseBean {

    /** Atributo data atual. */
    private Date dataAtual = new Date();

    /**
     * Construtor Padrao Instancia um novo objeto PrincipalBean.
     */
    public PrincipalBean() {
        this.listaEstadoDispositivo = getSelectIems(EstadoDispositivo.class);
    }

    /**
     * Nome: iniciarPagina Iniciar pagina.
     * @return string
     * @see
     */
    public String iniciarPagina() {
        this.listaEstadoDispositivo = getSelectIems(EstadoDispositivo.class);
        return "menuPrincipal";
    }

    /** Atributo lista estado dispositivo. */
    private List<SelectItem> listaEstadoDispositivo;

    /**
     * Nome: getDataAtual Recupera o valor do atributo 'dataAtual'.
     * @return valor do atributo 'dataAtual'
     * @see
     */
    public Date getDataAtual() {
        return dataAtual;
    }

    /**
     * Nome: setDataAtual Registra o valor do atributo 'dataAtual'.
     * @param dataAtual valor do atributo data atual
     * @see
     */
    public void setDataAtual(Date dataAtual) {
        this.dataAtual = dataAtual;

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
     * Nome: imprimirDispositivosPorEstado Imprimir dispositivos por estado.
     * @param event the event
     * @see
     */
    public void imprimirDispositivosPorEstado(ActionEvent event) {

        FacesContext context = FacesContext.getCurrentInstance();

        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(
                JasperBeanDataSource.createBeanCollection());

        //Abre o arquivo .jasper contendo o relatorio
        InputStream inputStream = ClassLoaderUtils.getJasperFileAsStream("dispositivoEstado.jasper");

        try {
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("LOGO_SMARTANGEL", getUrlBase()
                    + "/primefaces-smartangel/images/smartangel-150-90.jpg");
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

        } catch (JRException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Nome: imprimirRelatorioDesempenhoComercial
     * Imprimir relatorio desempenho comercial.
     *
     * @param event the event
     * @see
     */
    public void imprimirRelatorioDesempenhoComercial(ActionEvent event) {

        FacesContext context = FacesContext.getCurrentInstance();

        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(
                DataSourceDesempenhoComercial.createBeanCollection());

        //Abre o arquivo .jasper contendo o relatorio
        InputStream inputStream = ClassLoaderUtils.getJasperFileAsStream("desempenhocomercial.jasper");

        try {
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("LOGO_SMARTANGEL", getUrlBase()
                    + "/primefaces-smartangel/images/smartangel-150-90.jpg");
            JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parameters,
                    beanCollectionDataSource);
            HttpServletResponse response = getHttpServletResponse();
            response.reset();
            response.setContentType("application/pdf");
            response.addHeader("Content-disposition", "inline; filename=relatorioDesempenhoComercial.pdf");
            ServletOutputStream servletOutputStream = (ServletOutputStream) getHttpServletResponse()
                    .getOutputStream();

            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            context.getApplication().getStateManager().saveView(context);

            // Fecha o stream do response
            response.getOutputStream().flush();
            response.getOutputStream().close();
            context.responseComplete();

        } catch (JRException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
