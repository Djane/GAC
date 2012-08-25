package br.com.sw2.gac.view;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

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

import org.apache.commons.collections.map.HashedMap;

import br.com.sw2.gac.jasper.JasperBeanFactory;
import br.com.sw2.gac.tools.EstadoDispositivo;
import br.com.sw2.gac.util.ClassLoaderUtils;

/**
 * <b>Descri��o:</b> <br>
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

        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(
                JasperBeanFactory.createBeanCollection());
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext
                .getCurrentInstance().getExternalContext().getResponse();

        InputStream inputStream = ClassLoaderUtils.getDefaultClassLoader().getResourceAsStream("br/com/sw2/gac/report/dispositivoEstado.jasper");

        try {
            JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, new HashedMap(),
                    beanCollectionDataSource);
            ServletOutputStream servletOutputStream = (ServletOutputStream) httpServletResponse
                    .getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
        } catch (JRException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
