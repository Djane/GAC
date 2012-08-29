package br.com.sw2.gac.converter;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * <b>Descrição: Converte um valor string em BiDecimal e um valor BigDecimal em String.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@FacesConverter(value = "bigDecimalConverter")
public class BigDecimalConverter implements Converter {

    /*
     * (non-Javadoc)
     * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext,
     * javax.faces.component.UIComponent, java.lang.String)
     */
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        BigDecimal retorno = new BigDecimal("0.00").setScale(2);
        DecimalFormat dff = (DecimalFormat) DecimalFormat.getInstance();
        try {
            double valor = dff.parse(value).doubleValue();
            retorno = BigDecimal.valueOf(valor).setScale(2);
        } catch (ParseException e) {
            e.printStackTrace();
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
        DecimalFormat dff = new java.text.DecimalFormat("#,###,##0.00");
        if ((value != null) && (!value.equals(""))) {
            BigDecimal bigDecimal = (BigDecimal) value;
            retorno = dff.format(bigDecimal);
        }
        return retorno;
    }

}
