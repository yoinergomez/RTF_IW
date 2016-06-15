package co.edu.udea.iw.dao.impl;
import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.iw.dao.DispositivoDAO;
import co.edu.udea.iw.dto.Dispositivo;
import co.edu.udea.iw.exception.DaoException;

/**
 * Clase de prueba los metodos del DAO DispositivoDAO
 * @author Yoiner Gomez yoiner.gomez22@gmail.com
 * @version 1
 * @category TEST
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(locations = "file:src/configuracion.xml")
public class DispositivoDAOImplTest {

	@Autowired
	DispositivoDAO dispositivoDAO;
	
	/**
	 * Obtiene y lista todos los dispositivos 
	 * que se encuentran almacenados en la base de datos
	 */
	@Test
	@Rollback(false)
	public void obtenerDispositivos() {
		List<Dispositivo> dispositivos = null;
		BasicConfigurator.configure();
		try{
			dispositivos = dispositivoDAO.obtener();
			System.out.println("aaa"+new Date());
			for(Dispositivo dispositivo : dispositivos){
				System.out.println("Nombre ciudad: " + dispositivo.getNombre());
			}
			
			assertTrue(true);
		}catch(DaoException e){
			fail(e.getMessage());
		}
	}
	
	/**
	 * Inserta un nuevo dispositivo en la base de datos
	 */
	public void insertarDispositivo(){
		
		try{
			Dispositivo dispositivo = null;
			dispositivo = new Dispositivo();
			
			dispositivo.setCodigo(123456);
			dispositivo.setNombre("Movil");
			dispositivo.setMarca("SONY");
			
			dispositivoDAO.insertar(dispositivo);
			
			assertTrue(true);
		}catch(DaoException e){
			fail(e.getMessage());
		}
	}
}
