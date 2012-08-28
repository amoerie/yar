package gui;

import domain.ActiveFileException;
import domain.RenameModel;
import gui.listener.ExceptionListener;
import util.GBC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author moerie
 */
public class YarFrame extends JFrame implements ExceptionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6905578737131940137L;
	
	private RenameModel model;
	
	private JLabel			fileListLabel;
    private JButton         renameButton;
	private FileListPanel 	fileListPanel;
	private EditorPanel editorPanel;

	public YarFrame() {
		this.setTitle("Yar - Yet Another Renamer (by moerie)");
		model = new RenameModel();
		createComponents();
		addComponents();
        addListeners();
	}

	private void createComponents() {
		fileListLabel 	= new JLabel("Drop your files below:");
		fileListPanel 	= new FileListPanel(model);
		editorPanel     = new EditorPanel(model, this);
        renameButton    = new JButton("Rename it!");

	}

	private void addComponents() {
		this.setLayout(new GridBagLayout());
		this.add(fileListLabel, new GBC(0,0).setAnchor(GBC.LINE_START).setInsets(5));
        this.add(renameButton,  new GBC(1,0).setAnchor(GBC.LINE_END).setInsets(5));
		this.add(fileListPanel, new GBC(0,1).setFill(GBC.BOTH).setWeight(1, 1).setInsets(5).setSpan(2,1));
		this.add(editorPanel, new GBC(0,2).setFill(GBC.BOTH).setWeight(1, 0).setInsets(5).setSpan(2,1));
	}

    private void addListeners() {
        renameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    model.renameActiveFiles();
                } catch (ActiveFileException e) {
                    notifyExceptionOccurred(e);
                }
            }
        });
    }

    @Override
    public void notifyExceptionOccurred(final Exception e) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ExceptionFrame(e);
            }
        });
    }
}
