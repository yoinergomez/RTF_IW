package co.edu.udea.iw.dao.impl;
import static org.junit.Assert.*;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.iw.dao.DispositivoDAO;
import co.edu.udea.iw.dto.Dispositivo;
import co.edu.udea.iw.exception.IWDaoException;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(locations = "classpath:configSpring.xml")
public class DispositivoDAOImplTest {

	@Autowired
	DispositivoDAO dispositivoDAO;
	
	/**
	 * Obtiene y lista todos los dispositivos 
	 * que se encuentran almacenados en la base de datos
	 */
	public void obtenerDispositivos() {
		List<Dispositivo> dispositivos = null;
		
		try{
			dispositivos = dispositivoDAO.obtener();
			
			for(Dispositivo dispositivo : dispositivos){
				System.out.println("Nombre ciudad: " + dispositivo.getNombre());
			}
			
			assertTrue(true);
		}catch(IWDaoException e){
			fail(e.getMessage());
		}
	}
	
	/**
	 * Inserta un nuevo dispositivo en la base de datos
	 */
	@Test
	public void insertarDispositivo(){
		
		try{
			Dispositivo dispositivo = null;
			dispositivo = new Dispositivo();
			
			dispositivo.setCodigo("123456");
			dispositivo.setNombre("Movil");
			dispositivo.setMarca("SONY");
			
			dispositivoDAO.insertar(dispositivo);
			
			assertTrue(true);
		}catch(IWDaoException e){
			fail(e.getMessage());
		}
	}

}
