package br.com.sw2.gac.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>Descrição: Objeto que representa um cliente no sistema.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class ClienteVO {

    /** Atributo lista forma contato. */
    private List<FormaContatoVO> listaFormaContato = new ArrayList<FormaContatoVO>();

    /**
     * Nome: getListaFormaContato Recupera o valor do atributo 'listaFormaContato'.
     * @return valor do atributo 'listaFormaContato'
     * @see
     */
    public List<FormaContatoVO> getListaFormaContato() {
        return listaFormaContato;
    }

    /**
     * Nome: setListaFormaContato Registra o valor do atributo 'listaFormaContato'.
     * @param listaFormaContato valor do atributo lista forma contato
     * @see
     */
    public void setListaFormaContato(List<FormaContatoVO> listaFormaContato) {
        this.listaFormaContato = listaFormaContato;
    }

}
