package co.edu.udea.iw.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.iw.dao.DispositivoDAO;
import co.edu.udea.iw.dao.SolicitudDAO;
import co.edu.udea.iw.dao.UsuarioDAO;
import co.edu.udea.iw.dto.Dispositivo;
import co.edu.udea.iw.dto.Solicitud;
import co.edu.udea.iw.dto.SolicitudId;
import co.edu.udea.iw.dto.Usuario;
import co.edu.udea.iw.exception.DaoException;
import co.edu.udea.iw.exception.ServiceException;
import co.edu.udea.iw.util.DateUtil;
import co.edu.udea.iw.util.validations.Validaciones;

/**
 *  Es la clase que implementa la logica del negocio que es conscerniente a
 *  solicitud, para ello la clase despu�s de verificar las reglas del negocio
 *  utiliza las DAO para realizar operaciones en la entidad Solicitud. 
 * Al ser una clase transaccional cualquier error que interumpa 
 * la normal ejecuci�n de un metodo de la clase,genera un rollback 
 * para preservar la integridad de la informaci�n de la base de datos.
 * @author Jhonatan Orozco Blandon
 * @version 1
 * @category service
 *
 */
@Transactional
public class SolicitudService {

	
	private SolicitudDAO solicitudDAO;
	private UsuarioDAO usuarioDAO;
	private DispositivoDAO dispositivoDAO;

