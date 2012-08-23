package gui.action;

import domain.RenameModel;
import domain.action.ReplaceAction;
import gui.listener.RenameActionListener;
import util.GBC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @author moerie
 */
public class ReplaceActionPanel extends RenameActionPanel<ReplaceAction>{

    private JLabel targetLabel;
    private JTextField targetTextField;
    private JLabel replacementLabel;
    private JTextField replacementTextField;

	/**
	 * 
	 */
	private static final long serialVersionUID = 5419411107675064851L;

    public ReplaceActionPanel(RenameActionListener renameActionListener) {
        super(renameActionListener);
    }

    @Override
    protected void createComponents() {
        targetLabel = new JLabel("Replace");
        replacementLabel = new JLabel("With");

        targetTextField = new JTextField();
        replacementTextField = new JTextField();
    }

    @Override
    protected void addComponents() {
        this.setLayout(new GridBagLayout());
        this.add(targetLabel,
                new GBC(0,0).setInsets(5).setAnchor(GBC.LINE_END));
        this.add(targetTextField,
                new GBC(1,0).setWeight(1,0).setFill(GBC.HORIZONTAL).setInsets(5));

        this.add(replacementLabel,
                new GBC(0,1).setInsets(5).setAnchor(GBC.LINE_END));
        this.add(replacementTextField,
                new GBC(1,1).setWeight(1,0).setFill(GBC.HORIZONTAL).setInsets(5));
    }

    @Override
    protected void layoutComponents() {
        targetLabel.setPreferredSize(new Dimension(100, 25));
        replacementLabel.setPreferredSize(new Dimension(100, 25));

    }

    @Override
    protected void addListeners() {
        targetTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                renameAction.setTarget(targetTextField.getText());
                changed();
            }
        });

        replacementTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                renameAction.setReplacement(replacementTextField.getText());
                changed();
            }
        });
    }

    @Override
    public void initializeRenameAction() {
        renameAction = new ReplaceAction("","");
    }

    @Override
    public String getTitle() {
        return "Replace";
    }

}
