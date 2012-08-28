package gui.action;

import domain.action.MoveAction;
import gui.listener.RenameActionListener;
import util.GBC;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author moerie
 */
public class MoveActionPanel extends RenameActionPanel<MoveAction> {

    private JPanel          fromPanel;
    private JRadioButton    fromFromBeginningRadioButton;
    private JRadioButton    fromFromEndRadioButton;
    private ButtonGroup     fromButtonGroup;
    private JLabel          fromPositionLabel;
    private JSpinner	    fromPositionSpinner;
    private JLabel          countLabel;
    private JSpinner	    countSpinner;

    private JPanel          toPanel;
    private JRadioButton    toFromBeginningRadioButton;
    private JRadioButton    toFromEndRadioButton;
    private ButtonGroup     toButtonGroup;
    private JLabel          toPositionLabel;
    private JSpinner	    toPositionSpinner;

    private final String BEGIN = "begin";
    private final String END = "end";

    public MoveActionPanel(RenameActionListener renameActionListener) {
        super(renameActionListener);
    }

    @Override
    protected void createComponents() {
        /* FROM */
        fromPanel = new JPanel();
        fromFromBeginningRadioButton = new JRadioButton("From beginning", true);
        fromFromBeginningRadioButton.setActionCommand(BEGIN);

        fromFromEndRadioButton = new JRadioButton("From end", false);
        fromFromEndRadioButton.setActionCommand(END);

        fromButtonGroup = new ButtonGroup();
        fromButtonGroup.add(fromFromBeginningRadioButton);
        fromButtonGroup.add(fromFromEndRadioButton);

        fromPositionLabel  = new JLabel("From position");
        fromPositionSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 999, 1));

        countLabel = new JLabel("Count");
        countSpinner = new JSpinner(new SpinnerNumberModel(0,0,999,1));

        /* TO */
        toPanel = new JPanel();
        toFromBeginningRadioButton = new JRadioButton("From beginning", true);
        toFromBeginningRadioButton.setActionCommand(BEGIN);

        toFromEndRadioButton = new JRadioButton("From end", false);
        toFromEndRadioButton.setActionCommand(END);

        toButtonGroup = new ButtonGroup();
        toButtonGroup.add(toFromBeginningRadioButton);
        toButtonGroup.add(toFromEndRadioButton);

        toPositionLabel  = new JLabel("To position");
        toPositionSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 999, 1));
    }

    @Override
    protected void addComponents() {
        this.setLayout(new GridBagLayout());

        int row = 0;
        fromPanel.setLayout(new GridBagLayout());

        fromPanel.add(fromFromBeginningRadioButton,
                new GBC(0, ++row).setInsets(5).setAnchor(GBC.LINE_END));
        fromPanel.add(fromFromEndRadioButton,
                new GBC(1, row).setInsets(5).setAnchor(GBC.LINE_START));

        fromPanel.add(fromPositionLabel,
                new GBC(0, ++row).setAnchor(GBC.LINE_END).setInsets(5));
        fromPanel.add(fromPositionSpinner,
                new GBC(1, row).setAnchor(GBC.LINE_START).setWeight(1, 0).setInsets(5));

        fromPanel.add(countLabel,
                new GBC(0, ++row).setAnchor(GBC.LINE_END).setInsets(5));
        fromPanel.add(countSpinner,
                new GBC(1, row).setAnchor(GBC.LINE_START).setWeight(1, 0).setInsets(5));

        row = 0;
        toPanel.setLayout(new GridBagLayout());
        toPanel.add(toFromBeginningRadioButton,
                new GBC(0, ++row).setInsets(5).setAnchor(GBC.LINE_END));
        toPanel.add(toFromEndRadioButton,
                new GBC(1, row).setInsets(5).setAnchor(GBC.LINE_START));

        toPanel.add(toPositionLabel,
                new GBC(0, ++row).setAnchor(GBC.LINE_END).setInsets(5));
        toPanel.add(toPositionSpinner,
                new GBC(1, row).setAnchor(GBC.LINE_START).setWeight(1, 0).setInsets(5));

        this.add(fromPanel,
                new GBC(0,0).setWeight(1,0).setFill(GBC.HORIZONTAL).setAnchor(GBC.LINE_START));
        this.add(toPanel,
                new GBC(0,1).setWeight(1,0).setFill(GBC.HORIZONTAL).setAnchor(GBC.LINE_START));
    }

    @Override
    protected void layoutComponents() {
        fromPanel.setBorder(BorderFactory.createTitledBorder("From"));
        toPanel.setBorder(BorderFactory.createTitledBorder("To"));
    }

    @Override
    protected void addListeners() {
        ActionListener fromListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String actionCommand = fromButtonGroup.getSelection().getActionCommand();
                switch(actionCommand) {
                    case BEGIN:     renameAction.setFromFromBeginning(true);    break;
                    case END:       renameAction.setFromFromBeginning(false);   break;
                }
                changed();
            }
        };
        fromFromBeginningRadioButton.addActionListener(fromListener);
        fromFromEndRadioButton.addActionListener(fromListener);

        countSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                renameAction.setCount(Integer.parseInt(countSpinner.getValue().toString()));
                changed();
            }
        });

        fromPositionSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                renameAction.setFromPosition(Integer.parseInt(fromPositionSpinner.getValue().toString()));
                changed();
            }
        });

        ActionListener toListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String actionCommand = toButtonGroup.getSelection().getActionCommand();
                switch(actionCommand) {
                    case BEGIN:     renameAction.setToFromBeginning(true);    break;
                    case END:       renameAction.setToFromBeginning(false);   break;
                }
                changed();
            }
        };
        toFromBeginningRadioButton.addActionListener(toListener);
        toFromEndRadioButton.addActionListener(toListener);

        toPositionSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                renameAction.setToPosition(Integer.parseInt(toPositionSpinner.getValue().toString()));
                changed();
            }
        });
    }

    @Override
    public void initializeRenameAction() {
        renameAction = new MoveAction(true, 0, 0, true, 0);
    }

    @Override
    public String getTitle() {
        return "Move";
    }

    @Override
    public void resetInputFields() {
        countSpinner.setValue(renameAction.getCount());
        fromPositionSpinner.setValue(renameAction.getFromPosition());
        toPositionSpinner.setValue(renameAction.getToPosition());
        setRadioButtonSelected(renameAction.isFromFromBeginning(), fromFromBeginningRadioButton, fromFromEndRadioButton);
        setRadioButtonSelected(renameAction.isToFromBeginning(), toFromBeginningRadioButton, toFromEndRadioButton);
    }
}
