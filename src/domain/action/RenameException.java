package domain.action;

/**
 * @author moerie
 */
public class RenameException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3951805307524309427L;

	public RenameException(String message) {
		super(message);
	}
	
	public RenameException(String message, Throwable cause) {
		super(message, cause);
	}

}
