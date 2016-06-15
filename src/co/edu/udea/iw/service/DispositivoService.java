package co.edu.udea.iw.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.iw.dao.DispositivoDAO;
import co.edu.udea.iw.dto.Dispositivo;
import co.edu.udea.iw.exception.DaoException;
import co.edu.udea.iw.exception.ServiceException;
import co.edu.udea.iw.util.validations.Validaciones;

/**
 * Clase transaccional que contiene la logica del negocio
 * para la tabla dispositivo de la base de datos
 * @author Yoiner Gomez yoiner.gomez22@gmail.com
 * @version 1
 * @category BL
 */
@Transactional
public class DispositivoService {

	private DispositivoDAO dispositivoDAO;
	
	/**
	 * Guarda un dispositivo en la base de datos con 
	 * la informacion enviada en los parametros
	 * @param codigo
	 * @param nombre
	 * @param marca
	 * @param caracteristica
	 * @return
	 * @throws ServiceException
	 * @throws DaoException
	 */
	public Dispositivo guardarDispositivo(int codigo, String nombre, 
			String marca, String caracteristica) throws ServiceException, DaoException{
		
		Dispositivo dispositivo = null;
		
		if(Validaciones.isTextoVacio(nombre)){
			throw new ServiceException("El nombre del dispositivo no puede ser nulo, ni una cadena de caracteres vacia");
		}
		if(Validaciones.isTextoVacio(marca)){
			throw new ServiceException("La marca del dispositivo no puede ser nulo, ni una cadena de caracteres vacia");
		}
		
		if(dispositivoDAO.obtener(codigo)!=null){
			throw new DaoException("Ya existe un dispositivo con codigo" + codigo +  " en el sistema");
		}
		
		dispositivo = new Dispositivo();
		dispositivo.setCodigo(codigo);
		dispositivo.setNombre(nombre);
		dispositivo.setMarca(marca);
		dispositivo.setCaracteristicas(caracteristica);
		
		return dispositivoDAO.insertar(dispositivo);
	}
	
	/**
	 * Modifica un dispositivo en la base de datos con 
	 * la informacion enviada en los parametros
	 * @param codigo
	 * @param nombre
	 * @param marca
	 * @param caracteristica
	 * @return
	 * @throws ServiceException
	 * @throws DaoException
	 */
	public Dispositivo modificarDispositivo(int codigo, String nombre, 
			String marca, String caracteristica) throws ServiceException, DaoException{
		
		Dispositivo dispositivo = null;
		
		if(Validaciones.isTextoVacio(nombre)){
			throw new ServiceException("El nombre del dispositivo no puede ser nulo, ni una cadena de caracteres vacia");
		}
		if(Validaciones.isTextoVacio(marca)){
			throw new ServiceException("La marca del dispositivo no puede ser nulo, ni una cadena de caracteres vacia");
		}
		
		dispositivo = dispositivoDAO.obtener(codigo);
		if(dispositivo==null){
			throw new ServiceException("El dispositivo "+codigo+ " a modificar no existe en el sistema");
		}
		
		dispositivo.setCodigo(codigo);
		dispositivo.setNombre(nombre);
		dispositivo.setMarca(marca);
		dispositivo.setCaracteristicas(caracteristica);
		
		return dispositivoDAO.modificar(dispositivo);
	
	}
	
	/**
	 * Elimina un dispositivo en la base de datos dado
	 * el codigo enviado en el parametro
	 * @param codigo
	 * @return
	 * @throws ServiceException
	 * @throws DaoException
	 */
	public Dispositivo eliminarDispositivo(int codigo) 
			throws ServiceException, DaoException{
		
		Dispositivo dispositivo = null;
		
		dispositivo = dispositivoDAO.obtener(codigo);
		if(dispositivo==null){
			throw new ServiceException("El dispositivo "+codigo+ " a eliminar no existe en el sistema");
		}
		
		return dispositivoDAO.eliminar(codigo);
	
	}
	
	/**
	 * Busca un dispositivo en la base de datos dado
	 * el codigo enviado en el parametro
	 * @param codigo
	 * @return
	 * @throws ServiceException
	 * @throws DaoException
	 */
	public Dispositivo buscarDispositivo(int codigo) 
			throws ServiceException, DaoException{
		
		Dispositivo dispositivo = null;
		
		dispositivo = dispositivoDAO.obtener(codigo);
		if(dispositivo==null){
			throw new ServiceException("El dispositivo "+codigo+ " a buscar no existe en el sistema");
		}
		
		return dispositivo;
	}
	
	/**
	 * Obtiene todos los dispositivos que se encuentran
	 * almacenados en la base de datos.
	 * @return
	 * @throws DaoException
	 */
	public List<Dispositivo> obtenerTodos() throws DaoException{
		List<Dispositivo> dispositivos = null;
		dispositivos = dispositivoDAO.obtener();
		return dispositivos;
	}
	
	/**
	 * @return the dispositivoDAO
	 */
	public DispositivoDAO getDispositivoDAO() {
		return dispositivoDAO;
	}

	/**
	 * @param dispositivoDAO the dispositivoDAO to set
	 */
	public void setDispositivoDAO(DispositivoDAO dispositivoDAO) {
		this.dispositivoDAO = dispositivoDAO;
	}
	
	
}
