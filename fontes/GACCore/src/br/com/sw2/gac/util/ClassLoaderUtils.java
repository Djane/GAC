package br.com.sw2.gac.util;

/**
 * <b>Descrição:</b> <br>
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

}