	/**
	 * El m�todo se encarga de comprobar la disponibilidad del dispositivo que se quiere prestar
	 * de acuerdo a las fechas que se ingresan en la solicitud.Retorna true cuando las fechas no se traslapan
	 * con ninguna otra solicitud de prestamo del dispositivo ya aprobada previamente, en caso contrario retorna false
	 * @param solicitud
	 * @return
	 * @throws ServiceException
	 */
	public boolean estaDisponibleDispositivo(Solicitud solicitud) throws
	ServiceException {
		List<Solicitud> solicitudes=null;
		Dispositivo dispositivo=null;
		Date fechaInicioSolicitud=null;
		Date fechaFinSolicitud=null;
		Date fechaInicioSolicitudAprobada=null;
		Date fechaFinSolicitudAprobada=null;
		
		try{
			dispositivo=solicitud.getId().getDispositivo();
			fechaInicioSolicitud=solicitud.getId().getFechaInicio();
			fechaFinSolicitud=solicitud.getId().getFechaFin();
			solicitudes=solicitudDAO.obtenerSolicitudesAprobadasDeUnDispositivo(dispositivo);
			for(Solicitud s:solicitudes){
				fechaInicioSolicitudAprobada=s.getId().getFechaInicio();
				fechaFinSolicitudAprobada=s.getId().getFechaFin();
				/*
				System.out.println(fechaInicioSolicitud+" compare to "+ fechaInicioSolicitudAprobada +" :"+ fechaInicioSolicitud.compareTo(fechaInicioSolicitudAprobada));
				System.out.println(fechaFinSolicitud+" compare to "+ fechaInicioSolicitudAprobada +": "+ fechaFinSolicitud.compareTo(fechaInicioSolicitudAprobada));
				System.out.println(fechaInicioSolicitud+" compare to "+ fechaFinSolicitudAprobada + ": "+ fechaInicioSolicitud.compareTo(fechaFinSolicitudAprobada));
				System.out.println(fechaFinSolicitud+" compare to "+ fechaFinSolicitudAprobada+ ": "+ fechaFinSolicitud.compareTo(fechaFinSolicitudAprobada));
				*/
				if(fechaInicioSolicitud.compareTo(fechaInicioSolicitudAprobada)<=0
						&& fechaFinSolicitud.compareTo(fechaInicioSolicitudAprobada)>0 ){
					return false;
				}
				if(fechaInicioSolicitud.compareTo(fechaFinSolicitudAprobada)<0
						&& fechaFinSolicitud.compareTo(fechaFinSolicitudAprobada)>0 ){
					return false;
				}
				
			}
		}catch (DaoException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	
	/**
	 * El m�todo se encarga de verificar que el usuario que esta creando la solicitud
	 * no tenga pendiente por entregar alg�n dispositivo al laboratorio.Si retorna
	 * true el usuario tiene permisos para crear la solicitud.
	 * @param solicitud
	 * @return
	 * @throws ServiceException
	 */
	public boolean estaUsuarioHabilitado (Solicitud solicitud) throws ServiceException  {
		List<Solicitud> solicitudes=null;
		Usuario usuario=null;
		Date fechaInicioSolicitud=null;
		Date fechaFinSolicitudEnPrestamo=null;
		Date fechaMenor=null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy hh:mm");
		try{
			fechaInicioSolicitud=solicitud.getId().getFechaInicio();
			usuario=solicitud.getId().getUsuario();
			fechaMenor=dateFormat.parse("2017-05-03 00:00");
			solicitudes=solicitudDAO.obtenerSolicitudesDeUnUsuario(usuario, 2);
			for(Solicitud s:solicitudes){
				fechaFinSolicitudEnPrestamo=s.getId().getFechaFin();
				if(fechaMenor.compareTo(fechaFinSolicitudEnPrestamo)==1){
					fechaMenor=fechaFinSolicitudEnPrestamo;
				}
			}
			if(fechaMenor.compareTo(fechaInicioSolicitud)==1){
				return true;
			}
			
		 }catch (DaoException e) {
				e.printStackTrace();
		 } catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
		
	}
	/**
	 * Se encarga de registrar y aprobar la solicitud si cumple con las siguientes
	 * reglas:
	 * Los campos obligatorios no son nulos.
	 * Las fechas cumplen con el rango menor a 8 horas
	 * Las fechas son consistentes
	 * La solicitud no ha sido creada anteriormente
	 * El usuario no tiene que devolver ning�n dispositivo
	 * El dispositivo tiene disponibilidad en las fechas solicitadas
	 * En caso de que un criterio falle se rechaza la solicitud
	 * 
	 * @param correo
	 * @param codigoDispositivo
	 * @param fechaInicio
	 * @param fechaFin
	 * @param motivo
	 * @throws DaoException
	 * @throws ServiceException
	 * @throws DaoException
	 * @throws ParseException
	 */
	public void registrarSolicitud(String correo, int codigoDispositivo,
			String fechaInicio, String fechaFin,
			String motivo) throws DaoException, ServiceException, DaoException, ParseException{
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy HH:mm");
		Usuario usuario=null;
		Dispositivo dispositivo=null;
		Date fechaInicioSolicitud=null;
		Date fechaFinSolicitud=null;
		SolicitudId id=null;
		Solicitud solicitud=null;
		if(Validaciones.isTextoVacio(correo)){
			throw new ServiceException("El correo no puede ser vacio");
		}
		if(Validaciones.isTextoVacio(fechaInicio)){
			throw new ServiceException("La fecha inicial de la solicitud no puede ser vacia");
		}
		if(Validaciones.isTextoVacio(fechaFin)){
			throw new ServiceException("La fecha final de la solicitud no puede ser vacia");
		}
		usuario=usuarioDAO.obtener(correo);
		if(usuario==null){
			throw new ServiceException("El usuario no existe en el sistema");
		}
		
		dispositivo=dispositivoDAO.obtener(codigoDispositivo);
		if(dispositivo==null){
			throw new ServiceException("El dispositivo no existe en el sistema");
		}
		
		fechaInicio = fechaInicio.replace('T', ' ');
		
		fechaInicioSolicitud=dateFormat.parse(fechaInicio);
		if(fechaInicioSolicitud==null){
			throw new ServiceException("la fecha inicial no es valida");
		}
		fechaFin = fechaFin.replace('T', ' ');
		fechaFinSolicitud=dateFormat.parse(fechaFin);
		if(fechaFinSolicitud==null){
			throw new ServiceException("la fecha final no es valida");
		}
		if(!DateUtil.esDiaLaboral(fechaInicioSolicitud)){
			throw new ServiceException("la fecha inicial no esta en el rango laboral");
		}
		if(!DateUtil.esDiaLaboral(fechaFinSolicitud)){
			throw new ServiceException("la fecha final no esta en el rango laboral");
		}
		if(!DateUtil.esMenor8Horas(fechaInicioSolicitud, fechaFinSolicitud)){
			throw new ServiceException("El rango de fecha es superior a 8 horas");
		}
		if(fechaInicioSolicitud.compareTo(fechaFinSolicitud)!=-1){
			throw new ServiceException("Las fecha inicial no puede ser mayor o igual que la final");
		}
		id=new SolicitudId();
		id.setDispositivo(dispositivo);
		System.out.println(dispositivo.getNombre());
		id.setUsuario(usuario);
		System.out.println(usuario.getApellido());
		id.setFechaInicio(fechaInicioSolicitud);
		System.out.println(fechaInicioSolicitud);
		id.setFechaFin(fechaFinSolicitud);
		System.out.println(fechaFinSolicitud);
		
		solicitud=solicitudDAO.obtenerSolicitud(id);
		if(solicitud!=null){
			throw new ServiceException("La solicitud ya existe en el sistema");
		}
		solicitud=new Solicitud();
		solicitud.setId(id);
		solicitud.setEstadoSolicitud(1);
		solicitud.setMotivo(motivo);
		if(!estaDisponibleDispositivo(solicitud)){
			throw new ServiceException("El dispositivo no esta disponible en el horario especificado");
		}
		
		if(!estaUsuarioHabilitado(solicitud)){
			throw new ServiceException("El usuario no puede registrar la solicitud porque no ha "
					+ "devuelto un dispositivo");
		}
		
		solicitudDAO.insertar(solicitud);
		
	}
	
	/**
	 * Retorna el historial de solicitudes de un usuario en especifico.
	 * @param correo
	 * @return
	 * @throws DaoException
	 * @throws ServiceException
	 * @throws DaoException
	 */
	public List<Solicitud> listarSolicitudesDeUnUsuario(String correo)
			throws DaoException, ServiceException, DaoException{
		List<Solicitud> solicitudes=null;
		Usuario usuario=null;
		if(Validaciones.isTextoVacio(correo)){
			throw new ServiceException("El correo no puede ser vacio");
		}
		usuario=usuarioDAO.obtener(correo);
		if(usuario==null){
			throw new ServiceException("El usuario no existe en el sistema");
		}
		solicitudes=solicitudDAO.obtenerSolicitudesDeUnUsuario(usuario, -1);
		return solicitudes;
	}
	
	/**
	 * Obtiene todos los dispositivos que están disponibles
	 * @return
	 * @throws DaoException
	 */
	public List<Dispositivo> obtenerDispositivosDisponibles() throws DaoException{
		List<Solicitud> solicitudes = null;
		List<Dispositivo> dispositivosSolicitados= null;
		List<Dispositivo> dispositivosDisponibles= null;
		solicitudes = solicitudDAO.obtenerTodas();
		dispositivosSolicitados = new ArrayList<Dispositivo>();
		dispositivosDisponibles = new ArrayList<Dispositivo>();
		for (Solicitud solicitud : solicitudes){
			dispositivosSolicitados.add(solicitud.getId().getDispositivo());
		}
		dispositivosDisponibles = dispositivoDAO.obtener();
		for(Dispositivo dispositivo: dispositivosSolicitados){
			dispositivosDisponibles.remove(dispositivo);
		}
		return dispositivosDisponibles;
	}
	

	/**
	 * Permite al empleado cambiar el estado de una solicitud que haya realizado un usuario
	 * @param empleado
	 * @param cliente
	 * @param estado
	 * @param solicitud
	 * @throws ServiceException
	 * @throws DaoException
	 */
	public void cambiarEstadoSolicitud(Usuario empleado, Usuario cliente, int estado, Solicitud solicitud) throws ServiceException, DaoException{
		if(estado<1 || estado>3){
			throw new ServiceException("El estado ingresado no es válido");
		}
		if(empleado.getRol().getId()==3){
			throw new ServiceException("El usuario no puede realizar un cambio de solicitud");
		}
		empleado = usuarioDAO.obtener(empleado.getCorreo());
		if(empleado==null){
			throw new ServiceException("El empleado no existe");
		}
		cliente = usuarioDAO.obtener(cliente.getCorreo());
		if(cliente==null){
			throw new ServiceException("El usuario no existe");
		}
		solicitud = solicitudDAO.obtenerSolicitud(solicitud.getId());
		if(solicitud==null){
			throw new ServiceException("La solicitud no existe");
		}
		solicitud.setEstadoSolicitud(estado);
		solicitudDAO.modificar(solicitud);
	}
	
	/**
	 * El administrador o empleado cambia la solicitud de un prestamo.
	 * @param empleado
	 * @param cliente
	 * @param estado
	 * @param solicitud
	 * @throws ServiceException
	 * @throws DaoException
	 */
	public Solicitud cambiarEstadoSolicitud(String empleadoCorreo,int estado, SolicitudId solicitudId) throws ServiceException, DaoException{
		Usuario empleado=null;
		Usuario cliente=null;
		if(estado<1 || estado>3){
			throw new ServiceException("El estado ingresado no es válido");
		}
		if(Validaciones.isTextoVacio(empleadoCorreo)){
			throw new ServiceException("El correo del empleado no puede ser vacio ");
		}
		empleado = usuarioDAO.obtener(empleadoCorreo);
		if(empleado==null){
			throw new ServiceException("El empleado no existe");
		}
		if(empleado.getRol().getId()==3){
			throw new ServiceException("El usuario no puede realizar un cambio de solicitud");
		}
		
		if(solicitudId==null){
			throw new ServiceException("La identificacion de la solicitud no puede ser vacia");
		}
		cliente = solicitudId.getUsuario();
		if(cliente==null){
			throw new ServiceException("El usuario no existe");
		}
	
		Solicitud solicitud = solicitudDAO.obtenerSolicitud(solicitudId);
		if(solicitud==null){
			throw new ServiceException("No se encontro solicitud asociada a la id");
		}
		solicitud.setEstadoSolicitud(estado);
		solicitudDAO.modificar(solicitud);
		return solicitud;
	}
	
	
	/**
	 * Se busca una solicitud por medio de los atributos que son clave primaria en la 
	 * entidad Solicitud
	 * @param correo
	 * @param codigoDispositivo
	 * @param fechaInicio
	 * @param fechaFin
	 * @return
	 * @throws DaoException
	 * @throws ServiceException
	 * @throws DaoException
	 * @throws ParseException
	 */
	public Solicitud buscarSolicitud(String correo, int codigoDispositivo,
			String fechaInicio, String fechaFin) throws DaoException, ServiceException, DaoException, ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy HH:mm");
		Usuario usuario=null;
		Dispositivo dispositivo=null;
		Date fechaInicioSolicitud=null;
		Date fechaFinSolicitud=null;
		SolicitudId id=null;
		Solicitud solicitud=null;
		if(Validaciones.isTextoVacio(correo)){
			throw new ServiceException("El correo no puede ser vacio");
		}
		if(Validaciones.isTextoVacio(fechaInicio)){
			throw new ServiceException("La fecha inicial de la solicitud no puede ser vacia");
		}
		if(Validaciones.isTextoVacio(fechaFin)){
			throw new ServiceException("La fecha final de la solicitud no puede ser vacia");
		}
		usuario=usuarioDAO.obtener(correo);
		if(usuario==null){
			throw new ServiceException("El usuario no existe en el sistema");
		}
		
		dispositivo=dispositivoDAO.obtener(codigoDispositivo);
		if(dispositivo==null){
			throw new ServiceException("El dispositivo no existe en el sistema");
		}
		
		fechaInicioSolicitud=dateFormat.parse(fechaInicio);
		if(fechaInicioSolicitud==null){
			throw new ServiceException("la fecha inicial no es valida");
		}
		fechaFinSolicitud=dateFormat.parse(fechaFin);
		if(fechaFinSolicitud==null){
			throw new ServiceException("la fecha final no es valida");
		}
		
		id=new SolicitudId();
		id.setUsuario(usuario);
		id.setDispositivo(dispositivo);
		id.setFechaInicio(fechaInicioSolicitud);
		id.setFechaFin(fechaFinSolicitud);
		solicitud=solicitudDAO.obtenerSolicitud(id);
		if(solicitud==null){
			throw new ServiceException("No se encontro solicitud asociada a la id");
		}
		return solicitud;
		
	}
	

	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	public SolicitudDAO getSolicitudDAO() {
		return solicitudDAO;
	}

	public void setSolicitudDAO(SolicitudDAO solicitudDAO) {
		this.solicitudDAO = solicitudDAO;
	}

	public DispositivoDAO getDispositivoDAO() {
		return dispositivoDAO;
	}

	public void setDispositivoDAO(DispositivoDAO dispositivoDAO) {
		this.dispositivoDAO = dispositivoDAO;
	}
	
	
}
