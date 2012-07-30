package br.com.sw2.gac.exception;

/**
 * <b>Descrição: Exception genérica para regras de negócio não atendidas.</b> <br>
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
     * Futuras implementações de log e audtoria serão feitas a partir deste tipo de exception
     */
}
