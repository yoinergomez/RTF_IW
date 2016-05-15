/**
 * 
 */
package co.edu.udea.iw.dao;

import java.util.List;

import co.edu.udea.iw.dto.Dispositivo;
import co.edu.udea.iw.exception.IWDaoException;

/**
 * Interface que define los metodos que va a proveer el dao de Dispositivo
 * @author Yoiner Gomez yoiner.gomez22@gmail.com
 * @version 1
 * @category DAO
 */
public interface DispositivoDAO {

	/**
	 * Crea un nuevo dispositivo en el sistema
	 * @param dispositivo instancia del dispositivo a crear
	 * @return dispositivo insertado
	 * @throws IWDaoException cuando ocurre cualquier error en la comunicacion con la BD
	 */
	public Dispositivo insertar(Dispositivo dispositivo) throws IWDaoException;
	
	/**
	 * Modifica la informacion de un dispositivo en el sistema
	 * @param dispositivo instancia del dispositivo con los datos a modificar
	 * @return dispositivo modificado
	 * @throws IWDaoException cuando ocurre cualquier error en la comunicacion con la BD
	 */
	public Dispositivo modificar(Dispositivo dispositivo) throws IWDaoException;
	
	
	/**
	 * Entrega la lista de dispositivos activos en el sistema
	 * @return lista de dispositivos
	 * @throws IWDaoException cuando ocurre cualquier error en la comunicacion con la BD
	 */
	public List<Dispositivo> obtener() throws IWDaoException;
	
	/**
	 * Entrega la informacion de un dispositivo dado su codigo
	 * @param codigo identificacion del dispositivo
	 * @return instancia con los datos del dispositivo
	 * @throws IWDaoException cuando ocurre cualquier error en la comunicacion con la BD
	 */
	public Dispositivo obtener(String codigo) throws IWDaoException;
	
	
}
