package br.com.sw2.gac.exception;


/**
 * <b>Descrição: Exceptio que indica falha no login</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class LoginFailedException extends BusinessException {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = -1463116694762117907L;

    /**
     * Construtor Padrao Instancia um novo objeto LoginFailedException.
     */
    public LoginFailedException() {
        super();
    }

    /**
     * Construtor Padrao Instancia um novo objeto LoginFailedException.
     * @param message the message
     */
    public LoginFailedException(String message) {
        super(message);

    }
}
