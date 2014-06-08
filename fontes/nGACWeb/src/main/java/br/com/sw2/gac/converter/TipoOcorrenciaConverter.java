package br.com.sw2.gac.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.sw2.gac.tools.TipoOcorrencia;

/**
 * <b>Descri��o: Converter para o tipo de ocorrencia.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@FacesConverter(value = "tipoOcorrenciaConverter")
public class TipoOcorrenciaConverter implements Converter {

    /*
     * (non-Javadoc)
     * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext,
     * javax.faces.component.UIComponent, java.lang.String)
     */
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        TipoOcorrencia retorno = null;

        if ((value != null) && (!value.equals(""))) {
            for (TipoOcorrencia item : TipoOcorrencia.values()) {
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
            for (TipoOcorrencia item : TipoOcorrencia.values()) {
                if (item.getValue().equals(value.toString())) {
                    retorno = item.getLabel();
                }
            }
        }
        return retorno;
    }

}
