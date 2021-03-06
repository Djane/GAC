package br.com.sw2.gac.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.sw2.gac.tools.TipoContato;

/**
 * <b>Descri��o: Converte o codigo de uma forma de pagamento em uma descri��o.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@FacesConverter(value = "formaContatoConverter")
public class FormaContatoConverter implements Converter {

    /*
     * (non-Javadoc)
     * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext,
     * javax.faces.component.UIComponent, java.lang.String)
     */
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        TipoContato retorno = null;

        if ((value != null) && (!value.equals(""))) {
            for (TipoContato item : TipoContato.values()) {
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
            for (TipoContato item : TipoContato.values()) {
                if (item.getValue().equals(value)) {
                    retorno = item.getLabel();
                }
            }
        }
        return retorno;
    }

}
