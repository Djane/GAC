package br.com.sw2.gac.util;

import java.io.InputStream;

/**
 * <b>Descrição: Clasee usilitaria para encapular operações que envolvam o class loader.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public final class ClassLoaderUtils {

    /**
     * Construtor Padrao Instancia um novo objeto ClassLoaderUtils.
     */
    private ClassLoaderUtils() {
    }

    /**
     * Recupera o class loader pardrão para utilização na aplicação.
     * @return java.lang.ClassLoader
     * @see
     */
    public static ClassLoader getDefaultClassLoader() {
        ClassLoader classLoader = null;
        try {
            classLoader = Thread.currentThread().getContextClassLoader();
        } catch (Throwable ex) {
            classLoader = ClassLoaderUtils.class.getClassLoader();
        }
        return (classLoader);
    }

    /**
     * Retorna um java.io.InputStream referente ao nome do arquivo jasper informado. Os arquivos
     * .jasper ser�o procurados a partir do pacote br.com.sw2.gac.jasper.report
     * @param jasperFile nome do arquivo jasper a ser transformado em um java.io.InputStream
     * @return java.io.InputStream
     */
    public static InputStream getJasperFileAsStream(String jasperFile) {

        return getDefaultClassLoader().getResourceAsStream(
                "br/com/sw2/gac/jasper/report/" + jasperFile);

    }

}
