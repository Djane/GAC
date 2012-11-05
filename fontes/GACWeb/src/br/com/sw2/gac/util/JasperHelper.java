package br.com.sw2.gac.util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * <b>Descrição: Classe para auxilio na impressão dos relatório do Jasper.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public final class JasperHelper {

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

        String urlBase = request.getScheme() + "://" + request.getServerName() + ":"
            + request.getServerPort() + request.getContextPath();

        Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put("LOGO_SMARTANGEL", urlBase
            + "/primefaces-smartangel/images/logo/smartangel-147x87.jpg");

        return parameter;
    }

    /**
     * Retorna um java.io.InputStream referente ao nome do arquivo jasper informado. Os arquivos
     * .jasper serão procurados a partir do pacote br.com.sw2.gac.jasper.report
     * @param jasperFile nome do arquivo jasper a ser transformado em um java.io.InputStream
     * @return java.io.InputStream
     */
    public static InputStream getJasperFileAsStream(String jasperFile) {
        return ClassLoaderUtils.getDefaultClassLoader().getResourceAsStream(
            "br/com/sw2/gac/jasper/report/" + jasperFile);

    }

}
