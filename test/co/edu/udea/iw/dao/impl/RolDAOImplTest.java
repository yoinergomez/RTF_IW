package co.edu.udea.iw.dao.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import co.edu.udea.iw.dao.RolDAO;
import co.edu.udea.iw.dto.Rol;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(locations = "file:src/configuracion.xml")
/**
 * Test de RolDAOImpl
 * @author Santiago Gómez Giraldo
 * @version 1
 * @category Test
 *
 */
public class RolDAOImplTest {
	@Autowired
	RolDAO rolDAO;

	@Test
	public void testObtener() {
		Rol rol=null;
		try{
			rol=rolDAO.obtener(1);
			System.out.println(rol.getNombre());
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
