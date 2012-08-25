package gui;

import domain.ActiveFileException;
import domain.action.RenameAction;
import gui.action.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.sun.media.sound.ModelAbstractChannelMixer;

import domain.RenameModel;
import gui.listener.ExceptionListener;
import gui.listener.RenameActionListener;
import util.GBC;

/**
 * @author moerie
 */
public class EditorPanel extends JPanel implements RenameActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5563598465647704890L;
	
	private List<RenameActionPanel> renameActionPanels;
	private JTabbedPane				renameActionsTabbedPane;
    private JButton                 addButton;
    private JButton                 removeButton;
	private JList					plannedRenameActions;
    private RenameAction            pendingAction;
	
	private RenameModel model;
    private ExceptionListener exceptionListener;
	
	public EditorPanel(RenameModel model, ExceptionListener exceptionListener) {
		this.model = model;
        this.exceptionListener = exceptionListener;
		createComponents();
		addComponents();
        addListeners();
	}
	
	private void createComponents() {		
		// Fill action panels
		renameActionPanels = new ArrayList<>();
		renameActionPanels.add(new InsertActionPanel(this));
		renameActionPanels.add(new RemoveActionPanel(this));
		renameActionPanels.add(new ReplaceActionPanel(this));
        renameActionPanels.add(new MoveActionPanel(this));
		
		// Populate tabbed pane
		renameActionsTabbedPane = new JTabbedPane();
		for(RenameActionPanel renameActionPanel: renameActionPanels)
			renameActionsTabbedPane.add(renameActionPanel.getTitle(), renameActionPanel);

        addButton   = new JButton("+");
        removeButton= new JButton("-");
		
		plannedRenameActions = new JList(model.getRenameActionListModel());
	}
	
	private void addComponents() {
        this.setLayout(new GridBagLayout());

        this.add(renameActionsTabbedPane,
                new GBC(0,0).setWeight(0.4,1).setFill(GBC.BOTH));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        buttonPanel.add(addButton, new GBC(0,0).setInsets(5));
        buttonPanel.add(removeButton, new GBC(0,1).setInsets(5));

        this.add(buttonPanel,
                new GBC(1,0).setFill(GBC.BOTH).setInsets(5));

        this.add(new JScrollPane(plannedRenameActions),
                new GBC(2,0).setWeight(0.6, 1).setFill(GBC.BOTH).setInsets(0,5,0,0));
	}

    private void addListeners() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RenameActionPanel selectedActionPanel = (RenameActionPanel) renameActionsTabbedPane.getSelectedComponent();
                model.addRenameAction(selectedActionPanel.getRenameAction());
                selectedActionPanel.reset();
                model.setPendingRenameAction(selectedActionPanel.getRenameAction());
                try {
                    model.tryRenameActiveFiles();
                } catch (ActiveFileException e1) {
                    exceptionListener.notifyExceptionOccurred(e1);
                }
            }
        });
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RenameAction action = (RenameAction) plannedRenameActions.getSelectedValue();
                model.removeRenameAction(action);
                try {
                    model.tryRenameActiveFiles();
                } catch (ActiveFileException e1) {
                    exceptionListener.notifyExceptionOccurred(e1);
                }
            }
        });

        renameActionsTabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent event) {
                RenameActionPanel selectedRenameActionPanel =
                        ((RenameActionPanel) renameActionsTabbedPane.getSelectedComponent());
                pendingAction = selectedRenameActionPanel.getRenameAction();
                model.setPendingRenameAction(pendingAction);
                try {
                    model.tryRenameActiveFiles();
                } catch (ActiveFileException exception) {
                    exceptionListener.notifyExceptionOccurred(exception);
                }
            }
        });
    }

    @Override
    public void notifyRenameActionChanged(RenameAction renameAction) {
        model.setPendingRenameAction(renameAction);
        try {
            model.tryRenameActiveFiles();
        } catch (ActiveFileException e) {
            exceptionListener.notifyExceptionOccurred(e);
        }
    }
}
