package gui.action;

import domain.action.InsertAction;
import gui.listener.RenameActionListener;
import util.GBC;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @author moerie
 */
public class InsertActionPanel extends RenameActionPanel<InsertAction> {

	private JLabel insertPositionLabel;
	private JSpinner insertPositionSpinner;
    private JRadioButton    fromBeginningRadioButton;
    private JRadioButton    fromEndRadioButton;
    private ButtonGroup     fromButtonGroup;
	private JLabel		    insertionLabel;
	private JTextField	    insertionText;

    private final String BEGIN = "begin";
    private final String END = "end";

    public InsertActionPanel(RenameActionListener renameActionListener) {
        super(renameActionListener);
    }


    protected void createComponents() {
        fromBeginningRadioButton= new JRadioButton("From beginning", true);
        fromBeginningRadioButton.setActionCommand(BEGIN);
        fromEndRadioButton      = new JRadioButton("From end", false);
        fromEndRadioButton.setActionCommand(END);
        fromButtonGroup         = new ButtonGroup();
        fromButtonGroup.add(fromBeginningRadioButton);
        fromButtonGroup.add(fromEndRadioButton);
        insertPositionLabel = new JLabel("Start position: ");
        insertPositionSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 999, 1));
        insertionLabel			= new JLabel("Text to insert: ");
        insertionText			= new JTextField();
    }

    protected void addComponents() {
        this.setLayout(new GridBagLayout());
        this.add(fromBeginningRadioButton,
                new GBC(0,0).setInsets(5).setAnchor(GBC.LINE_END));
        this.add(fromEndRadioButton,
                new GBC(1,0).setInsets(5).setAnchor(GBC.LINE_START));
        this.add(insertPositionLabel,
                new GBC(0,1).setWeight(0, 0).setAnchor(GBC.LINE_END));
        this.add(insertPositionSpinner,
                new GBC(1,1).setWeight(1, 0).setAnchor(GBC.LINE_START));

        this.add(insertionLabel,
                new GBC(0,2).setWeight(0, 0).setAnchor(GBC.LINE_END));
        this.add(insertionText,
                new GBC(1,2).setWeight(1, 0).setAnchor(GBC.LINE_START).setFill(GBC.HORIZONTAL));
    }

    protected void layoutComponents() {
        insertPositionLabel.setPreferredSize(new Dimension(100, 25));
        insertionLabel.setPreferredSize(new Dimension(100,25));
    }

    @Override
    public void initializeRenameAction() {
        renameAction = new InsertAction(0,"", true);
    }

    @Override
    protected void addListeners() {
        insertPositionSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                renameAction.setInsertPosition(Integer.parseInt(insertPositionSpinner.getValue().toString()));
                changed();
            }
        });
        insertionText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                renameAction.setText(insertionText.getText());
                changed();
            }
        });
        ActionListener fromListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String actionCommand = fromButtonGroup.getSelection().getActionCommand();
                switch(actionCommand) {
                    case BEGIN:     renameAction.setFromBeginning(true);    break;
                    case END:       renameAction.setFromBeginning(false);   break;
                }
                changed();
            }
        };
        fromBeginningRadioButton.addActionListener(fromListener);
        fromEndRadioButton.addActionListener(fromListener);
    }
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4275350060399760081L;

	@Override
	public String getTitle() {
		return "Insert";
	}

    @Override
    public void resetInputFields() {
        insertPositionSpinner.setValue(renameAction.getInsertPosition());
        insertionText.setText(renameAction.getText());
        setRadioButtonSelected(renameAction.isFromBeginning(), fromBeginningRadioButton, fromEndRadioButton);
    }


}
