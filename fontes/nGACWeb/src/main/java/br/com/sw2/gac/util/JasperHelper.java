package br.com.sw2.gac.util;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * <b>Descrição: Classe para auxilio na impressão dos relatório do Jasper.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public final class JasperHelper {

    /** Constante SESSION_ATTRIBUTE_JASPER_FILE_NAME. */
    public static final String SESSION_ATTRIBUTE_JASPER_FILE_NAME = "jasperFile";

    /** Constante SESSION_ATTRIBUTE_REPORT_PARAMETER_NAME. */
    public static final String SESSION_ATTRIBUTE_REPORT_PARAMETER_NAME = "beanParameters";

    /** Constante SESSION_ATTRIBUTE_REPORT_BEAN_COLLECTION_NAME. */
    public static final String SESSION_ATTRIBUTE_REPORT_BEAN_COLLECTION_NAME = "beanCollection";

    /** Atributo logger. */
    private LoggerUtils logger = LoggerUtils.getInstance(getClass());

    /**
     * Construtor Padrao Instancia um novo objeto JasperHelper.
     */
    private JasperHelper() {
        super();
    }

    /**
     * Nome: getParameterLogoSmartAngel Recupera o valor do atributo 'parameterLogoSmartAngel'.
     * @param request the request
     * @return valor do atributo 'parameterLogoSmartAngel'
     * @see
     */
    public static Map<String, Object> getParameterLogoSmartAngel(HttpServletRequest request) {

        Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put("LOGO_SMARTANGEL", getUrlBase(request)
            + "/primefaces-smartangel/images/logo/smartangel-147x87.jpg");

        return parameter;
    }

    /**
     * Retorna um java.io.InputStream referente ao nome do arquivo jasper informado. Os arquivos
     * .jasper serão procurados a partir do pacote br.com.sw2.gac.jasper.report
     * @param jasperFile nome do arquivo jasper a ser transformado em um java.io.InputStream
     * @return java.io.InputStream
     * @see
     */
    public static InputStream getJasperFileAsStream(String jasperFile) {
        return ClassLoaderUtils.getDefaultClassLoader().getResourceAsStream(
            "br/com/sw2/gac/jasper/report/" + jasperFile);

    }

    /**
     * Nome: getUrlBase Recupera o valor do atributo 'urlBase'.
     * @param request the request
     * @return valor do atributo 'urlBase'
     * @see
     */
    public static String getUrlBase(HttpServletRequest request) {
        return request.getScheme() + "://" + request.getServerName() + ":"
            + request.getServerPort() + request.getContextPath();
    }

    /**
     * Nome: clearSessionAtributes Clear session atributes.
     * @param request the request
     * @see
     */
    public static void clearSessionAtributes(HttpServletRequest request) {
        request.getSession().removeAttribute(SESSION_ATTRIBUTE_REPORT_BEAN_COLLECTION_NAME);
        request.getSession().removeAttribute(SESSION_ATTRIBUTE_REPORT_PARAMETER_NAME);
        request.getSession().removeAttribute(SESSION_ATTRIBUTE_JASPER_FILE_NAME);
    }


    /**
     * Nome: saveSessionAtributes
     * Save session atributes.
     *
     * @param request the request
     * @param jasperFileName the jasper file name
     * @param parameters the parameters
     * @param beanCollection the bean collection
     * @see
     */
    public static void saveSessionAtributes(HttpServletRequest request, String jasperFileName,
            Map<String, Object> parameters, List<?> beanCollection) {
        request.getSession().setAttribute(SESSION_ATTRIBUTE_JASPER_FILE_NAME, jasperFileName);
        request.getSession().setAttribute(SESSION_ATTRIBUTE_REPORT_PARAMETER_NAME, parameters);
        request.getSession().setAttribute(SESSION_ATTRIBUTE_REPORT_BEAN_COLLECTION_NAME, beanCollection);
    }

    /**
     * Nome: getRealPathReport
     * Recupera o valor do path de um relatório jasper baseado eum seu pacote e nome de arquivo .jasper.
     *
     * @param packageName the package name
     * @param reportFileName the report file name
     * @return valor do atributo 'pathReport'
     * @see
     */
    public static String getRealPathReport(String packageName, String reportFileName) {
        String path = ClassLoaderUtils.getDefaultClassLoader().getResource(packageName + reportFileName).getPath();
        path.replace("/", File.separator);
        path = path.substring(0, (path.length() - reportFileName.length()));
        return path;
    }
}
