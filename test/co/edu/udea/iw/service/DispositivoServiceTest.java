package co.edu.udea.iw.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.iw.dto.Dispositivo;
import co.edu.udea.iw.exception.DaoException;
import co.edu.udea.iw.exception.ServiceException;

/**
 * Clase de prueba los metodos del servicio DispositivoService
 * @author Yoiner Gomez yoiner.gomez22@gmail.com
 * @version 1
 * @category TEST
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(locations = "file:src/configuracion.xml")
public class DispositivoServiceTest {
	
	@Autowired
	DispositivoService dispositivoService;
	
	/**
	 * Test para insertar un dispositivo en la base de datos 
	 */
	@Test
	public void testInsertarDispositivo() {
		Dispositivo dispositivo = new Dispositivo();
		int codigo = 1;
		String nombre = "Mouse";
		String marca = "Genius";
		String caracteristica = "Optico";
		try{
			dispositivoService.guardarDispositivo(codigo, nombre, marca, caracteristica);
		}catch(ServiceException e){
			new ServiceException(e.getMessage());
			fail(e.getMessage());
		} catch (DaoException e) {
			new ServiceException(e.getMessage());
			fail(e.getMessage());
		}
	}
	
	/**
	 * Test para modificar un dispositivo en la base de datos 
	 */
	//@Test
	public void testModificarDispositivo() {
		int codigo = 1;
		String nombre = "Mouse";
		String marca = "Genius";
		String caracteristica = "Optico";
		try{
			dispositivoService.modificarDispositivo(codigo, nombre, marca, caracteristica);
		}catch(ServiceException e){
			new ServiceException(e.getMessage());
			fail(e.getMessage());
		} catch (DaoException e) {
			new ServiceException(e.getMessage());
			fail(e.getMessage());
		}
	}
	
	/**
	 * Test para eliminar un dispositivo en la base de datos 
	 */
	//@Test
	public void testEliminarDispositivo() {
		int codigo = 1;
		try{
			dispositivoService.eliminarDispositivo(codigo);
		}catch(ServiceException e){
			new ServiceException(e.getMessage());
			fail(e.getMessage());
		} catch (DaoException e) {
			new ServiceException(e.getMessage());
			fail(e.getMessage());
		}
	}
	
	/**
	 * Test para eliminar un dispositivo en la base de datos 
	 */
	//@Test
	public void testBuscarDispositivo() {
		int codigo = 1;
		try{
			dispositivoService.buscarDispositivo(codigo);
		}catch(ServiceException e){
			new ServiceException(e.getMessage());
			fail(e.getMessage());
		} catch (DaoException e) {
			new ServiceException(e.getMessage());
			fail(e.getMessage());
		}
	}

}
