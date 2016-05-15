package co.edu.udea.iw.service;

import co.edu.udea.iw.dao.RolDAO;
import co.edu.udea.iw.dao.UsuarioDAO;
import co.edu.udea.iw.dto.Rol;
import co.edu.udea.iw.dto.Usuario;
import co.edu.udea.iw.exception.DaoException;
import co.edu.udea.iw.exception.ServiceException;
import co.edu.udea.iw.util.validations.Validaciones;
/**
 * Clase transaccional que contiene la logica del negocio
 * para la tabla Usuario de la base de datos
 * @author Santiago Gómez Giraldo
 * @version 1
 * @category BL
 */
public class UsuarioService {
	
	private UsuarioDAO usuarioDAO;
	private RolDAO rolDAO;

	public RolDAO getRolDAO() {
		return rolDAO;
	}

	public void setRolDAO(RolDAO rolDAO) {
		this.rolDAO = rolDAO;
	}

	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}
	
	/**
	 * Metodo usado para autenticar usuario, este se encarga de verificar si el usuario ingresado 
	 * existe en la base de datos y depues verifica si la contraseña es correcta.
	 * @param correo
	 * @param contrasena
	 * @return
	 * @throws DaoException
	 * @throws ServiceException
	 */
public Boolean autenticarUsuario(String correo, String contrasena) throws DaoException, ServiceException{
		
		if(Validaciones.isTextoVacio(correo)){
			throw new ServiceException("El login del usuario no puede ser nula, ni una cadena de caracteres vacia");
		}
		
		if(Validaciones.isTextoVacio(contrasena)){
			throw new ServiceException("La clave del usuario no puede ser nula, ni una cadena de caracteres vacia");
		}
		
		Usuario usuario = usuarioDAO.obtener(correo);
		if(usuario == null){
			throw new ServiceException("Usuario o contrasena no validos");
		}
		
		
		if(!(contrasena).equals(usuario.getContrasena())){
			throw new ServiceException("Usuario o contrasena no validos");
		}
		
		return Boolean.TRUE;	
	}
/**
 *  Metodo para registar un usuario, este verifica que los campos sean validos y que no 
 *  exista un Usuario con el mismo correo
 * @param correo
 * @param cedula
 * @param nombre
 * @param apellido
 * @param contrasena
 * @param rol
 * @throws NumberFormatException
 * @throws Exception
 */
public void registrarUsuario(String correo, String cedula,String nombre, String apellido, String contrasena, String rol) throws NumberFormatException, Exception{
	Usuario usuario=null;
	
	if(Validaciones.isTextoVacio(correo)){
		throw new ServiceException("El correo del usuario no puede ser nulo, ni una cadena de caracteres vacia");
	}
	if(Validaciones.isTextoVacio(nombre)){
		throw new ServiceException("Los nombres del usuario no puede ser nula, ni una cadena de caracteres vacia");
	}
	if(Validaciones.isTextoVacio(apellido)){
		throw new ServiceException("Los apellidos del usuario no puede ser nula, ni una cadena de caracteres vacia");
	}
	if(Validaciones.isTextoVacio(cedula)){
		throw new ServiceException("La cï¿½dula electrï¿½nico del usuario no puede ser nula, ni una cadena de caracteres vacia");
	}
	if(Validaciones.isTextoVacio(rol)){
		throw new ServiceException("El Rol del usuario no puede ser nulo, ni una cadena de caracteres vacia");
	}
	
	if(usuarioDAO.obtener(correo) != null){
		throw new ServiceException("Ya existe un usuario con correo " + correo +  " en el sistema");
	}
	
	usuario= new Usuario();
	Rol rolaux=new Rol();
	rolaux=rolDAO.obtener(Integer.parseInt(rol));
	
	usuario.setCorreo(correo);
	usuario.setCedula(cedula);
	usuario.setNombre(nombre);
	usuario.setApellido(apellido);
	usuario.setContrasena(contrasena);
	usuario.setRol(rolaux);	
	
	usuarioDAO.insertar(usuario);
	}

/**
 * Metodo para buscar un usuario asociado al correo que se envia como parametro
 * El metodo verifica que el correo sea valido y verifica si el usuario existe
 * @param correo
 * @return
 * @throws ServiceException
 * @throws DaoException
 */
