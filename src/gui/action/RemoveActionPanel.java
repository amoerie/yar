package gui.action;

import domain.action.RemoveAction;
import gui.listener.RenameActionListener;
import util.GBC;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * @author moerie
 */
public class RemoveActionPanel extends RenameActionPanel<RemoveAction> {

    private JLabel 		    startPositionLabel;
    private JSpinner	    startPositionSpinner;
    private JLabel          countLabel;
    private JSpinner	    countSpinner;
    private JRadioButton    fromBeginningRadioButton;
    private JRadioButton    fromEndRadioButton;
    private ButtonGroup     fromButtonGroup;

    private final String BEGIN = "begin";
    private final String END = "end";

	/**
	 * 
	 */
	private static final long serialVersionUID = 3670926034131060015L;

    public RemoveActionPanel(RenameActionListener renameActionListener) {
        super(renameActionListener);
    }

    @Override
    protected void createComponents() {
        startPositionLabel  = new JLabel("Start position");
        startPositionSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 999, 1));

        countLabel = new JLabel("Count");
        countSpinner = new JSpinner(new SpinnerNumberModel(0,0,999,1));

        fromBeginningRadioButton = new JRadioButton("From beginning", true);
        fromBeginningRadioButton.setActionCommand(BEGIN);
        fromEndRadioButton = new JRadioButton("From end", false);
        fromEndRadioButton.setActionCommand(END);
        fromButtonGroup = new ButtonGroup();
        fromButtonGroup.add(fromBeginningRadioButton);
        fromButtonGroup.add(fromEndRadioButton);
    }

    @Override
    protected void addComponents() {
        this.setLayout(new GridBagLayout());
        this.add(fromBeginningRadioButton,
                new GBC(0,0).setInsets(5).setAnchor(GBC.LINE_END));
        this.add(fromEndRadioButton,
                new GBC(1,0).setInsets(5).setAnchor(GBC.LINE_START));

        this.add(startPositionLabel,
                new GBC(0,1).setAnchor(GBC.LINE_END).setInsets(5));
        this.add(startPositionSpinner,
                new GBC(1,1).setAnchor(GBC.LINE_START).setWeight(1,0).setInsets(5));

        this.add(countLabel,
                new GBC(0,2).setAnchor(GBC.LINE_END).setInsets(5));
        this.add(countSpinner,
                new GBC(1,2).setAnchor(GBC.LINE_START).setWeight(1,0).setInsets(5));
    }

    @Override
    protected void layoutComponents() {
        fromBeginningRadioButton.setMnemonic(KeyEvent.VK_B);
        fromEndRadioButton.setMnemonic(KeyEvent.VK_E);

        startPositionLabel.setPreferredSize(new Dimension(100,25));
        countLabel.setPreferredSize(new Dimension(100,25));
    }

    @Override
    protected void addListeners() {
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

        countSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                renameAction.setCount(Integer.parseInt(countSpinner.getValue().toString()));
                changed();
            }
        });

        startPositionSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                renameAction.setStart(Integer.parseInt(startPositionSpinner.getValue().toString()));
                changed();
            }
        });
    }

    @Override
    public void initializeRenameAction() {
        renameAction = new RemoveAction(0, 0, true);
    }

    @Override
    protected void resetInputFields() {
        countSpinner.setValue(renameAction.getCount());
        startPositionSpinner.setValue(renameAction.getStart());
        setRadioButtonSelected(renameAction.isFromBeginning(), fromBeginningRadioButton, fromEndRadioButton);
    }

    @Override
    public String getTitle() {
        return "Remove";  //To change body of implemented methods use File | Settings | File Templates.
    }

}
