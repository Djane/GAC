package br.com.sw2.gac.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>Descrição: Exception utilizada para representação de dados obrigatórios não fornecidos.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2013 SmartAngel.
 */
public class DadosIncompletosException extends BusinessException {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = 8727544495090368450L;

    /** Atributo list key message. */
    private List<String> listKeyMessage = new ArrayList<String>();

    /**
     * Construtor Padrao
     * Instancia um novo objeto DadosIncompletosException.
     *
     * @param keys the keys
     */
    public DadosIncompletosException(List<String> keys) {
        this.listKeyMessage = keys;
    }

    /**
     * Nome: getListKeyMessage
     * Recupera o valor do atributo 'listKeyMessage'.
     *
     * @return valor do atributo 'listKeyMessage'
     * @see
     */
    public List<String> getListKeyMessage() {
        return listKeyMessage;
    }

    /**
     * Nome: setListKeyMessage
     * Registra o valor do atributo 'listKeyMessage'.
     *
     * @param listKeyMessage valor do atributo list key message
     * @see
     */
    public void setListKeyMessage(List<String> listKeyMessage) {
        this.listKeyMessage = listKeyMessage;
    }

}
