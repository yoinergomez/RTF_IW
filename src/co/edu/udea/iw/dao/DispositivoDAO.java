package co.edu.udea.iw.dao;

import java.util.List;

import co.edu.udea.iw.dto.Dispositivo;
import co.edu.udea.iw.exception.DaoException;

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
	 * @throws DaoException cuando ocurre cualquier error en la comunicacion con la BD
	 */
	public Dispositivo insertar(Dispositivo dispositivo) throws DaoException;
	
	/**
	 * Modifica la informacion de un dispositivo en el sistema
	 * @param dispositivo instancia del dispositivo con los datos a modificar
	 * @return dispositivo modificado
	 * @throws DaoException cuando ocurre cualquier error en la comunicacion con la BD
	 */
	public Dispositivo modificar(Dispositivo dispositivo) throws DaoException;
	
	
	/**
	 * Entrega la lista de todos los dispositivos en el sistema
	 * @return lista de dispositivos
	 * @throws DaoException cuando ocurre cualquier error en la comunicacion con la BD
	 */
	public List<Dispositivo> obtener() throws DaoException;
	
	/**
	 * Entrega la informacion de un dispositivo dado su codigo
	 * @param codigo identificacion del dispositivo
	 * @return instancia con los datos del dispositivo
	 * @throws DaoException cuando ocurre cualquier error en la comunicacion con la BD
	 */
	public Dispositivo obtener(Integer codigo) throws DaoException;
	
	/**
	 * Elimina el dispositivo que tenga el codigo enviado en el parametro
	 * @param codigo
	 * @return Dispositivo eliminado
	 * @throws DaoException
	 */
	public Dispositivo eliminar(Integer codigo) throws DaoException;
	
	
}
