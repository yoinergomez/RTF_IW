package co.edu.udea.iw.dao;

import java.util.List;

import co.edu.udea.iw.dto.Usuario;
import co.edu.udea.iw.exception.DaoException;
/**
 * Por medio de esta interfaz se quiere acceder al DataSource
 * para poder manipular los datos de la entidad Usuario
 * @author Santiago Gómez Giraldo
 * @version 1
 * @category DAO
 */
public interface UsuarioDAO {
	
	/**
	 * Busca en la entidad Usuario un registro asociado con el id
	 * enviado por parametro, en caso de ser así retorne un objeto Usuario
	 * asociado a ese correo.
	 * @param correo
	 * @return
	 * @throws DaoException
	 */
	public Usuario obtener(String correo)throws DaoException;
	
	/**
	 *Recibe un objeto Usuario y lo inserta en la base de datos 
	 * @param usuario
	 * @throws DaoException
	 */
	public void insertar (Usuario usuario) throws DaoException;
	
	/**
	 * Recibe un objeto Usuario, verifica que el objeto tenga un 
	 * registro asociado en la entidad Usuario, en caso de ser asi modifica 
	 * todo el registro con la informacion del objeto Solicitud
	 * @param usuario
	 * @throws DaoException
	 */
	public void modificar (Usuario usuario) throws DaoException;
	
	/**
	 * Recibe un objeto Usuario, verifica que el objeto Usuario coincida
	 * con un registro en la entidad Usuario,en caso de ser asi elimina
	 * el registro
	 * @param usuario
	 * @throws DaoException
	 */
	public void eliminar (Usuario usuario) throws DaoException;
	
	/**
	 * Retorna una lista con todos los registros de la entidad Usuario
	 * de la base de datos
	 * @return
	 * @throws DaoException
	 */
	public List<Usuario> obtener() throws DaoException;

}
