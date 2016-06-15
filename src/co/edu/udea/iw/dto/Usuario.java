package co.edu.udea.iw.dto;

/**
 * Clase dto para transportar los datos de un Usuario. Mapea las columnas de la tabla Usuario
 * @author Santiago Gómez Giraldo
 * @version 1
 * @category DTO
 */
public class Usuario {
	/**
	 * Correo del usuario
	 */
	private String correo;
	/**
	 * Cedula del usuario
	 */
	private String cedula;
	/**
	 * Nombre del usuario
	 */
	private String nombre;
	/**
	 * Apedllido del usuario
	 */
	private String apellido;
	/**
	 * Contraseña del usuario
	 */
	private String contrasena;
	/**
	 * Rol del usuario, este puede ser Administrador, Empleado o Usuario
	 */
	private Rol rol;
	
	/**
	 * Getters y Setters de usuario
	 */
	
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public Rol getRol() {
		return rol;
	}
	public void setRol(Rol rol) {
		this.rol = rol;
	}

}
