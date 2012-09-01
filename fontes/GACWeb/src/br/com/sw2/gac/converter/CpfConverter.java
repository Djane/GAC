/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sw2.gac.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 * <b>Descricão: Converte a formatação do CPF.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@FacesConverter(value = "cpfConverter")
public class CpfConverter implements Converter {

    private static final int NUMERO_03 = 3;
    private static final int NUMERO_06 = 6;
    private static final int NUMERO_09 = 9;
    private static final int NUMERO_11 = 11;

    /*
     * (non-Javadoc)
     * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext,
     * javax.faces.component.UIComponent, java.lang.String)
     */
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value)
        throws ConverterException {

        /*
         * Irá converter CPF formatado para um sem pontos e traço. Ex.: 355.245.198-87 torna-se
         * 35524519887.
         */
        String cpf = value;
        if (value != null && !value.equals("")) {
            cpf = value.replaceAll("\\.", "").replaceAll("\\-", "");
        }

        return cpf;

    }


    /*
     * (non-Javadoc)
     * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext,
     * javax.faces.component.UIComponent, java.lang.String)
     */
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value)
        throws ConverterException {

        /*
         * Irá converter CPF não formatado para um com pontos e traço. Ex.: 35524519887 torna-se
         * 355.245.198-87.
         */
        String cpf = (String) value;
        if (cpf != null && cpf.length() == NUMERO_11) {
            cpf = cpf.substring(0, NUMERO_03) + "." + cpf.substring(NUMERO_03, NUMERO_06) + "." + cpf.substring(NUMERO_06, NUMERO_09) + "-"
                    + cpf.substring(NUMERO_09, NUMERO_11);
        }

        return cpf;
    }
}
