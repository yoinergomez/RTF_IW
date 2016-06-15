package co.edu.udea.iw.dto;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Clase dto para transportar los datos de un dispositivo. Mapea las columnas de la tabla dispositivo
 * @author Yoiner Gomez yoiner.gomez22@gmail.com
 * @version 1
 * @category DTO
 */
@XmlRootElement
public class Dispositivo {
	
	private Integer codigo;
	private String nombre;
	private String marca;
	private String caracteristicas;
	
	
	/**
	 * @return the codigo
	 */
	public Integer getCodigo() {
		return codigo;
	}
	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(Integer codigo) {
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
	 * @return the caracteristicas
	 */
	public String getCaracteristicas() {
		return caracteristicas;
	}
	/**
	 * @param caracteristicas the caracteristicas to set
	 */
	public void setCaracteristicas(String caracteristicas) {
		this.caracteristicas = caracteristicas;
	}
	
	
	
}