public Usuario buscarUsuario(String correo) throws ServiceException, DaoException{
	if(correo == null && "".equals(correo)){
		throw new ServiceException("El correo del cliente a buscar no puede ser nulo, ni una cadena de caracteres vacia");
	}
	if(usuarioDAO.obtener(correo)==null)
	{
		throw new ServiceException("El usuario que ingreso no existe");
	}
	return usuarioDAO.obtener(correo);	
	}

/**
 * Metodo para modificar un usuario, primero verifica si el usuario a modificar existe, 
 * despues valida los campos ingresados y modifica al usuario
 * @param correo
 * @param cedula
 * @param nombre
 * @param apellido
 * @param contrasena
 * @param rol
 * @throws Exception
 */
public void modificarUsuario(String correo, String cedula,String nombre, String apellido, String contrasena, String rol) throws Exception{
	Usuario usuario=null;
	
	if(usuarioDAO.obtener(correo) == null){
		throw new ServiceException("No existe un usuario con correo " + correo +  " en el sistema");
	}
	
	if(Validaciones.isTextoVacio(correo)){
		throw new ServiceException("El correo del usuario no puede ser nulo, ni una cadena de caracteres vacia");
	}
	if(Validaciones.isTextoVacio(nombre)){
		throw new ServiceException("Los nombres del usuario no puede ser nula, ni una cadena de caracteres vacia");
	}
	if(Validaciones.isTextoVacio(apellido)){
		throw new ServiceException("Los apellidos del usuario no puede ser nula, ni una cadena de caracteres vacia");
	}
	if(Validaciones.isTextoVacio(cedula)){
		throw new ServiceException("La cï¿½dula del usuario no puede ser nula, ni una cadena de caracteres vacia");
	}
	if(Validaciones.isTextoVacio(rol)){
		throw new ServiceException("El Rol del usuario no puede ser nulo, ni una cadena de caracteres vacia");
	}
	
	usuario= new Usuario();
	Rol rolaux=new Rol();
	rolaux=rolDAO.obtener(Integer.parseInt(rol));
	
	usuario=usuarioDAO.obtener(correo);
	
	usuario.setCedula(cedula);
	usuario.setNombre(nombre);
	usuario.setApellido(apellido);
	usuario.setContrasena(contrasena);
	usuario.setRol(rolaux);	
	
	usuarioDAO.modificar(usuario);
	}

/**
 * Metodo para eliminar un usuario, primero verifica si el usuario a eliminar existe, luego verifica si el 
 * usuario que lo va a eliminar tiene Rol de administrador
 * despues valida los campos ingresados y elimina al usuario
 * @param correo
 * @param cedula
 * @param nombre
 * @param apellido
 * @param contrasena
 * @param rol
 * @param usuarioElimina
 * @throws Exception
 */
public void eliminarUsuario(String correo, String cedula,String nombre, String apellido, String contrasena, String rol, String usuarioElimina) throws Exception{
	Usuario usuario=null;
	Usuario usuarioAux=usuarioDAO.obtener(usuarioElimina);
	
	if(!usuarioAux.getRol().getId().equals(1)){
		throw new ServiceException("Solamente un administrador puede eliminar un usuario");
	}
	if(usuarioDAO.obtener(correo) == null){
		throw new ServiceException("No existe un usuario con correo " + correo +  " en el sistema");
	}
	if(Validaciones.isTextoVacio(correo)){
		throw new ServiceException("El correo del usuario no puede ser nulo, ni una cadena de caracteres vacia");
	}
	if(Validaciones.isTextoVacio(nombre)){
		throw new ServiceException("Los nombres del usuario no puede ser nula, ni una cadena de caracteres vacia");
	}
	if(Validaciones.isTextoVacio(apellido)){
		throw new ServiceException("Los apellidos del usuario no puede ser nula, ni una cadena de caracteres vacia");
	}
	if(Validaciones.isTextoVacio(cedula)){
		throw new ServiceException("La cï¿½dula electrï¿½nico del usuario no puede ser nula, ni una cadena de caracteres vacia");
	}
	if(Validaciones.isTextoVacio(rol)){
		throw new ServiceException("El Rol del usuario no puede ser nulo, ni una cadena de caracteres vacia");
	}
	
	usuario= new Usuario();
	Rol rolaux=new Rol();
	rolaux=rolDAO.obtener(Integer.parseInt(rol));
	
	usuario=usuarioDAO.obtener(correo);
	
	usuario.setCedula(cedula);
	usuario.setNombre(nombre);
	usuario.setApellido(apellido);
	usuario.setContrasena(contrasena);
	usuario.setRol(rolaux);	
	
	usuarioDAO.eliminar(usuario);
	}
}
