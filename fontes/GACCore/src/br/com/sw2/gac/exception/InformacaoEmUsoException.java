package br.com.sw2.gac.exception;


/**
 * <b>Descrição: Exception utilizada para representação de dados obrigatórios não fornecidos.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2013 SmartAngel.
 */
public class InformacaoEmUsoException extends BusinessException {


    /** Constante serialVersionUID. */
    private static final long serialVersionUID = 3267382446152922586L;

    /**
     * Construtor Padrao
     * Instancia um novo objeto InformacaoEmUsoException.
     *
     * @param message the message
     */
    public InformacaoEmUsoException(String message) {
        super(message);
    }


}
