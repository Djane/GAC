package br.com.sw2.gac.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.sw2.gac.tools.LocalizacaoDispositivo;

/**
 * <b>Descri��o: Converte o codigo de uma localiza��o de dispositivo em uma descri��o.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@FacesConverter(value = "localizacaoDispositivoConverter")
public class LocalizacaoDispositivoConverter implements Converter {

    /*
     * (non-Javadoc)
     * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext,
     * javax.faces.component.UIComponent, java.lang.String)
     */
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        LocalizacaoDispositivo retorno = null;

        if ((value != null) && (!value.equals(""))) {
            for (LocalizacaoDispositivo item : LocalizacaoDispositivo.values()) {
                if (item.getCodLocal() == Integer.parseInt(value)) {
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
            for (LocalizacaoDispositivo item : LocalizacaoDispositivo.values()) {
                if (item.getCodLocal() == Integer.parseInt(value.toString())) {
                    retorno = item.getDescLocal();
                }
            }
        }
        return retorno;
    }

}
