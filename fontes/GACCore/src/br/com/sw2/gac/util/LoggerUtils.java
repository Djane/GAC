package br.com.sw2.gac.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * <b>Descrição: Classe wrapper para o serviço de log. Retira a dependência de frameworks terceiros
 * na aplicação</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public final class LoggerUtils {

    /** Atributo logger. */
    private Logger logger;

    /** Atributo external properties. */
    private static String externalProperties = "log4j-gac.properties";

    /** Atributo date format. */
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

    /**
     * Construtor privado. Inicializa o Logger.
     * @param currentObject the current object
     */
    private LoggerUtils(Object currentObject) {

        this.setUp();

        if (ObjectUtils.instanceOf(currentObject, Class.class)) {
            this.logger = Logger.getLogger((Class<?>) currentObject);
        } else {
            this.logger = Logger.getLogger(currentObject.getClass());
        }

    }

    /**
     * Inicializa a configuração padrão para o logger.
     * @see
     */
    private void setUp() {
        try {
            PropertyConfigurator.configure(externalProperties);
        } catch (Exception exception) {
            System.err.println("Falha no carregamento da configura��o do Log4j  " + exception);
        }
    }

    /**
     * Cria o logger para o objeto 'caller'.
     * @param currentObject the current object
     * @return loggerUtils
     */
    public static LoggerUtils getInstance(Object currentObject) {
        return (new LoggerUtils(currentObject));
    }

    /**
     * Obtem um instancia unica atraves do metodo LoggerUtils.
     *
     * @param currentObject the current object
     * @param externalProperties the external properties
     * @return Retorna a instancia atraves do metodo LoggerUtils
     */
    public static LoggerUtils getInstance(Object currentObject, String externalProperties) {
        LoggerUtils.externalProperties = externalProperties;
        return (new LoggerUtils(currentObject));
    }

    /**
     * Método wrapper para log nível DEBUG.
     * @param obj Objeto a ser logado.
     * @see
     */
    public void debug(Object obj) {
        this.logger.debug(dateFormat.format(new Date()) + " - " + obj);
    }

    /**
     * Método wrapper para uso log INFO.
     * @param obj Objeto a ser logado.
     * @see
     */
    public void info(Object obj) {
        this.logger.info(dateFormat.format(new Date()) + " - " + obj);
    }

    /**
     * Método wrapper para log nível FATAL.
     * @param obj Objeto a ser logado.
     * @see
     */
    public void fatal(Object obj) {
        this.logger.fatal(dateFormat.format(new Date()) + " - " + obj);
    }

    /**
     * Método wrapper para log nível WARNING.
     * @param obj Objeto a ser logado.
     * @see
     */
    public void warn(Object obj) {
        this.logger.warn(dateFormat.format(new Date()) + " - " + obj);
    }

    /**
     * Método wrapper para log nível ERROR.
     * @param obj Objeto a ser logado.
     * @see
     */
    public void error(Object obj) {
        this.logger.error(dateFormat.format(new Date()) + " - " + obj);
    }

    /**
     * Nome: getExternalProperties
     * Recupera o valor do atributo 'externalProperties'.
     *
     * @return valor do atributo 'externalProperties'
     * @see
     */
    public static String getExternalProperties() {
        return externalProperties;
    }

    /**
     * Nome: setExternalProperties
     * Registra o valor do atributo 'externalProperties'.
     *
     * @param externalProperties valor do atributo external properties
     * @see
     */
    public static void setExternalProperties(String externalProperties) {
        LoggerUtils.externalProperties = externalProperties;
    }


}
