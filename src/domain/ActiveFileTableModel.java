package domain;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * @author moerie
 */
public class ActiveFileTableModel extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2923369251595861139L;
	private List<ActiveFile> activeFiles;
	
	private final String[] COLUMNS = {"Old file name", "New file name"};
	
	public ActiveFileTableModel(List<ActiveFile> activeFiles) {
		super();
		this.activeFiles = activeFiles;
	}

	@Override
	public int getColumnCount() {
		return COLUMNS.length;
	}

	@Override
	public int getRowCount() {
		return activeFiles.size();
	}

	@Override
	public Object getValueAt(int row, int column) {
		switch(column) {
			case 0: return activeFiles.get(row).getOldFileName();
			case 1: return activeFiles.get(row).getNewFileName();
			default: return null;
		}
	}

	@Override
	public String getColumnName(int column) {
		return COLUMNS[column];
	}	
	
	public void setActiveFiles(List<ActiveFile> activeFiles) {
		this.activeFiles = activeFiles;
		fireTableDataChanged();
	}
	
}
