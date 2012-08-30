package br.com.sw2.gac.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * <b>Descri��o: Classe wrapper para o servi�o de log. Retira a depend�ncia de frameworks terceiros
 * na aplica��o</b> <br>
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
        if (ResourceUtils.instanceOf(currentObject, Class.class)) {
            this.logger = Logger.getLogger((Class<?>) currentObject);
        } else {
            this.logger = Logger.getLogger(currentObject.getClass());
        }

        this.setUp();
    }

    /**
     * Inicializa a configura��o padr�o para o logger.
     * @see
     */
    private void setUp() {
        try {
            PropertyConfigurator.configure(ResourceUtils
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
     * M�todo wrapper para log n�vel DEBUG.
     * @param obj Objeto a ser logado.
     * @see
     */
    public void debug(Object obj) {
        this.logger.debug(dateFormat.format(new Date()) + " - " + obj);
    }

    /**
     * M�todo wrapper para uso log INFO.
     * @param obj Objeto a ser logado.
     * @see
     */
    public void info(Object obj) {
        this.logger.info(dateFormat.format(new Date()) + " - " + obj);
    }

    /**
     * M�todo wrapper para log n�vel FATAL.
     * @param obj Objeto a ser logado.
     * @see
     */
    public void fatal(Object obj) {
        this.logger.fatal(dateFormat.format(new Date()) + " - " + obj);
    }

    /**
     * M�todo wrapper para log n�vel WARNING.
     * @param obj Objeto a ser logado.
     * @see
     */
    public void warn(Object obj) {
        this.logger.warn(dateFormat.format(new Date()) + " - " + obj);
    }

    /**
     * M�todo wrapper para log n�vel ERROR.
     * @param obj Objeto a ser logado.
     * @see
     */
    public void error(Object obj) {
        this.logger.error(dateFormat.format(new Date()) + " - " + obj);
    }
}
