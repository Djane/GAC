package br.com.sw2.gac.view;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.sw2.gac.util.DateUtil;

/**
 * <b>Descrição:</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@ManagedBean
@SessionScoped
public class CabecalhoBean extends BaseBean {

    /** Atributo data atual extenso. */
    private String dataAtualExtenso = DateUtil.getDataCompleta();

    /**
     * Nome: getDataAtualExtenso Recupera o valor do atributo 'dataAtualExtenso'.
     * @return valor do atributo 'dataAtualExtenso'
     * @see
     */
    public String getDataAtualExtenso() {
        return dataAtualExtenso;
    }

    /**
     * Nome: setDataAtualExtenso Registra o valor do atributo 'dataAtualExtenso'.
     * @param dataAtualExtenso valor do atributo data atual extenso
     * @see
     */
    public void setDataAtualExtenso(String dataAtualExtenso) {
        this.dataAtualExtenso = dataAtualExtenso;
    }

}
