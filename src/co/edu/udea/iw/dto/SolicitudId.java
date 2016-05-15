package co.edu.udea.iw.dto;

import java.util.Date;
import java.io.Serializable;

/**
 * Esta clase representa la clave compuesta que tiene la clave solicitud.
 * @author Jhonatan Orozco Blandon
 * @version 1
 * @category DTO
 *
 */
public class SolicitudId implements Serializable {
	
	private Usuario usuario;
	private Dispositivo dispositivo;
	private Date fechaInicio;
	private Date fechaFin;
	
	/**
	 * Devuelve el objeto usuario asociado a la solicitud
	 * @return
	 */
	public Usuario getUsuario() {
		return usuario;
	}
	
	/**
	 * Modifica el objeto usuario por el nuevo objeto que entra 
	 * por parametro
	 * @param usuario
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	/**
	 * Devuelve el objeto dispositivo asociado a la solicitud
	 * @return
	 */
	public Dispositivo getDispositivo() {
		return dispositivo;
	}
	
	/**
	 * Modifica el objeto dispositivo por el nuevo objeto que entra 
	 * por parametro
	 * @param dispositivo
	 */
	public void setDispositivo(Dispositivo dispositivo) {
		this.dispositivo = dispositivo;
	}
	
	/**
	 * Devuelve la fecha incial de la solicitud
	 * @return
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}
	
	/**
	 * Modifica la fecha inicial por la que entra por parametro
	 * @param fechaInicio
	 */
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	
	/**
	 * Devuelve la fecha final de la solicitud
	 * @return
	 */
	public Date getFechaFin() {
		return fechaFin;
	}
	
	/**
	 * Modifica la fecha final por la que entra por parametro
	 * @param fechaFin
	 */
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	
}
