package br.com.sw2.gac.util;

import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateUtil {

	public static String dayWeek[] = { "Domingo", "Segunda-feira",
			"Terça-Feira", "Quarta-Feira", "Quinta-Feira", "Sexta-Feira",
			"Sábado" };
	
	public static String month[] = { "Janeiro", "Fevereiro",
		"Março", "Abril", "Maio", "Junho",
		"Julho", "Agosto", "Setembro", "Outubro", "Dezembro" };
	
	public static String getDataCompleta() {
		return getDataCompleta(new Date());
	}
	
	public static String getDataCompleta(Date date) {		
		String retorno;
		GregorianCalendar data = new GregorianCalendar();
		data.setTime(date);		
		retorno = dayWeek[data.get(Calendar.DAY_OF_WEEK)-1];
		
		retorno = retorno + ", "  + data.get(Calendar.DAY_OF_MONTH);
		retorno = retorno + " de "  + month[data.get(Calendar.MONTH)];
		retorno = retorno + " de "  + data.get(Calendar.YEAR);
		return retorno;
	}		
	
	public static void main(String args[]) {
		System.out.println(getDataCompleta());
	}
}
