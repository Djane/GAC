package br.com.sw2.gac.exception;

import br.com.sw2.gac.business.UsuarioBusiness;
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

    private LoggerUtils logger = LoggerUtils.getInstance(UsuarioBusiness.class);

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

}
