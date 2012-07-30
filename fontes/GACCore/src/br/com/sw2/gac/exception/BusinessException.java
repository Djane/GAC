package br.com.sw2.gac.exception;

import br.com.sw2.gac.tools.BusinessExceptionMessages;
import br.com.sw2.gac.util.LoggerUtils;

/**
 * <b>Descrição: Exception genérica para regras de negócio não atendidas.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class BusinessException extends RuntimeException {

    /** Constante serialVersionUID. */
    public static final long serialVersionUID = -6353287459086929559L;

    /** Atributo logger. */
    private LoggerUtils logger = LoggerUtils.getInstance(BusinessException.class);

    /**
     * Construtor Padrao Instancia um novo objeto BusinessException.
     */
    public BusinessException() {
        super();
    }

    /**
     * Construtor Padrao Instancia um novo objeto BusinessException.
     * @param message the message
     */
    public BusinessException(String message) {
        super(message);
        logger.error(message);

    }

    /**
     * Construtor Padrao Instancia um novo objeto BusinessException.
     * @param message the message
     */
    public BusinessException(BusinessExceptionMessages message) {
        logger.error(message.getValue() + " - " + message.getLabel());
    }

    /**
     * Construtor Padrao Instancia um novo objeto BusinessException.
     * @param code the code
     * @param message the message
     */
    public BusinessException(int code, String message) {
        super(message);
        logger.error(code + " - " + message);
    }

}
