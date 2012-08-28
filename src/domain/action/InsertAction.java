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
	public String execute(String fileName) {
        int length = fileName.length();
        int safeInsertPosition = insertPosition;
		if(safeInsertPosition > length)
            safeInsertPosition = length;
        if(fromBeginning)
		    return fileName.substring(0, safeInsertPosition) + text + fileName.substring(safeInsertPosition, length);
        else
            return fileName.substring(0, length - safeInsertPosition) + text + fileName.substring(length - safeInsertPosition, length);
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

    public int getInsertPosition() {
        return insertPosition;
    }

    public boolean isFromBeginning() {
        return fromBeginning;
    }

    public String getText() {
        return text;
    }
}
