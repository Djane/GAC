package br.com.sw2.gac.util;

import org.eclipse.persistence.logging.AbstractSessionLog;
import org.eclipse.persistence.logging.SessionLog;
import org.eclipse.persistence.logging.SessionLogEntry;

/**
 * <b>Descrição: Classe responsável por direcionar os logs do EclipseLink para o Log4J.</b> <br>
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class EclipseLinkSessionLogger extends AbstractSessionLog implements SessionLog {

    /** Atributo logger. */
    private LoggerUtils logger = LoggerUtils.getInstance(this);

    /**
     * {@inheritDoc}
     */
    public void log(SessionLogEntry sessionLogEntry) {

        switch (sessionLogEntry.getLevel()) {
            case SEVERE:
                logger.error(sessionLogEntry.getMessage());
                break;
            case WARNING:
                logger.warn(sessionLogEntry.getMessage());
                break;
            case INFO:
                logger.info(sessionLogEntry.getMessage());
                break;
            default:
                logger.debug(sessionLogEntry.getMessage());
        }
    }
}