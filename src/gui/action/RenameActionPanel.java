package gui.action;

import domain.action.RenameAction;
import gui.listener.RenameActionListener;

import javax.swing.*;

/**
 * @author moerie
 */
public abstract class RenameActionPanel<E extends RenameAction> extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5563598465647704890L;
    protected E renameAction;

    private RenameActionListener renameActionListener;

    protected RenameActionPanel(RenameActionListener renameActionListener) {
        this.renameActionListener = renameActionListener;
        createComponents();
        addComponents();
        layoutComponents();
        addListeners();
        initializeRenameAction();
        changed();
    }

    protected abstract void createComponents();
    protected abstract void addComponents();
    protected abstract void layoutComponents();
    protected abstract void addListeners();
    protected abstract void initializeRenameAction();
    protected abstract void resetInputFields();

	public abstract String getTitle();

    protected void changed() {
        renameActionListener.notifyRenameActionChanged(renameAction);
    }

    public RenameAction getRenameAction() {
        return renameAction;
    }

    /**
     * Re-initializes the rename action
     * and sets the appropriate input fields back to their defaults.
     */
    public void reset() {
        initializeRenameAction();
        resetInputFields();
    }

    protected void setRadioButtonSelected(boolean value, JRadioButton buttonIfTrue, JRadioButton buttonIfFalse) {
        buttonIfTrue.setSelected(value);
        buttonIfFalse.setSelected(!value);
    }
}
