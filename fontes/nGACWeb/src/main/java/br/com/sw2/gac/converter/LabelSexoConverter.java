package br.com.sw2.gac.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.sw2.gac.tools.Sexo;

/**
 * <b>Descrição: Converter para o tipo de sexo. Retorna a descrição baseada no código.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@FacesConverter(value = "labelSexoConverter")
public class LabelSexoConverter implements Converter {

    /*
     * (non-Javadoc)
     * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext,
     * javax.faces.component.UIComponent, java.lang.String)
     */
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Sexo retorno = null;

        if ((value != null) && (!value.equals(""))) {
            for (Sexo item : Sexo.values()) {
                if (item.getValue().equals(value)) {
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
            for (Sexo item : Sexo.values()) {
                if (item.getValue().equals(value)) {
                    retorno = "Sexo " + item.getLabel();
                }
            }
        }
        return retorno;
    }

}
