package br.com.sw2.gac.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.sw2.gac.tools.EstadoDispositivo;

/**
 * <b>Descrição: Converte o codigo de um estado de dispositivo em uma descrição.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@FacesConverter(value = "estadoDispositivoConverter")
public class EstadoDispositivoConverter implements Converter {

    /*
     * (non-Javadoc)
     * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext,
     * javax.faces.component.UIComponent, java.lang.String)
     */
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        EstadoDispositivo retorno = null;

        if ((value != null) && (!value.equals(""))) {
            for (EstadoDispositivo item : EstadoDispositivo.values()) {
                if (item.getValue() == (Integer.parseInt(value))) {
                    retorno = item;
                }
            }
        }
        return retorno;
    }

    /*
     * (non-Javadoc)
     * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext,
     * javax.faces.component.UIComponent, java.lang.Object)
     */
    @Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
        String retorno = null;
        if ((value != null) && (!value.equals(""))) {
            for (EstadoDispositivo item : EstadoDispositivo.values()) {
                if (item.getValue() == (Integer.parseInt(value.toString()))) {
                    retorno = item.getLabel();
                }
            }
        }
        return retorno;
    }

}
