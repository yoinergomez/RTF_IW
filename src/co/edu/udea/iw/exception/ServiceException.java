package co.edu.udea.iw.exception;

import org.apache.log4j.Logger;

/**
 * Clase de control de excepciones para los servicios de la aplicacion
 * @author Yoiner Gomez yoiner.gomez22@gmail.com
 * @version 1
 * @category EXCEPTION
 */
public class ServiceException extends Exception {
	
	private final static Logger logger = Logger.getLogger(DaoException.class);

	public ServiceException() {
		logger.error("Error ServiceException");
	}

	public ServiceException(String arg0) {
		super(arg0);
		logger.error("Error ServiceException: "+arg0);
		// TODO Auto-generated constructor stub
	}

	public ServiceException(Throwable arg0) {
		super(arg0);
		logger.error("Error ServiceException: "+arg0);
		// TODO Auto-generated constructor stub
	}

	public ServiceException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		logger.error("Error ServiceException: "+arg0+"\t"+arg1);
		// TODO Auto-generated constructor stub
	}

}
