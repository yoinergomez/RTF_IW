package co.edu.udea.iw.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import co.edu.udea.iw.dao.SolicitudDAO;
import co.edu.udea.iw.dto.Dispositivo;
import co.edu.udea.iw.dto.Solicitud;
import co.edu.udea.iw.dto.SolicitudId;
import co.edu.udea.iw.dto.Usuario;
import co.edu.udea.iw.exception.DaoException;
/**
 * Clase que implementa la interfaz SolicitudDAO integrando Spring-Hibernate
 * @author Jhonatan Orozco Blandon
 * @version 1
 * @category DAOImp
 *
 */
public class SolicitudDAOImpl extends HibernateDaoSupport implements SolicitudDAO {

	@Override
	public void insertar(Solicitud solicitud) throws DaoException {
		Session session=null;
		try{
			session=getHibernateTemplate().getSessionFactory().getCurrentSession();
			session.save(solicitud);
		}catch(HibernateException e){
			throw new DaoException(e);
		}
		
	}

	@Override
	public List<Solicitud> obtenerTodas() throws DaoException {
		Session session=null;
		List<Solicitud> solicitudes=null;
		try{
			session=getHibernateTemplate().getSessionFactory().getCurrentSession();
			Criteria criteria=session.createCriteria(Solicitud.class);
			solicitudes=criteria.list();
		}catch(HibernateException e){
			throw new DaoException(e);
		}
		return solicitudes;
	}

	@Override
	public void modificar(Solicitud solicitud) throws DaoException {
		Session session=null;
		try{
			session=getHibernateTemplate().getSessionFactory().getCurrentSession();
			session.update(solicitud);
		}catch(HibernateException e){
			throw new DaoException(e);
		}
		
	}

	@Override
	public void eliminar(Solicitud solicitud) throws DaoException {
		Session session=null;
		try{
			session=getHibernateTemplate().getSessionFactory().getCurrentSession();
			session.delete(solicitud);
		}catch(HibernateException e){
			throw new DaoException(e);
		}
		
	}

	@Override
	public Solicitud obtenerSolicitud(SolicitudId id) throws DaoException {
		Solicitud solicitud=null;
		Session session=null;
		try{
			session=getHibernateTemplate().getSessionFactory().getCurrentSession();
			solicitud=(Solicitud)session.get(Solicitud.class,id);
		}catch(HibernateException e){
			throw new DaoException(e);
		}
		return solicitud;
	}

	@Override
	public List<Solicitud> obtenerSolicitudesDeUnUsuario(Usuario usuario, int estadoSolicitud) throws DaoException{
		List<Solicitud> solicitudes=null;
		Session session=null;
		
		try{
			session=getHibernateTemplate().getSessionFactory().getCurrentSession();
			Criteria criteria=session.createCriteria(Solicitud.class);
			criteria.add(Restrictions.eq("id.usuario", usuario));
			if(estadoSolicitud>0){
				criteria.add(Restrictions.eq("estadoSolicitud", estadoSolicitud));
			}
			solicitudes=criteria.list();

		}catch(HibernateException e){
			throw new DaoException(e);
		}
		return solicitudes;
	}
	
	
	@Override
	public List<Solicitud> obtenerSolicitudesAprobadasDeUnDispositivo(Dispositivo dispositivo) throws DaoException{
		List<Solicitud> solicitudes=null;
		Session session=null;
		try{
			session=getHibernateTemplate().getSessionFactory().getCurrentSession();
			Criteria criteria=session.createCriteria(Solicitud.class);
			criteria.add(Restrictions.eq("id.dispositivo", dispositivo));
			criteria.add(Restrictions.eq("estadoSolicitud",1));
			solicitudes=criteria.list();

		}catch(HibernateException e){
			throw new DaoException(e);
		}
		return solicitudes;
	}
	

}
