package br.com.sw2.gac.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.sw2.gac.util.MenuItem;

/**
 * <b>Descrição: controller do menu principal.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@ManagedBean
@SessionScoped
public class MenuBean extends BaseBean {

    /**
     * Nome: invokarPagina Invokar pagina.
     * @return string
     * @see
     */
    public String invokarPagina() {
        String toViewId = "";
        Integer codigo = Integer.parseInt(getRequestParameter("codigoModulo"));

        for (MenuItem item : MenuItem.values()) {

            if (codigo.intValue() == item.getCodigoModulo().intValue()) {
                toViewId = item.getViewID();
            }
        }
        return toViewId;
    }

}
