package co.edu.udea.iw.dto;

/**
 * Es la clase que representa la entidad Solicitud. DTO
 * @author Jhonatan Orozco Blandon
 * @version 1
 * @category DTO
 *
 */

public class Solicitud {
	
	private SolicitudId id;
	private int estadoSolicitud;
	private String motivo;
	private String respuesta;
	
	/**
	 * Retorna la clave primaria compuesta de Solicitud
	 * @return
	 */
	public SolicitudId getId() {
		return id;
	}
	
	/**
	 * Modifica la clave primaria compuesta de Solicitud por el objeto SolicitudId id
	 * id 
	 * @param id
	 */
	public void setId(SolicitudId id) {
		this.id = id;
	}
	
	/**
	 * Retorna el estado de la solicitud
	 * @return
	 */
	public int getEstadoSolicitud() {
		return estadoSolicitud;
	}
	/**
	 * Modifica el estado de la solicitud
	 * @param estadoSolicitud
	 */
	public void setEstadoSolicitud(int estadoSolicitud) {
		this.estadoSolicitud = estadoSolicitud;
	}
	
	/**
	 * Retorna el motivo por el que se solicita el prestamo
	 * @return
	 */
	public String getMotivo() {
		return motivo;
	}
	
	/**
	 * Modifica el motivo de la solicitud
	 * @param motivo
	 */
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	
	/**
	 * Retorna la respuesta de la solicitud
	 * @return
	 */
	public String getRespuesta() {
		return respuesta;
	}
	
	/**
	 * Modifica la respuesta de la solicitud
	 * @param respuesta
	 */
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
	
}
