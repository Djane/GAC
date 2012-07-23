package br.com.sw2.gac.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.sw2.gac.vo.DoencaVO;

/**
 * <b>Descrição:</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@FacesConverter(forClass = DoencaVO.class, value = "pickListDoencaConverter")
public class PickListDoencasConverter implements Converter {

    /*
     * (non-Javadoc)
     * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext,
     * javax.faces.component.UIComponent, java.lang.String)
     */
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        DoencaVO dispositivo = new DoencaVO();

        if ((value != null) && (!value.equals(""))) {
            dispositivo.setIdDoenca(Integer.parseInt(value));
        }
        return dispositivo;
    }

    /*
     * (non-Javadoc)
     * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext,
     * javax.faces.component.UIComponent, java.lang.Object)
     */
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Integer retorno = null;
        if (!(value == null)) {
            DoencaVO dispositivo = new DoencaVO();
            dispositivo = (DoencaVO) value;
            retorno = dispositivo.getIdDoenca();
        }
        return retorno.toString();
    }

}
