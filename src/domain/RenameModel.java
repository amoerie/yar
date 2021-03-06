package domain;

import domain.action.RenameAction;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author moerie
 */
public class RenameModel  {
	private List<ActiveFile> 		activeFiles;
    private RenameAction            pendingRenameAction;
	
	private ActiveFileTableModel	activeFileTableModel;
	private RenameActionListModel	renameActionListModel;
	
	public RenameModel() {
		activeFiles 			= new ArrayList<>();
		
		activeFileTableModel 	= new ActiveFileTableModel(activeFiles);
		renameActionListModel 	= new RenameActionListModel();
	}
	
	public void setFiles(File... files) {
		activeFiles.clear();
		for(File file: files){
			activeFiles.add(new ActiveFile(file));
		}
        renameActionListModel.getRenameActions().clear();
	}
	
	public ActiveFileTableModel getActiveFileTableModel() {
		return activeFileTableModel;
	}
	
	public RenameActionListModel getRenameActionListModel() {
		return renameActionListModel;
	}
	
	public void addRenameAction(RenameAction renameAction) {
        renameActionListModel.addElement(renameAction);
	}

    public void removeRenameAction(RenameAction renameAction) {
        renameActionListModel.removeElement(renameAction);
    }
	
	public void tryRenameActiveFiles() throws ActiveFileException {
        renameActionListModel.addElement(pendingRenameAction);
		for(ActiveFile activeFile: activeFiles) {
            try {
                activeFile.tryRename(renameActionListModel.getRenameActions());
            } catch (ActiveFileException e) {
                renameActionListModel.removeElement(pendingRenameAction);
                throw e;
            }
		}
        renameActionListModel.removeElement(pendingRenameAction);
        activeFileTableModel.fireTableDataChanged();
	}
	
	public void renameActiveFiles() throws ActiveFileException {
		for(ActiveFile activeFile: activeFiles) {
            activeFile.rename(renameActionListModel.getRenameActions());
		}
        activeFileTableModel.fireTableDataChanged();
	}

    public void setPendingRenameAction(RenameAction pendingRenameAction) {
        this.pendingRenameAction = pendingRenameAction;
    }
}
