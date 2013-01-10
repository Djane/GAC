package br.com.sw2.gac.exception;

/**
 * <b>Descrição:</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2013 SmartAngel.
 */
public class StatusOcorrenciaException extends BusinessException {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = 8727544495090368450L;

    /**
     * Construtor Padrao
     * Instancia um novo objeto StatusOcorrenciaException.
     *
     * @param message the message
     */
    public StatusOcorrenciaException(String message) {
        super(message);
    }

}
