package br.com.sw2.gac.exception;

/**
 * <b>Descri��o: Exception gen�rica para regras de neg�cio n�o atendidas.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class BusinessException extends RuntimeException {

    /** Constante serialVersionUID. */
    public static final long serialVersionUID = -6353287459086929559L;

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

    }
    /*
     * Futuras implementa��es de log e audtoria ser�o feitas a partir deste tipo de exception
     */
}
