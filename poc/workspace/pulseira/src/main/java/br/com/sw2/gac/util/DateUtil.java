package br.com.sw2.gac.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import br.com.sw2.gac.tools.DiasDaSemana;
import br.com.sw2.gac.tools.MesesDoAno;

/**
 * <b>Descri��o:</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public abstract class DateUtil {

    /** Atributo day week. */
    private static DiasDaSemana[] dayWeek = DiasDaSemana.values();

    /** Atributo month. */
    private static MesesDoAno[] month = MesesDoAno.values();

    /**
     * Nome: getDataCompleta Recupera o valor do atributo 'dataCompleta'.
     * @return valor do atributo 'dataCompleta'
     * @see
     */
    public static String getDataCompleta() {
        return getDataCompleta(new Date());
    }

    /**
     * Nome: getDataCompleta Recupera o valor do atributo 'dataCompleta'.
     * @param date the date
     * @return valor do atributo 'dataCompleta'
     * @see
     */
    public static String getDataCompleta(Date date) {
        String retorno;
        GregorianCalendar data = new GregorianCalendar();
        data.setTime(date);
        retorno = dayWeek[data.get(Calendar.DAY_OF_WEEK) - 1].getLabel();

        retorno = retorno + ", " + data.get(Calendar.DAY_OF_MONTH);
        retorno = retorno + " de " + month[data.get(Calendar.MONTH)].getLabel();
        retorno = retorno + " de " + data.get(Calendar.YEAR);
        return retorno;
    }

    public static Date getDate(int ano, int mes, int dia) {

        Calendar data = new GregorianCalendar();
        data.set(ano, mes, dia);

        return data.getTime();
    }
    
}
