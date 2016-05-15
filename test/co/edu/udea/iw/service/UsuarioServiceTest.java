package co.edu.udea.iw.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.iw.dto.Usuario;
import co.edu.udea.iw.exception.DaoException;
import co.edu.udea.iw.exception.ServiceException;
/**
 * Test de UsuarioService
 * @author Santiago G�mez Giraldo
 * @version 1
 * @category Test
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(locations = "classpath:configuracion.xml")
public class UsuarioServiceTest {
	
	@Autowired
	UsuarioService usuarioService;

	/**
	 * Test de para autenticarUsuario
	 */
	@Test
	public void testValidar(){
		boolean isValido=false;
		try{
			isValido=usuarioService.autenticarUsuario("santiago@udea", "123456");
			System.out.println(isValido);
		}catch(ServiceException e){
			new ServiceException(e.getMessage());
			fail(e.getMessage());
		} catch (DaoException e) {
			new ServiceException(e.getMessage());
			fail(e.getMessage());
		}	
	}
	
	/**
	 * Test de para registartUsuario
	 */
	//@Test
	@Rollback(false)
	public void testRegistrar() throws NumberFormatException, Exception{
		try{
			usuarioService.registrarUsuario("jhonatan@udea", "213456", "Jhonatan", "Orozco", "1346", "3");
		}catch(ServiceException e){
			new ServiceException(e.getMessage());
			fail(e.getMessage());
		} catch (DaoException e) {
			new ServiceException(e.getMessage());
			fail(e.getMessage());
		}

	}
	
	/**
	 * Test de para buscarUsuario
	 */
//	@Test
	public void testBuscarUsuario() throws ServiceException {
		Usuario usuario=null;
		try{
			usuario=usuarioService.buscarUsuario("asdadsa@udea");
			if(usuario!=null){
				System.out.println(usuario.getNombre());
			}
		}catch(ServiceException e){
			new ServiceException(e.getMessage());
			fail(e.getMessage());
		} catch (DaoException e) {
			new ServiceException(e.getMessage());
			fail(e.getMessage());
		}
	}
	
	/**
	 * Test de para modificarUsuario
	 */
//	@Test
	@Rollback(false)
	public void testModificar() throws Exception{
		try{
			usuarioService.modificarUsuario("pepe@udea", "1234582", "federico", "perez", "159753", "1");
		}catch(ServiceException e){
			new ServiceException(e.getMessage());
			fail(e.getMessage());
		} catch (DaoException e) {
			new ServiceException(e.getMessage());
			fail(e.getMessage());
		}

	}
	
	/**
	 * Test de para eliminarUsuario
	 */
//	@Test
	@Rollback(false)
	public void testEliminar() throws Exception{
		try{
			usuarioService.eliminarUsuario("pepe@udea", "1234582", "popo", "perez", "159753", "1", "yoiner@udea");
		}catch(ServiceException e){
			new ServiceException(e.getMessage());
			fail(e.getMessage());
		} catch (DaoException e) {
			new ServiceException(e.getMessage());
			fail(e.getMessage());
		}

	}
	
}
