package gui;

import java.awt.GridBagLayout;
import java.io.File;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import util.FileDrop;
import util.GBC;
import domain.*;

/**
 * @author moerie
 */
public class FileListPanel extends JPanel  {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7123222531329500727L;
	private JTable fileListTable;
	private RenameModel model;
	
	public FileListPanel(RenameModel model) {
		this.model = model;
		createComponents();
		addComponents();
		addListeners();
	}


	private void createComponents() {
		fileListTable		= new JTable(model.getActiveFileTableModel());		
	}
	
	private void addComponents() {
		this.setLayout(new GridBagLayout());
		this.add(new JScrollPane(fileListTable), new GBC(0, 0).setFill(GBC.BOTH).setWeight(1, 1));
	}
	
	private void addListeners() {
		new FileDrop(this, new FileDrop.Listener() {
			@Override
			public void filesDropped(File[] files) {
				model.setFiles(files);
			}
		});
	}
}
