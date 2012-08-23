package domain;

/**
 * @author moerie
 */
public class ActiveFileException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1405063643645611759L;

	public ActiveFileException(String message) {
		super(message);
	}
	
	public ActiveFileException(String message, Throwable cause) {
		super(message, cause);
	}
}
