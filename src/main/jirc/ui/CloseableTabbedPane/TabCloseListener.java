/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jirc.ui.CloseableTabbedPane;

import java.util.EventListener;

/**
 *
 * @author troels
 */
public interface TabCloseListener extends EventListener {

    /**
     * Informs all <code>CloseableTabbedPaneListener</code>s when a tab should be
     * closed
     * @param tabIndexToClose the index of the tab which should be closed
     * @return true if the tab can be closed, false otherwise
     */
    boolean closeTab(int tabIndexToClose);
}
