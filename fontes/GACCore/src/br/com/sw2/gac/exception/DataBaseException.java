package br.com.sw2.gac.exception;

import br.com.sw2.gac.util.LoggerUtils;

/**
 * <b>Descrição: Exception genérica para regras de negócio não atendidas.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class DataBaseException extends RuntimeException {

    /** Atributo delete violacao constraint. */
    public static final int DELETE_VIOLACAO_CONSTRAINT = 2000;

    /** Constante FALHA_COMUNICACAO_BANCO. */
    public static final int FALHA_COMUNICACAO_BANCO = 2001;

    /** Atributo exception code. */
    private int exceptionCode;

    /** Constante serialVersionUID. */
    public static final long serialVersionUID = -6353287459086929559L;

    /** Atributo logger. */
    private LoggerUtils logger = LoggerUtils.getInstance(DataBaseException.class);

    /**
     * Construtor Padrao Instancia um novo objeto BusinessException.
     */
    public DataBaseException() {
        super();
    }

    /**
     * Construtor Padrao Instancia um novo objeto BusinessException.
     * @param message the message
     */
    public DataBaseException(String message) {
        super(message);
        logger.error(message);

    }

    /**
     * Construtor Padrao Instancia um novo objeto BusinessException.
     * @param code the code
     * @param message the message
     */
    public DataBaseException(int code, String message) {
        super(message);
        this.exceptionCode = code;
        logger.error(code + " - " + message);
    }

    /**
     * Nome: getExceptionCode Recupera o valor do atributo 'exceptionCode'.
     * @return valor do atributo 'exceptionCode'
     * @see
     */
    public int getExceptionCode() {
        return exceptionCode;
    }

    /**
     * Nome: setExceptionCode Registra o valor do atributo 'exceptionCode'.
     * @param exceptionCode valor do atributo exception code
     * @see
     */
    public void setExceptionCode(int exceptionCode) {
        this.exceptionCode = exceptionCode;
    }
}
