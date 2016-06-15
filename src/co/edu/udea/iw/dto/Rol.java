package co.edu.udea.iw.dto;

/**
 * Clase dto para transportar los datos de un Rol. Mapea las columnas de la tabla Rol
 * @author Santiago Gómez Giraldo
 * @version 1
 * @category DTO
 */
public class Rol {
	/**
	 * Identificador del rol del usuario
	 * 1) Administrador
	 * 2) Empleado
	 * 3) Usuario
	 */
	private Integer id;
	
	/**
	 * Nombre del rol, puede ser Administrador, Empleado o Usuario
	 */
	private String nombre;
	
	/**
	 * Getters y Setters de rol
	 */
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
