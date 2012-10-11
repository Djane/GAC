package br.com.sw2.gac.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.sw2.gac.util.StringUtil;

/**
 * <b>Descrição: Converte um valor string em BiDecimal e um valor BigDecimal em String.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@FacesConverter(value = "telefoneConverter")
public class TelefoneConverter implements Converter {

    /*
     * (non-Javadoc)
     * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext,
     * javax.faces.component.UIComponent, java.lang.String)
     */
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        return value;
    }

    /*
     * (non-Javadoc)
     * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext,
     * javax.faces.component.UIComponent, java.lang.Object)
     */
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        final int tamanhoCelular = 11;
        final int tamanhoFixo = 10;
        String maskCelular = "(##)#####-####";
        String maskFixo = "(##)####-####";
        String retorno = null;
        String numero = value.toString();
        if (numero.length() == tamanhoCelular) {
            retorno = StringUtil.formatString(numero, maskCelular);
        } else if (numero.length() == tamanhoFixo) {
            retorno = StringUtil.formatString(numero, maskFixo);
        } else {
            retorno = numero;
        }
        return retorno;
    }
}
