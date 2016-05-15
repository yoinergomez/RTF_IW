package co.edu.udea.iw.dao;

import co.edu.udea.iw.dto.Rol;
import co.edu.udea.iw.exception.DaoException;
/**
 * Por medio de esta interfaz se quiere acceder al DataSource
 * para poder manipular los datos de la entidad Usuario
 * @author Santiago Gómez Giraldo
 * @version 1
 * @category DAO
 */
public interface RolDAO {
	/**
	 * Busca en la entidad Rol un registro asociado con el id
	 * enviado por parametro, en caso de ser así retorne un objeto Rol
	 * asociado a ese id.
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public Rol obtener (Integer id)throws DaoException;
	
}
