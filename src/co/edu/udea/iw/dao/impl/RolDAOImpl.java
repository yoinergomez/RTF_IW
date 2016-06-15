package co.edu.udea.iw.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import co.edu.udea.iw.dao.RolDAO;
import co.edu.udea.iw.dto.Rol;
import co.edu.udea.iw.exception.DaoException;

/**
 * Implementa la interface que provee el DAO de Rol
 * @author Santiago Gómez Giraldo
 * @version 1
 * @category DAOImpl
 */
public class RolDAOImpl extends HibernateDaoSupport implements RolDAO{

	@Override
	public Rol obtener(Integer id) throws DaoException {
		Rol usuario = null;
		Session session = null;
		try{
			session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			Criteria criteria = session.createCriteria(Rol.class);
			criteria.add(Restrictions.eq("id", id));
			usuario = (Rol)criteria.uniqueResult();
		}catch(HibernateException e){
			throw new DaoException(e);
		} 
		return usuario;
	}
}
