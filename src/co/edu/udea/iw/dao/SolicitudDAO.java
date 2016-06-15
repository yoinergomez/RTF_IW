package co.edu.udea.iw.dao;

import java.util.List;

import co.edu.udea.iw.dto.Dispositivo;
import co.edu.udea.iw.dto.Solicitud;
import co.edu.udea.iw.dto.SolicitudId;
import co.edu.udea.iw.dto.Usuario;
import co.edu.udea.iw.exception.DaoException;

/**
 * Por medio de esta interfaz se quiere acceder al DataSource
 * para poder manipular los datos de la entidad Solicitud
 * @author Jhonatan Orozco Blandon
 * @version 1
 * @category DAO
 */

public interface SolicitudDAO {
	/**
	 * Recibe un objeto Solicitud y lo inserta en la base de datos
	 * @param solicitud
	 * @throws DaoException
	 */
	public void insertar(Solicitud solicitud) throws DaoException;
	
	/**
	 * Retorna una lista con todos los registros de la entidad Solicitud
	 * de la base de datos
	 * @return
	 * @throws DaoException
	 */
	public List<Solicitud> obtenerTodas() throws DaoException;
	
	/**
	 * Recibe un objeto Solicitud, verifica que el objeto tenga un 
	 * registro asociado en la entidad Solicitud, en caso de ser asi modifica 
	 * todo el registro con la información del objeto Solicitud
	 * @param solicitud 
	 * @throws DaoException
	 */
	public void modificar(Solicitud solicitud) throws DaoException;
	
	/**
	 * Recibe un objeto Solicitud, verifica que el objeto Solicitud coincida
	 * con un registro en la entidad Solicitud,en caso de ser así elimina
	 * el registro
	 * @param solicitud
	 * @throws DaoException
	 */
	public void eliminar(Solicitud solicitud) throws DaoException;
	
	/**
	 * Busca en la entidad Solicitud un registro asociado con el id
	 * enviado por parametro, en caso de ser así retorne un objeto Solicitud
	 * asociado al id.
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public Solicitud obtenerSolicitud(SolicitudId id) throws DaoException;
	
	/**
	 * Busca en la entidad Solicitud los registros que estan asociados a un 
	 * usuario que es enviado por parametro.Además si se quiere obtener todas 
	 * las solicitudes asociadas al usuario el parametro estadoSolicitud debe ser<0.
	 * En caso de que se quiera obtener las solicitudes aprobadas de un usuario 
	 * el parametro estadoSolicitud debe ser igual a 1.
	 *  En caso de que se quiera obtener las solicitudes en prestamo de un usuario 
	 * el parametro estadoSolicitud debe ser igual a 2.
	 *  En caso de que se quiera obtener las solicitudes entregadas de un usuario 
	 * el parametro estadoSolicitud debe ser igual a 3.
	 * @param usuario
	 * @param estadoSolicitud
	 * @return
	 * @throws DaoException
	 */
	public List<Solicitud> obtenerSolicitudesDeUnUsuario(Usuario usuario, int estadoSolicitud) throws DaoException;
	
	/**
	 * Busca en la entidad Solicitud los registros que estan asociados a un 
	 * dispositivo que es enviado por parametro.Además solo se obtienen las solicitudes
	 * que ya fueron aprobadas por el sistema.
	 * @param dispositivo
	 * @return
	 * @throws DaoException
	 */
	public List<Solicitud> obtenerSolicitudesAprobadasDeUnDispositivo(Dispositivo dispositivo) throws DaoException;
	
}

