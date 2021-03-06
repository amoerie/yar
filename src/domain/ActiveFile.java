package domain;

import domain.action.RenameAction;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


/**
 * @author moerie
 */
public class ActiveFile {
	private File file;
	private String oldFileName;
	private String newFileName;

    private String extension;
	
	public ActiveFile(File file) {
        loadFile(file);
	}

    private void loadFile(File file) {
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
				newFileName = renameAction.execute(newFileName);
		}
	}
	
	public void rename(List<RenameAction> renameActions) throws ActiveFileException {
        try {
            tryRename(renameActions);
            Path currentPath = file.toPath();
            Path newPath = getNewPath(currentPath, newFileName, extension);
			Files.move(currentPath, newPath);
			loadFile(newPath.toFile());
		} catch (Exception e) {
			throw new ActiveFileException("Failed to rename " + getOldFileName() + " to " + getNewFileName(), e);
		}
	}
	
	public String getNewFileName() {
		return newFileName + extension;
	}
	
	public String getOldFileName() {
		return oldFileName + extension;
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
