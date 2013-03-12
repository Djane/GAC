package br.com.sw2.gac.socket.exception;


/**
 * <b>Descrição:</b> <br>.
 *
 * @author: SW2
 * @version 1.0
 *
 * Copyright 2013 SmartAngel.
 */
public class SocketConnectionException extends SocketException {


    /** Constante serialVersionUID. */
    private static final long serialVersionUID = 2660370688103387670L;

    /**
     * Construtor Padrao
     * Instancia um novo objeto SocketConnectionException.
     */
    public SocketConnectionException() {
        super();
    }

    /**
     * Construtor Padrao
     * Instancia um novo objeto SocketConnectionException.
     *
     * @param message the message
     * @param cause the cause
     * @param enableSuppression the enable suppression
     * @param writableStackTrace the writable stack trace
     */
    public SocketConnectionException(String message, Throwable cause, boolean enableSuppression,
        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    /**
     * Construtor Padrao
     * Instancia um novo objeto SocketConnectionException.
     *
     * @param message the message
     * @param cause the cause
     */
    public SocketConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Construtor Padrao
     * Instancia um novo objeto SocketConnectionException.
     *
     * @param message the message
     */
    public SocketConnectionException(String message) {
        super(message);
    }

    /**
     * Construtor Padrao
     * Instancia um novo objeto SocketConnectionException.
     *
     * @param cause the cause
     */
    public SocketConnectionException(Throwable cause) {
        super(cause);
    }

}
