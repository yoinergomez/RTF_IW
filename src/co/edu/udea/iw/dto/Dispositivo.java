/**
 * 
 */
package co.edu.udea.iw.dto;

/**
 * Permite la representacion de los datos 
 * de la tabla dispositivo que se encuentra en la BD
 * @author Yoiner Gomez yoiner.gomez22@gmail.com
 * @version 1
 * @category DTO
 */
public class Dispositivo {

	private String codigo;
	private String nombre;
	private String marca;
	private String caracteristica;
	
	
	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}
	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the marca
	 */
	public String getMarca() {
		return marca;
	}
	/**
	 * @param marca the marca to set
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}
	/**
	 * @return the caracteristica
	 */
	public String getCaracteristica() {
		return caracteristica;
	}
	/**
	 * @param caracteristica the caracteristica to set
	 */
	public void setCaracteristica(String caracteristica) {
		this.caracteristica = caracteristica;
	}

}
