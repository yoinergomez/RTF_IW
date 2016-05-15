package co.edu.udea.iw.exception;

import org.apache.log4j.Logger;

/**
 * Clase de control de excepciones para los DAO de la aplicacion
 * @author Yoiner Gomez yoiner.gomez22@gmail.com
 * @version 1
 * @category EXCEPTION
 */
public class DaoException extends Exception {

	private final static Logger logger = Logger.getLogger(DaoException.class);
	
	public DaoException() {
		logger.error("Error IWDaoException");
	}

	public DaoException(String arg0) {
		super(arg0);
		logger.error("Error IWDaoException: "+arg0);
		// TODO Auto-generated constructor stub
	}

	public DaoException(Throwable arg0) {
		super(arg0);
		logger.error("Error IWDaoException: "+arg0);
		// TODO Auto-generated constructor stub
	}

	public DaoException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		logger.error("Error IWDaoException: "+arg0+"\t"+arg1);
		// TODO Auto-generated constructor stub
	}

}
