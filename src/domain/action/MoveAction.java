package domain.action;

/**
 * @author moerie
 */
public class MoveAction extends RenameAction{
    // MOVE FROM
    /**
     * If you want to move the last 3 characters except the last one to the beginning of the file name, these
     * would be your values:
     * fromFromBeginning = false
     * fromPosition = 1
     * count = 2
     *
     * toFromBeginning = true
     * toPosition = 0
     */
    private boolean fromFromBeginning;
    private int fromPosition;
    private int count;

    // TO
    private boolean toFromBeginning;
    private int toPosition;

    /**
     * Helper attributes that vary per given file name.
     */
    private int startIndex;
    private int endIndex;
    private String targetName;
    private int targetIndex;

    public MoveAction(boolean fromFromBeginning, int fromPosition, int count, boolean toFromBeginning, int toPosition) {
        this.fromFromBeginning = fromFromBeginning;
        this.fromPosition = fromPosition;
        this.count = count;
        this.toFromBeginning = toFromBeginning;
        this.toPosition = toPosition;
    }

    @Override
    public String execute(String fileName) throws RenameException {
        /*
        Step 1: Extract the part of the filename that needs to be moved.
         */
        if(fromFromBeginning)
            startIndex = fromPosition;
        else
            startIndex = fileName.length() - fromPosition - 1;
        endIndex = startIndex + count;
        if(endIndex > fileName.length())
            throw new RenameException(
                    String.format("End of string reached. Count is higher than remaining characters. " +
                            "Tried to move from position %d to %d but filename is only %d characters",
                            startIndex, endIndex, fileName.length()));
        String movingPart = fileName.substring(startIndex, endIndex);
        String fileNameWithoutMovingPart = fileName.substring(0, startIndex) +
                fileName.substring(endIndex, fileName.length());

        /*
        Step 2: Determine the location where the moving part will go
         */
        if(toFromBeginning)
            targetIndex = toPosition;
        else {
            targetIndex = fileNameWithoutMovingPart.length() - toPosition;
        }

        /*
        Step 3: Insert the moving part at the new location
         */
        if(targetIndex < 0)
            throw new RenameException("Back at the beginning of string. Can't move " + movingPart + " to index " + targetIndex);
        targetName = fileNameWithoutMovingPart.substring(0, targetIndex)
                + movingPart
                + fileNameWithoutMovingPart.substring(targetIndex, fileNameWithoutMovingPart.length());
        return targetName;
    }

    @Override
    public String toString() {
        return String.format("Move %d characters from position %d from %s to position %d from %s",
                count, fromPosition, fromFromBeginning ? "the beginning":"the end",
                toPosition, toFromBeginning?"the beginning":"the end");
    }

    public void setFromFromBeginning(boolean fromFromBeginning) {
        this.fromFromBeginning = fromFromBeginning;
    }

    public void setFromPosition(int fromPosition) {
        this.fromPosition = fromPosition;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setToFromBeginning(boolean toFromBeginning) {
        this.toFromBeginning = toFromBeginning;
    }

    public void setToPosition(int toPosition) {
        this.toPosition = toPosition;
    }
}
