package co.edu.udea.iw.dao.impl;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import co.edu.udea.iw.dao.DispositivoDAO;
import co.edu.udea.iw.dto.Dispositivo;
import co.edu.udea.iw.exception.DaoException;

/**
 * Implementa la interface que provee el DAO de Dispositivo
 * @author Yoiner Gomez yoiner.gomez22@gmail.com
 * @version 1
 * @category IMPLEMENTACION
 */
public class DispositivoDAOImpl extends HibernateDaoSupport implements DispositivoDAO{

	@Override
	public Dispositivo insertar(Dispositivo dispositivo) throws DaoException {
		Session session = null;
		try{
			session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			session.save(dispositivo);
		}catch(HibernateException e){
			throw new DaoException(e);
		}
		return dispositivo;
	}

	@Override
	public Dispositivo modificar(Dispositivo dispositivo) throws DaoException {
		Session session = null;
		try{
			session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			
			session.update(dispositivo);
			
		}catch(HibernateException e){
			throw new DaoException(e);
		}
		
		return dispositivo;
	}

	@Override
	public List<Dispositivo> obtener() throws DaoException {
		List<Dispositivo> clientes = new ArrayList<Dispositivo>();
		Session session = null;
		try{
			session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			
			Criteria criteria = session.createCriteria(Dispositivo.class);
			
			clientes = criteria.list();
			
		}catch(HibernateException e){
			throw new DaoException(e);
		}
		
		return clientes;
	}

	@Override
	public Dispositivo obtener(Integer codigo) throws DaoException {
		Dispositivo dispositivo = null;
		Session session = null;
		try{
			session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			
			dispositivo = (Dispositivo)session.get(Dispositivo.class, codigo);
			
		}catch(HibernateException e){
			throw new DaoException(e);
		}
		
		return dispositivo;
	}
	
	@Override
	public Dispositivo eliminar(Integer codigo) throws DaoException {
		Dispositivo dispositivo = null;
		Session session = null;
		try{
			session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			dispositivo = obtener(codigo);
			session.delete(dispositivo);
			
		}catch(HibernateException e){
			throw new DaoException(e);
		}
		
		return dispositivo;
	}

}
