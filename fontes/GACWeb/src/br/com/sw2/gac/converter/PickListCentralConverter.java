package br.com.sw2.gac.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.sw2.gac.vo.CentralVO;

/**
 * <b>Descrição: Popular a pickList de dispositivos dispositivos.</b> <br>
 * .
 * @author: lucianor
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@FacesConverter(forClass = CentralVO.class, value = "centralConverter")
public class PickListCentralConverter implements Converter {

    /*
     * (non-Javadoc)
     * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext,
     * javax.faces.component.UIComponent, java.lang.String)
     */
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        CentralVO central = new CentralVO();

        if ((value != null) && (!value.equals(""))) {
            central.setIdCentral(Integer.parseInt(value));
        }
        return central;
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
            CentralVO central = new CentralVO();
            central = (CentralVO) value;
            retorno = central.getIdCentral();
        }
        return retorno.toString();
    }

}
