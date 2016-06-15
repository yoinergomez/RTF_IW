package co.edu.udea.iw.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import co.edu.udea.iw.dao.UsuarioDAO;
import co.edu.udea.iw.dto.Usuario;
import co.edu.udea.iw.exception.DaoException;

/**
 * Implementa la interface que provee el DAO de Usuario
 * @author Santiago Gómez Giraldo
 * @version 1
 * @category DAOImpl
 */
public class UsuarioDAOImpl extends HibernateDaoSupport implements UsuarioDAO{

	@Override
	public Usuario obtener(String correo) throws DaoException {
		Usuario usuario = null;
		Session session = null;
		try{
			session =getHibernateTemplate().getSessionFactory().getCurrentSession();
			Criteria criteria = session.createCriteria(Usuario.class);
			criteria.add(Restrictions.eq("correo", correo));
			usuario = (Usuario)criteria.uniqueResult();
		}catch(HibernateException e){
			throw new DaoException(e);
		} 
		return usuario;
	}

	@Override
	public void insertar(Usuario usuario) throws DaoException {
		Session session = null;
		try{
			session= getHibernateTemplate().getSessionFactory().getCurrentSession();
			session.save(usuario);
		}catch(HibernateException e){
			throw new DaoException(e);
		} 
		
	}

	@Override
	public void modificar(Usuario usuario) throws DaoException {
		Session session = null;
		try{
			session= getHibernateTemplate().getSessionFactory().getCurrentSession();
			session.update(usuario);
		}catch(HibernateException e){
			throw new DaoException(e);
		} 
		
	}

	@Override
	public void eliminar(Usuario usuario) throws DaoException {
		Session session = null;
		try{
			session=getHibernateTemplate().getSessionFactory().getCurrentSession();
			session.delete(usuario);
		}catch(HibernateException e){
			throw new DaoException(e);
		} 
		
	}

	@Override
	public List<Usuario> obtener() throws DaoException {
		Session session = null;
		List<Usuario> usuario = null;
		try{
			session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			Criteria criteria = session.createCriteria(Usuario.class);
			usuario = criteria.list();
			
		} catch(HibernateException e){
			throw new DaoException(e);
		} 
		return usuario;
	}

}
