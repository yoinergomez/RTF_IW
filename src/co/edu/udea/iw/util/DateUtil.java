package co.edu.udea.iw.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Clase de utileria que contiene funciones
 * para la validacion de fechas en el sistema
 * @author Yoiner Gomez yoiner.gomez22@gmail.com
 * @version 1
 * @category UTIL
 */
public class DateUtil {
	
	public static final SimpleDateFormat FORMATO_CONVENCIONAL = new SimpleDateFormat("dd-MM-yyyy HH:mm");
	public static final SimpleDateFormat FORMATO_FECHA = new SimpleDateFormat("dd-MM-yyyy");
	
	/**
	 * Verifica si desde la fecha ingresada hasta la fecha fin
	 * existe por lo menos 8 horas de diferencia
	 * @param date
	 * @return boolean
	 */
	public static boolean esMenor8Horas(Date dateInicio, Date dateFin){
		return (dateInicio.getTime()>=(dateFin.getTime()-horasToMilisegundos(8)));
	}
	
	/**
	 * Convierte un entero de N horas a milisegundos
	 * @param horas
	 * @return
	 */
	public static long horasToMilisegundos(int horas){
		return horas*60*60*1000;
	}
	
	/**
	 * Comprueba si la fecha ingresada esta entre el 
	 * horario laboral (lunes a sabados de 8:00 a 20:00)
	 * @param date
	 * @return
	 */
	public static boolean esDiaLaboral(Date date){
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		int diaSemana = cal.get(Calendar.DAY_OF_WEEK);
		int horaDia = cal.get(Calendar.HOUR_OF_DAY);
		int minutoDia = cal.get(Calendar.MINUTE);
		System.out.println(date);
		System.out.println(diaSemana);
		System.out.println(horaDia);
		System.out.println(minutoDia);
		if(diaSemana!=1){
			if(horaDia>8 && horaDia<20){
				return true;
			}
			if((horaDia==8 || horaDia==20) && minutoDia==0){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Compara la fecha ingresada con la fecha actual en el formato dd-MM-yyyy
	 * @param date
	 * @return int: -1 si date es menor, 0 si date es igual y 1 si date es mayor
	 * @throws ParseException 
	 */
	public static int compararConHoy(Date date) throws ParseException{
		Date fechaActual = new Date();
		date = FORMATO_FECHA.parse(date.toString());
		fechaActual = FORMATO_FECHA.parse(fechaActual.toString());
		return fechaActual.compareTo(date);	
	}

}
