package br.com.sw2.gac.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.sw2.gac.vo.DispositivoVO;

/**
 * <b>Descrição: Popular a pickList de dispositivos dispositivos.</b> <br>
 * .
 * @author: lucianor
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@FacesConverter(forClass = DispositivoVO.class, value = "dispositivoConverter")
public class PickListDispositivoConverter implements Converter {

    /*
     * (non-Javadoc)
     * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext,
     * javax.faces.component.UIComponent, java.lang.String)
     */
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        DispositivoVO dispositivo = new DispositivoVO();

        if ((value != null) && (!value.equals(""))) {
            dispositivo.setIdDispositivo(Integer.parseInt(value));
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
            DispositivoVO dispositivo = new DispositivoVO();
            dispositivo = (DispositivoVO) value;
            retorno = dispositivo.getIdDispositivo();
        }
        return retorno.toString();
    }

}
