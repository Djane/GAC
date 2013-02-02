package br.com.sw2.gac.socket;

/**
 * <b>Descrição:</b> <br>.
 *
 * @author: SW2
 * @version 1.0
 *
 * Copyright 2013 SmartAngel.
 */
public class SocketCommandException extends SocketException {


    /** Constante serialVersionUID. */
    private static final long serialVersionUID = -5881849615536061760L;

    /**
     * Construtor Padrao
     * Instancia um novo objeto SocketCommandException.
     */
    public SocketCommandException() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * Construtor Padrao
     * Instancia um novo objeto SocketCommandException.
     *
     * @param message the message
     * @param cause the cause
     * @param enableSuppression the enable suppression
     * @param writableStackTrace the writable stack trace
     */
    public SocketCommandException(String message, Throwable cause, boolean enableSuppression,
        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    /**
     * Construtor Padrao
     * Instancia um novo objeto SocketCommandException.
     *
     * @param message the message
     * @param cause the cause
     */
    public SocketCommandException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Construtor Padrao
     * Instancia um novo objeto SocketCommandException.
     *
     * @param message the message
     */
    public SocketCommandException(String message) {
        super(message);
    }

    /**
     * Construtor Padrao
     * Instancia um novo objeto SocketCommandException.
     *
     * @param cause the cause
     */
    public SocketCommandException(Throwable cause) {
        super(cause);
    }

}
