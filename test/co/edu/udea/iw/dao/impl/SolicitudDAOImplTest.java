package co.edu.udea.iw.dao.impl;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.iw.dao.DispositivoDAO;
import co.edu.udea.iw.dao.RolDAO;
import co.edu.udea.iw.dao.SolicitudDAO;
import co.edu.udea.iw.dao.UsuarioDAO;
import co.edu.udea.iw.dto.Dispositivo;
import co.edu.udea.iw.dto.Rol;
import co.edu.udea.iw.dto.Solicitud;
import co.edu.udea.iw.dto.SolicitudId;
import co.edu.udea.iw.dto.Usuario;
import co.edu.udea.iw.exception.DaoException;

/**
 * Clase de prueba para los métodos de la clase SolicitudDAOImpl.
 * Al ser una clase transaccional cualquier error que interumpa 
 * la normal ejecución de un metodo de la clase,genera un rollback 
 * para preservar la integridad de la información de la base de datos.
 * @author Jhonatan Orozco Blandon
 * @version 1
 * @category test
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(locations = "file:src/configuracion.xml")
public class SolicitudDAOImplTest {

	@Autowired
	UsuarioDAO usuarioDAO;
	
	@Autowired
	SolicitudDAO solicitudDAO;
	
	@Autowired
	DispositivoDAO dispositivoDAO;
	
	
	//@Test
	public void testInsertar() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		Date fechaInicio=null;
		Date fechaFin=null;
		Usuario usuario=null;
		Dispositivo dispositivo=null;
		SolicitudId solicitudId=null;
		Solicitud solicitud=null;
		Rol rol=null;
		try {
			usuario=new Usuario();
			usuario=usuarioDAO.obtener("santiago@udea");
			dispositivo=dispositivoDAO.obtener(1);
			solicitudId=new SolicitudId();
			solicitudId.setDispositivo(dispositivo);
			solicitudId.setUsuario(usuario);
			fechaInicio=dateFormat.parse("10-05-2016 00:00:00");
			fechaFin=dateFormat.parse("11-05-2016 09:10:00");
			solicitudId.setFechaInicio(fechaInicio);
			solicitudId.setFechaFin(fechaFin);
			solicitud=new Solicitud();
			solicitud.setId(solicitudId);
			solicitud.setEstadoSolicitud(2);
			solicitud.setMotivo("es importante para mi");
			solicitudDAO.insertar(solicitud);
			System.out.println("exito");
		} catch (DaoException e ) {
			e.printStackTrace();
		} catch(ParseException e){
			e.getMessage();
		} 
		
	}
	 

	
	@Test
	public void testObtenerTodas() {
		List<Solicitud> solicitudes=null;
		try{
			solicitudes=solicitudDAO.obtenerTodas();
			for(Solicitud solicitud:solicitudes){
				System.out.println(solicitud.getEstadoSolicitud());
				
			}
			
		}catch(DaoException e){
			fail(e.getMessage());
		} 
		
	}
	
	
		 
	 

	@Test
	public void testModificar() {
		fail("Not yet implemented");
	}
	
	
	


	@Test
	public void testEliminar() {
		

	}
	


	@Test
	public void testObtenerSolicitud() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy HH:mm:ss");
		Date fechaInicio=null;
		Date fechaFin=null;
		Usuario usuario=null;
		Dispositivo dispositivo=null;
		SolicitudId solicitudId=null;
		Solicitud solicitud=null;
		Rol rol=null;
		try {
			usuario=new Usuario();
			usuario=usuarioDAO.obtener("santiago@udea");
			dispositivo=new Dispositivo();
			dispositivo.setCodigo(1);
			dispositivo.setNombre("Laptop");
			dispositivo.setMarca("Lenovo");
			dispositivo.setCaracteristicas("Intel centrino");
			solicitudId=new SolicitudId();
			solicitudId.setDispositivo(dispositivo);
			solicitudId.setUsuario(usuario);
			fechaInicio=dateFormat.parse("15-03-2016 00:00:00");
			fechaFin=dateFormat.parse("04-05-2016 00:00:00");
			solicitudId.setFechaInicio(fechaInicio);
			solicitudId.setFechaFin(fechaFin);
			solicitud=solicitudDAO.obtenerSolicitud(solicitudId);
			System.out.println(solicitud.getMotivo());
		} catch (DaoException e ) {
			e.printStackTrace();
		} catch(ParseException e){
			e.getMessage();
		} 
		
		
		
	}
	
	
	@Test
	public void testObtenerSolicitudDeUnUsuario() {
		fail("Not yet implemented");
	}

	
	@Test
	public void obtenerSolicitudesUsuario(){
		List<Solicitud> solicitudes=null;
		Usuario usuario=null;
		try{
			usuario=new Usuario();
			usuario=usuarioDAO.obtener("santiago@udea");
			solicitudes=solicitudDAO.obtenerSolicitudesDeUnUsuario(usuario, -1);
		
			for(Solicitud solicitud:solicitudes){
				System.out.println(solicitud.getMotivo());
			}
		}catch(DaoException e){
			fail(e.getMessage());
		}
	}
}
