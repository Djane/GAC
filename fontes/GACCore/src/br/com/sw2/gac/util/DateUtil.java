package br.com.sw2.gac.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * <b>Descrição: Classe utilitária para manipulação de datas.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public abstract class DateUtil {

    /**
     * Nome: getDataAtual Recupera o valor do atributo 'dataAtual'.
     * @return valor do atributo 'dataAtual'
     * @see
     */
    public static Date getDataAtual() {
        return new Date();
    }

    /**
     * Nome: getAnoAtual Recupera o valor do ano corrente.
     * @return valor do atributo 'anoAtual'
     * @see
     */
    public static int getAnoAtual() {
        Calendar data = new GregorianCalendar();
        return data.get(Calendar.YEAR);
    }

    /**
     * Nome: compareIgnoreTime Compara duas datas ignorando a hora, minuto e segundo.
     * @param date1 the date1
     * @param date2 the date2
     * @return int
     * @see
     */
    public static int compareIgnoreTime(Date date1, Date date2) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyMMdd");

        int retorno = 0;
        int strd1 = Integer.parseInt(sdf.format(date1));
        int strd2 = Integer.parseInt(sdf.format(date2));

        if (strd1 > strd2) {
            retorno = 1;
        } else if (strd1 < strd2) {
            retorno = -1;
        }

        return retorno;
    }

    /**
     * Nome: Retorna um objeto java.util.date com as valores informados.
     * @param ano the ano
     * @param mes the mes
     * @param dia the dia
     * @return valor do atributo 'date'
     * @see
     */
    public static Date getDate(int ano, int mes, int dia) {

        Calendar data = new GregorianCalendar();
        data.set(ano, mes, dia);

        return data.getTime();
    }
}
