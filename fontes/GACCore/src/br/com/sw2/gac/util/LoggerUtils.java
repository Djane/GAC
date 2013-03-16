package br.com.sw2.gac.util;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


/**
 * <b>Descrição: Classe wrapper para o serviço de log. Retira a dependência de frameworks terceiros
 * na aplicação</b> <br>.
 *
 * @author: SW2
 * @version 1.0
 *
 * Copyright 2013 SmartAngel.
 */
public final class LoggerUtils {

    /** The instance. */
    private static LoggerUtils instance = null;

    /** Atributo logger. */
    private Logger logger;

    /**
     * Nome: init
     * Inits the.
     *
     * @param fileProperties the file properties
     * @see
     */
    public static synchronized void init(String fileProperties) {

        instance = new LoggerUtils();

        try {
            PropertyConfigurator.configure(fileProperties);
        } catch (Exception exception) {
            System.err.println("Falha no carregamento da configuração do Log4j  " + exception);
        }
    }

    /**
     * Obtem um instancia unica atraves do metodo LoggerUtils.
     *
     * @param currentObject the current object
     * @return Retorna a instancia atraves do metodo LoggerUtils
     */
    public static LoggerUtils getInstance(Object currentObject) {
        if (null != instance) {
            if (ObjectUtils.instanceOf(currentObject, Class.class)) {
                instance.logger = Logger.getLogger((Class<?>) currentObject);
            } else {
                instance.logger = Logger.getLogger(currentObject.getClass());
            }
        } else {
            instance = new LoggerUtils();
        }
        return instance;
    }


    /**
     * Nome: debug
     * Debug.
     *
     * @param obj the obj
     * @see
     */
    public void debug(Object obj) {
        this.logger.debug(" - " + obj);
    }


    /**
     * Nome: debug
     * Debug.
     *
     * @param clazz the clazz
     * @param obj the obj
     * @see
     */
    public void debug(Class<?> clazz, Object obj) {
        this.logger.debug(clazz.getName() + " - " + obj);
    }


    /**
     * Nome: info
     * Info.
     *
     * @param obj the obj
     * @see
     */
    public void info(Object obj) {
        this.logger.info(" - " + obj);
    }


    /**
     * Nome: fatal
     * Fatal.
     *
     * @param obj the obj
     * @see
     */
    public void fatal(Object obj) {
        this.logger.fatal(" - " + obj);
    }

    /**
     * Nome: warn
     * Warn.
     *
     * @param obj the obj
     * @see
     */
    public void warn(Object obj) {
        this.logger.warn(" - " + obj);
    }

    /**
     * Nome: error
     * Error.
     *
     * @param clazz the clazz
     * @param obj the obj
     * @see
     */
    public void error(Class<?> clazz, Object obj) {
        this.logger.error(clazz.getName() + " - " + obj);
    }

    /**
     * Nome: error
     * Error.
     *
     * @param obj the obj
     * @see
     */
    public void error(Object obj) {
        this.logger.error(" - " + obj);
    }

}
