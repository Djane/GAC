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

    /** Atributo date format. */
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy - hh:mm");

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
            PropertyConfigurator.configure(ObjectUtils
                    .getResourceAsProperties("log4j-gac.properties"));
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
}
