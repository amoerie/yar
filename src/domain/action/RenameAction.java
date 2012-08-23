package domain.action;

/**
 * @author moerie
 */
public abstract class RenameAction {
	public abstract String execute(String fileName) throws RenameException;
	public abstract String toString();
}
