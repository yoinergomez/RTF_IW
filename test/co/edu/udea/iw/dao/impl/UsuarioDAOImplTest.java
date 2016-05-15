package co.edu.udea.iw.dao.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.hibernate.annotations.SourceType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.iw.dao.RolDAO;
import co.edu.udea.iw.dao.UsuarioDAO;
import co.edu.udea.iw.dto.Rol;
import co.edu.udea.iw.dto.Usuario;



@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(locations = "file:src/configuracion.xml")
/**
 * Test de UsuariDAOImpl
 * @author Santiago Gómez Giraldo
 * @version 1
 * @category Test
 *
 */
public class UsuarioDAOImplTest {
	
	@Autowired
	UsuarioDAO usuarioDAO;
	@Autowired
	RolDAO rolDAO;

	@Test
	@Rollback(false)
	public void testInsertar() {
		Rol rol=null;
		Usuario usuario=new Usuario();
		usuario.setNombre("Jhonatan");
		usuario.setApellido("Orozco");
		usuario.setCedula("125469475");
		usuario.setCorreo("Jhonatan@udea");
		usuario.setContrasena("123456789");
		try{
			rol = rolDAO.obtener(1);
			usuario.setRol(rol);
			usuarioDAO.insertar(usuario);
			System.out.println(usuario.getNombre()+' '+usuario.getRol().getNombre());
			assertTrue(usuario!=null);
		}catch(Exception e){
			e.printStackTrace();
			fail(e.getMessage());
			
		}
	}

//@Test
	public void testObtener() {
		Usuario usuario=null;
		UsuarioDAO usuarioDAO= null;
		try{
			usuarioDAO=new UsuarioDAOImpl();
			usuario = usuarioDAO.obtener("Jhonatan@udea");
			System.out.println(usuario.getNombre()+" "+usuario.getRol().getNombre());
			assertTrue(usuario!=null);
		}catch(Exception e){
			e.printStackTrace();
			fail(e.getMessage());
			
		}
	}
	
//	@Test
	public void testModificar() {
		Rol rol=null;
		RolDAO rolDAO= null;
		Usuario usuario=new Usuario();
		usuario.setNombre("Jhonatan");
		usuario.setApellido("Orozco");
		usuario.setCedula("125469475");
		usuario.setCorreo("Jhonatan@udea");
		usuario.setContrasena("123456789");
		UsuarioDAO usuarioDAO= null;
		try{
			rolDAO= new RolDAOImpl();
			rol = rolDAO.obtener(2);
			usuario.setRol(rol);
			usuarioDAO=new UsuarioDAOImpl();
			usuarioDAO.modificar(usuario);
			System.out.println(usuario.getNombre()+' '+usuario.getRol().getNombre());
			assertTrue(usuario!=null);
		}catch(Exception e){
			e.printStackTrace();
			fail(e.getMessage());			
		}
	}
	
//@Test
	public void testEliminar() {
		Rol rol=null;
		RolDAO rolDAO= null;
		Usuario usuario=new Usuario();
		usuario.setNombre("Jhonatan");
		usuario.setApellido("Orozco");
		usuario.setCedula("125469475");
		usuario.setCorreo("Jhonatan@udea");
		usuario.setContrasena("123456789");
		UsuarioDAO usuarioDAO= null;
		try{
			rolDAO= new RolDAOImpl();
			rol = rolDAO.obtener(1);
			usuario.setRol(rol);
			usuarioDAO=new UsuarioDAOImpl();
			usuarioDAO.eliminar(usuario);
			assertTrue(usuario!=null);
		}catch(Exception e){
			e.printStackTrace();
			fail(e.getMessage());		
		}
	}
	
//	@Test
	@Rollback(false)
	public void testObtenerTodos() {
		List<Usuario> usuarios=null;
		try{
			usuarios = usuarioDAO.obtener();
			for(Usuario usuario:usuarios){
				System.out.println(usuario.getNombre());
			}
			assertTrue(usuarios.size()>0);
		}catch(Exception e){
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
}
