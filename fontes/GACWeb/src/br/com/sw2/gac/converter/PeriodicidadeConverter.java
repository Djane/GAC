package br.com.sw2.gac.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.sw2.gac.tools.Periodicidade;

/**
 * <b>Descrição: Converter para obtenção da descrição da frequencia de um tratamento.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@FacesConverter(value = "periodicidadeConverter")
public class PeriodicidadeConverter implements Converter {

    /*
     * (non-Javadoc)
     * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext,
     * javax.faces.component.UIComponent, java.lang.String)
     */
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Periodicidade retorno = null;

        if ((value != null) && (!value.equals(""))) {
            for (Periodicidade item : Periodicidade.values()) {
                if (item.getValue() == Integer.parseInt(value)) {
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
            for (Periodicidade item : Periodicidade.values()) {
                if (item.getValue() == Integer.parseInt(value.toString())) {
                    retorno = item.getLabel();
                }
            }
        }
        return retorno;
    }

}
