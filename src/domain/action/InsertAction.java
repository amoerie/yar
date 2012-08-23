package domain.action;

/**
 * @author moerie
 */
public class InsertAction extends RenameAction {
	private int insertPosition;
    private boolean fromBeginning;
	private String text;
	
	public InsertAction(int insertPosition, String text, boolean fromBeginning) {
		this.insertPosition = insertPosition;
		this.text = text;
        this.fromBeginning = fromBeginning;
	}

	@Override
	public String execute(String fileName) throws RenameException {
        int length = fileName.length();
		if(insertPosition > length)
			throw new RenameException("Invalid insert position " + insertPosition + ", filename length is only " + length + " characters long.");
        if(fromBeginning)
		    return fileName.substring(0, insertPosition) + text + fileName.substring(insertPosition, length);
        else
            return fileName.substring(0, length - insertPosition) + text + fileName.substring(length - insertPosition, length);
	}

	@Override
	public String toString() {
		return "Insert '" + text + "' at position " + insertPosition + " from " + (fromBeginning?" the beginning":" the end");
	}
	
	public void setInsertPosition(int insertPosition) {
		this.insertPosition = insertPosition;
	}
	
	public void setText(String text) {
		this.text = text;
	}

    public void setFromBeginning(boolean fromBeginning) {
        this.fromBeginning = fromBeginning;
    }
}
