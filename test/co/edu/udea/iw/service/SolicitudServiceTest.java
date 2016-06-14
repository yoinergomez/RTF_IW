package co.edu.udea.iw.service;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.iw.dao.DispositivoDAO;
import co.edu.udea.iw.dao.SolicitudDAO;
import co.edu.udea.iw.dao.UsuarioDAO;
import co.edu.udea.iw.dto.Dispositivo;
import co.edu.udea.iw.dto.Rol;
import co.edu.udea.iw.dto.Solicitud;
import co.edu.udea.iw.dto.SolicitudId;
import co.edu.udea.iw.dto.Usuario;
import co.edu.udea.iw.exception.DaoException;
import co.edu.udea.iw.exception.ServiceException;
/**
 * Clase de prueba para los m�todos de la clase SolicitudService.
 * Al ser una clase transaccional cualquier error que interumpa 
 * la normal ejecuci�n de un metodo de la clase,genera un rollback 
 * para preservar la integridad de la informaci�n de la base de datos.
 * @author Jhonatan Orozco Blandon
 * @version 1
 * @category test
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(locations = "file:src/configuracion.xml")
public class SolicitudServiceTest {

	@Autowired
	SolicitudService solicitudService;
	
	@Autowired
	SolicitudDAO solicitudDAO;
	
	@Autowired
	UsuarioDAO usuarioDAO;
	
	@Autowired
	DispositivoDAO dispositivoDAO;
	
	/**
	 * Prueba unitaria para el m�todo estaDisponibleDispositivo()
	 */
	//@Test
	public void testEstaDisponibleDispositivo() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy HH:mm:ss");
		Date fechaInicio=null;
		Date fechaFin=null;
		Usuario usuario=null;
		Dispositivo dispositivo=null;
		SolicitudId solicitudId=null;
		Solicitud solicitud=null;
		Boolean flag=null;
		try{
			usuario=new Usuario();
			usuario=usuarioDAO.obtener("jhonatorozco@gmail.com");
			dispositivo=dispositivoDAO.obtener(1);
			solicitudId=new SolicitudId();
			solicitudId.setDispositivo(dispositivo);
			solicitudId.setUsuario(usuario);
			fechaInicio=dateFormat.parse("01-01-2016 12:30:00");
			fechaFin=dateFormat.parse("01-01-2016 16:00:00");
			solicitudId.setFechaInicio(fechaInicio);
			solicitudId.setFechaFin(fechaFin);
			solicitud=new Solicitud();
			solicitud.setId(solicitudId);
			solicitud.setEstadoSolicitud(0);
			solicitud.setMotivo("es importante para mi");
			flag=solicitudService.estaDisponibleDispositivo(solicitud);
			System.out.println(flag);
		}catch(ServiceException e){
			new ServiceException(e.getMessage());
			fail(e.getMessage());
		} catch (DaoException e) {
			new ServiceException(e.getMessage());
			fail(e.getMessage());
		} catch (ParseException e) {
			new ServiceException(e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * Prueba unitaria para el m�todo estaUsuarioHabilitado()
	 */
	//@Test
	public void testEstaUsuarioHabilitado(){
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy HH:mm:ss");
		Date fechaInicio=null;
		Date fechaFin=null;
		Usuario usuario=null;
		Dispositivo dispositivo=null;
		SolicitudId solicitudId=null;
		Solicitud solicitud=null;
		Boolean flag=null;
		try{
			usuario=new Usuario();
			usuario=usuarioDAO.obtener("jhonatorozco@gmail.com");
			dispositivo=dispositivoDAO.obtener(3);
			solicitudId=new SolicitudId();
			solicitudId.setDispositivo(dispositivo);
			solicitudId.setUsuario(usuario);
			fechaInicio=dateFormat.parse("01-01-2016 12:30:00");
			fechaFin=dateFormat.parse("01-01-2016 16:00:00");
			solicitudId.setFechaInicio(fechaInicio);
			solicitudId.setFechaFin(fechaFin);
			solicitud=new Solicitud();
			solicitud.setId(solicitudId);
			solicitud.setEstadoSolicitud(0);
			solicitud.setMotivo("es importante para mi");
			flag=solicitudService.estaUsuarioHabilitado(solicitud);
			System.out.println(flag);
		} catch(ServiceException e){
			new ServiceException(e.getMessage());
			fail(e.getMessage());
		} catch (DaoException e) {
			new ServiceException(e.getMessage());
			fail(e.getMessage());
		} catch (ParseException e) {
			new ServiceException(e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * Prueba unitaria para el m�todo registrarSolicitud()
	 */
	//@Test
	@Rollback(false)
	public void testRegistrarSolicitud() throws ServiceException{
		try{
			solicitudService.registrarSolicitud("santiago@udea", 1, 
					"15-03-2016 12:10:00", "15-03-2016 12:50:00", "Para una investigacion");
		} catch(ServiceException e){
			new ServiceException(e.getMessage());
			fail(e.getMessage());
		} catch (DaoException e) {
			new ServiceException(e.getMessage());
			fail(e.getMessage());
		} catch (ParseException e) {
			new ServiceException(e.getMessage());
			e.printStackTrace();
		}
		
	}
	/**
	 * Prueba unitaria para el m�todo listarSolicitudesDeUnUsuario()
	 */
	//@Test
	public void testListarSolicitudesDeUnUsuario() throws ServiceException {
		List<Solicitud> solicitudes=null; 
		try {
			solicitudes=solicitudService.listarSolicitudesDeUnUsuario("jhonatoroo@gmail.com");
			for(Solicitud solicitud:solicitudes){
				System.out.println("Motivo: "+solicitud.getMotivo());
			}
		}  catch(ServiceException e){
			new ServiceException(e.getMessage());
			fail(e.getMessage());
		} catch (DaoException e) {
			new ServiceException(e.getMessage());
			fail(e.getMessage());
		}
		
	}
	
	/**
	 * Prueba unitaria para el metodo obtener todos los dispositivos disponibles
	 * @throws DaoException 
	 */
	@Test
	public void testObtenerDispositivosDisponibles() {
		List<Dispositivo> dispositivos=null; 
		try {
			dispositivos = solicitudService.obtenerDispositivosDisponibles();
			for(Dispositivo dispositivo:dispositivos){
				System.out.println("Motivo: "+dispositivo.getNombre());
			}
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
