package domain;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.*;

import domain.action.RenameAction;

/**
 * @author moerie
 */
public class RenameActionListModel extends DefaultListModel<RenameAction> {
    public List<RenameAction> getRenameActions() {
        List<RenameAction> renameActions = new ArrayList<>();
        Enumeration<RenameAction> elements = elements();
        while(elements.hasMoreElements())
            renameActions.add(elements.nextElement());
        return renameActions;
    }


}
