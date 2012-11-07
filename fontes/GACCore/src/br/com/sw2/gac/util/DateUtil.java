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
     * Nome: getAnoAtual Recupera o valor do atributo 'anoAtual'.
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
     * @return int onde, 1 quando date1 > date2, -1 quando date1 < date2 e 0 quando forem iguais.
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
        data.set(ano, mes - 1, dia);
        return data.getTime();
    }

    /**
     * Nome: getDia Retorna o dia do mês de um objeto java.util.date informado.
     * @param data the data
     * @return valor do atributo 'dia'
     * @see
     */
    public static int getDia(Date data) {
        int retorno = 0;
        if (null != data) {
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(data);
            retorno = calendar.get(Calendar.DAY_OF_MONTH);
        }
        return retorno;
    }

    /**
     * Nome: getDia getDia Retorna o dia do mês de um objeto java.util.date informado.
     * @param object the object
     * @return valor do atributo 'dia'
     * @see
     */
    public static int getDia(Object object) {
        int retorno = 0;
        if (null != object && object instanceof Date) {
            retorno = getDia((Date) object);
        }
        return retorno;
    }

    /**
     * Nome: getPrimeiroDiaMes Retorna um objeto java.util.Date contendo o primeiro dia do mês
     * informado através de um java.util.date.
     * @param data the data
     * @return valor do atributo 'primeiroDiaMes'
     * @see
     */
    public static Date getPrimeiroDiaMes(Date data) {
        Date retorno = null;
        if (null != data) {
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(data);
            retorno = getDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1,
                calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        }
        return retorno;
    }

    /**
     * Nome: getUltimoDiaMes Retorna um objeto java.util.Date contendo o ultimo dia do mês informado
     * através de um java.util.date.
     * @param data the data
     * @return valor do atributo 'primeiroDiaMes'
     * @see
     */
    public static Date getUltimoDiaMes(Date data) {
        Date retorno = null;
        if (null != data) {
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(data);
            retorno = getDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1,
                calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        }
        return retorno;
    }

    /**
     * Nome: isHorario Verifica se uma string informada reflete um horário valido entre 00:00 e
     * 23:59.
     * @param horario the horario
     * @return true, se for horario senão retorna false
     * @see
     */
    public static boolean isHorario(String horario) {
        final int tamanhoMinimo = 4;
        final int tamanhoMaximo = 5;
        final int horaMaxima = 23;
        final int minutoMaximo = 59;
        boolean valid = true;
        int hora = 0;
        int minuto = 0;
        if (horario.length() > tamanhoMinimo && horario.length() > tamanhoMaximo) {
            valid = false;
        } else {
            try {
                int doisPontos = horario.indexOf(":");
                hora = Integer.parseInt(horario.substring(0, doisPontos));
                minuto = Integer.parseInt(horario.substring(doisPontos + 1, horario.length()));

                if (hora < 0 || hora > horaMaxima) {
                    valid = false;
                }

                if (minuto < 0 || minuto > minutoMaximo) {
                    valid = false;
                }

            } catch (Exception e) {
                valid = false;
            }
        }
        return valid;
    }

    /**
     * Nome: string2Time Converte uma string no formato hh:mm em um objeto do tipo Calendar.
     * @param horario the horario
     * @return calendar
     * @see
     */
    public static Calendar stringToTime(String horario) {
        final int anoBase = 1970;
        int doisPontos = horario.indexOf(":");
        int hora = Integer.parseInt(horario.substring(0, doisPontos));
        int minuto = Integer.parseInt(horario.substring(doisPontos + 1, horario.length()));

        Calendar calendar = new GregorianCalendar();
        calendar.set(anoBase, 01, 01, 0, 0, 0);
        calendar.set(Calendar.AM_PM, Calendar.AM);

        calendar.set(Calendar.HOUR, hora);
        calendar.set(Calendar.MINUTE, minuto);
        calendar.set(Calendar.SECOND, minuto);
        return calendar;
    }

    /**
     * Nome: Recebe uma data e uma quantidade de dias e retorna uma nova data subtraindo a
     * quantidade de dias informada.
     * @param data the data
     * @param dias the dias
     * @return date
     * @see
     */
    public static Date subtrairDias(Date data, int dias) {

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(data);
        calendar.add(Calendar.DAY_OF_MONTH, (dias * -1));

        return calendar.getTime();

    }

    /**
     * Nome: Recebe uma data e uma quantidade de dias e retorna uma nova data adicionado a ela a
     * quantidade de dias informada.
     * @param data the data
     * @param dias the dias
     * @return date
     * @see
     */
    public static Date adicionarDias(Date data, int dias) {

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(data);
        calendar.add(Calendar.DAY_OF_MONTH, dias);
        return calendar.getTime();

    }


    /**
     * Nome: adicionarDias
     * Retorna um objeto java.util.Date com a adição da quantidade de dias iformado como parâmetro.
     *
     * @param dias the dias
     * @return date
     * @see
     */
    public static Date adicionarDias(int dias) {

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, dias);
        return calendar.getTime();

    }

}
