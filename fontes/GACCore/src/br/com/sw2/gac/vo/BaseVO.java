package br.com.sw2.gac.vo;

/**
 * <b>Descrição:</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class BaseVO {

    /** Atributo crud. */
    private String crud = "";

    /**
     * Nome: getCrud Recupera o valor do atributo 'crud'.
     * @return valor do atributo 'crud'
     * @see
     */
    public String getCrud() {
        return crud;
    }

    /**
     * Nome: setCrud Registra o valor do atributo 'crud'.
     * @param crud valor do atributo crud
     * @see
     */
    public void setCrud(String crud) {
        this.crud = crud;
    }

}
