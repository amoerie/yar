package domain.action;

/**
 * @author moerie
 */
public class RemoveAction extends RenameAction {
	private int start;
	private int count;
	private boolean fromBeginning;

    public RemoveAction(int start, int count, boolean fromBeginning) {
        this.start = start;
        this.count = count;
        this.fromBeginning = fromBeginning;
    }
	
	@Override
	public String execute(String fileName) {
		String remainingString = "";
        if(start > fileName.length())
            return "";

        if(fromBeginning) {
            for(int i = 0; i < start; i++)
                remainingString += fileName.charAt(i);

            for(int i = start + count; i < fileName.length(); i++)
                remainingString += fileName.charAt(i);

        } else{
            for(int i = fileName.length()-1; i >= fileName.length() - start; i--)
                remainingString = fileName.charAt(i) + remainingString;

            for(int i = fileName.length() - 1 - start - count; i >= 0; i--)
                remainingString = fileName.charAt(i) + remainingString;
        }
        return remainingString;
	}

    public void setStart(int start) {
        this.start = start;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setFromBeginning(boolean fromBeginning) {
        this.fromBeginning = fromBeginning;
    }

    @Override
	public String toString() {
		return "Remove " + count + " characters starting at " + start + " characters from the " + (fromBeginning? "start":"end");
	}
	
}
