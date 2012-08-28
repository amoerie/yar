package domain;

import domain.action.RenameAction;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

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
