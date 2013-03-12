package br.com.sw2.gac.socket.exception;

/**
 * <b>Descrição:</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2013 SmartAngel.
 */
public class SocketException extends RuntimeException {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = -749540759999736001L;

    /**
     * Construtor Padrao Instancia um novo objeto SocketException.
     */
    public SocketException() {
        super();
    }

    /**
     * Construtor Padrao Instancia um novo objeto SocketException.
     * @param message the message
     * @param cause the cause
     * @param enableSuppression the enable suppression
     * @param writableStackTrace the writable stack trace
     */
    public SocketException(String message, Throwable cause, boolean enableSuppression,
        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);

    }

    /**
     * Construtor Padrao Instancia um novo objeto SocketException.
     * @param message the message
     * @param cause the cause
     */
    public SocketException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Construtor Padrao Instancia um novo objeto SocketException.
     * @param message the message
     */
    public SocketException(String message) {
        super(message);

    }

    /**
     * Construtor Padrao Instancia um novo objeto SocketException.
     * @param cause the cause
     */
    public SocketException(Throwable cause) {
        super(cause);
    }

}
