package domain;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import domain.action.RenameAction;
import domain.action.RenameException;


/**
 * @author moerie
 */
public class ActiveFile {
	private File file;
	private String oldFileName;
	private String newFileName;

    private String extension;
	
	public ActiveFile(File file) {
		this.file = file;
        String fileName = file.getName();
        if(fileName.contains(".")) {
		    oldFileName = fileName.substring(0, fileName.lastIndexOf('.'));
            extension = fileName.substring(fileName.lastIndexOf('.'), fileName.length());
        } else {
            oldFileName = fileName;
            extension = "";
        }
		newFileName = new String(oldFileName);
	}
	
	public void tryRename(List<RenameAction> renameActions) throws ActiveFileException {
		newFileName = new String(oldFileName);
		for(RenameAction renameAction: renameActions) {
			try {
				newFileName = renameAction.execute(newFileName);
			} catch (RenameException e) {
				throw new ActiveFileException("Rename action failed.\n\nFilename before action: \t" + newFileName +
						".\nAttempted rename action: \t" + renameAction.toString(), e);
			}
		}
	}
	
	public void rename(List<RenameAction> renameActions) throws ActiveFileException {
		tryRename(renameActions);
		Path currentPath = file.toPath();
		Path newPath = getNewPath(currentPath, newFileName, extension);
        try {
			Files.move(currentPath, newPath);
			oldFileName = newFileName;
		} catch (IOException e) {
			throw new ActiveFileException("Failed to rename " + currentPath.toString() + " to " + newPath.toString(), e);
		}
	}
	
	public String getNewFileName() {
		return newFileName;
	}
	
	public String getOldFileName() {
		return oldFileName;
	}

    /**
     * Resolves a Path object for the desired new filename of an existing Path.
     * @param currentPath
     * @param newFileName
     * @param extension
     * @return
     */
    private Path getNewPath(Path currentPath, String newFileName, String extension) {
        Path newPath = currentPath.getParent().resolve(newFileName + extension);
        int version = 2;
        /*
        If the new filename is already taken, add (2) to the new filename.
        If that's also already taken, use (3), then (4), etc.
         */
        while(newPath.toFile().exists()) {
            newPath = currentPath.getParent().resolve(newFileName + " (" + version + ")" + extension);
            version++;
        }
        return newPath;
    }
	
}
