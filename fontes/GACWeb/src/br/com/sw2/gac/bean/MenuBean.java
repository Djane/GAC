package br.com.sw2.gac.bean;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import br.com.sw2.gac.business.DispositivoBusiness;
import br.com.sw2.gac.util.ClassLoaderUtils;
import br.com.sw2.gac.util.MenuItem;
import br.com.sw2.gac.vo.DispositivoEstadoVO;
import br.com.sw2.gac.vo.RelHistDispositivoVO;
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

    private static final String IMAGEM = "/primefaces-smartangel/images/smartangel-150-90.jpg";
	private static final String HISTORICO_DISPOSITIVO_JASPER = "historicoDispositivo.jasper";
	/** Constante serialVersionUID. */
    private static final long serialVersionUID = -1506925064205437167L;

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
                toViewId = item.getViewID();
            }
        }
        return toViewId;
    }


    /**
     * Nome: verificarPermissaoPerfil
     * Verificar permissao perfil.
     *
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

        FacesContext context = FacesContext.getCurrentInstance();

        //Obtem os dados que serão exibidos no relatório
        DispositivoBusiness business = new DispositivoBusiness();
        List<DispositivoEstadoVO> lista = business.recuperaQtdeDispositivosPorEstado();
        //Seta o dataSource do relatório com os dados.
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(lista);

        //Abre o arquivo .jasper contendo o relatorio
        InputStream inputStream = ClassLoaderUtils.getJasperFileAsStream("dispositivoEstado.jasper");

        exportarRelatorio(context, beanCollectionDataSource, inputStream);
    }

    /**
     * Imprimir relatório histórico de dispositivos.
     * @param event the event
     */
    public void imprimirHistoricoDispositivos(ActionEvent event) {

        FacesContext context = FacesContext.getCurrentInstance();

        //Obtem os dados que serão exibidos no relatório
        DispositivoBusiness business = new DispositivoBusiness();
        List<RelHistDispositivoVO> lista = business.recuperaHistDispositivos(null, null, null, null);
        //Seta o dataSource do relatório com os dados.
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(lista);

        //Abre o arquivo .jasper contendo o relatorio
        InputStream inputStream = ClassLoaderUtils.getJasperFileAsStream(HISTORICO_DISPOSITIVO_JASPER);

        exportarRelatorio(context, beanCollectionDataSource, inputStream);
    }


	private void exportarRelatorio(FacesContext context,
			JRBeanCollectionDataSource beanCollectionDataSource,
			InputStream inputStream) {
		try {
            //Exporta o relatorio.
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("LOGO_SMARTANGEL", getUrlBase() + IMAGEM);
            JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parameters, beanCollectionDataSource);
            HttpServletResponse response = getHttpServletResponse();
            response.reset();
            response.setContentType("application/pdf");
            response.addHeader("Content-disposition", "inline; filename=relatorio.pdf");
            ServletOutputStream servletOutputStream = (ServletOutputStream) getHttpServletResponse().getOutputStream();

            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            context.getApplication().getStateManager().saveView(context);

            // Fecha o stream do response
            response.getOutputStream().flush();
            response.getOutputStream().close();
            context.responseComplete();

        } catch (Exception e) {
            getLogger().error(e);
        }
	}

}
