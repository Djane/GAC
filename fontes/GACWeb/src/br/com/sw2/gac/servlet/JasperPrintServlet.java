package br.com.sw2.gac.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import br.com.sw2.gac.util.CollectionUtils;
import br.com.sw2.gac.util.JasperHelper;
import br.com.sw2.gac.util.LoggerUtils;

/**
 * Implementação servlet para impressão dos relatorios jasper no iframe global.
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@WebServlet("/reportview")
public class JasperPrintServlet extends HttpServlet {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** Atributo logger. */
    private LoggerUtils logger = LoggerUtils.getInstance(getClass());

    /**
     * Construtor Padrao Instancia um novo objeto JasperPrintServlet.
     * @see HttpServlet#HttpServlet()
     */
    public JasperPrintServlet() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        this.logger.debug("***** Iniciando método doGet do servlet reportview *****");

        Collection<?> beanCollection = (Collection<?>) request.getSession().getAttribute(
            "beanCollection");

        if (CollectionUtils.isEmptyOrNull(beanCollection)) {
            response.sendRedirect(response.encodeRedirectURL(JasperHelper.getUrlBase(request)
                + "/report_blank.xhtml"));
        } else {

            try {
                Map<String, Object> beanParameters = (Map<String, Object>) request.getSession()
                    .getAttribute("beanParameters");
                String jasperFile = (String) request.getSession().getAttribute("jasperFile");
                // Seta o dataSource
                JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(
                    beanCollection);

                // Abre o arquivo .jasper contendo o relatorio
                InputStream inputStream = JasperHelper.getJasperFileAsStream(jasperFile);

                Map<String, Object> parameters = new HashMap<String, Object>();
                parameters.putAll(JasperHelper.getParameterLogoSmartAngel(request));
                if (beanParameters != null) {
                    parameters.putAll(beanParameters);
                }
                JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parameters,
                    beanCollectionDataSource);

                response.reset();
                response.setContentType("application/pdf");

                response.addHeader("Content-disposition", "inline; filename=relatorio.pdf");

                ServletOutputStream servletOutputStream = (ServletOutputStream) response
                    .getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
                response.getOutputStream().flush();
                response.getOutputStream().close();

            } catch (Exception e) {
                logger.error("Erro no processamento do Servlet de visualização de relatórios !");
                logger.error(e);
            }
            this.logger.debug("***** Finalizado método doGet do servlet reportview *****");
        }
        JasperHelper.clearSessionAtributes(request);
    }

}
