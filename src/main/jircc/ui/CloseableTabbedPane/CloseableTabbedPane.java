/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package troels.projects.jircc.ui.CloseableTabbedPane;

import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JTabbedPane;
import javax.swing.event.EventListenerList;

/**
 *
 * @author troels
 */
public class CloseableTabbedPane extends JTabbedPane {

    private ArrayList<TabComponent> tabComponents = new ArrayList<TabComponent> ();
    
    /**
     * The <code>EventListenerList</code>.
     */
    private EventListenerList listenerList = new EventListenerList();

    /**
     * Creates a new instance of <code>troels.projects.jircc.ui.CloseableTabbedPane</code>
     */
    public CloseableTabbedPane() {
        super();
        init();
    }

    /**
     * Initializes the <code>troels.projects.jircc.ui.CloseableTabbedPane</code>
     * 
     * @param horizontalTextPosition the horizontal position of the text (e.g.
     * SwingUtilities.TRAILING or SwingUtilities.LEFT)
     */
    private void init() {
        
        listenerList = new EventListenerList();
    }
    
    public void addTab(final String title, Component component, boolean closeAble) {

        addTab(title, component);

        TabComponent tab = new TabComponent(this, title, closeAble);
        
        tabComponents.add(tab);
        
        setTabComponentAt(getTabCount() - 1, tab);
    }

    public ArrayList<TabComponent> getTabComponents() {
        return tabComponents;
    }

    public void setTabComponents(ArrayList<TabComponent> tabComponents) {
        this.tabComponents = tabComponents;
    }
    
    /**
     * Adds an <code>CloseableTabbedPaneListener</code> to the tabbedpane.
     * @param l the <code>CloseableTabbedPaneListener</code> to be added
     */
    public void addTabCloseListener(TabCloseListener l) {

        listenerList.add(TabCloseListener.class, l);
    }

    /**
     * Removes an <code>CloseableTabbedPaneListener</code> from the tabbedpane.
     * @param l the listener to be removed
     */
    public void removeTabCloseListener(TabCloseListener l) {

        listenerList.remove(TabCloseListener.class, l);
    }

    /**
     * Returns an array of all the <code>SearchListener</code>s added to this
     * <code>SearchPane</code> with addSearchListener().
     * @return all of the <code>SearchListener</code>s added or an empty array if
     * no listeners have been added
     */
    public TabCloseListener[] getTabCloseListener() {

        return listenerList.getListeners(TabCloseListener.class);
    }

    /**
     * Notifies all listeners that have registered interest for notification on
     * this event type.
     * @param tabIndexToClose the index of the tab which should be closed
     * @return true if the tab can be closed, false otherwise
     */
    protected boolean fireCloseTab(int tabIndexToClose) {

        boolean closeit = true;
        // Guaranteed to return a non-null array
        Object[] listeners = listenerList.getListenerList();

        for (Object i : listeners) {

            if (i instanceof TabCloseListener) {

                if (!((TabCloseListener) i).closeTab(tabIndexToClose)) {

                    closeit = false;
                    break;
                }
            }
        }
        return closeit;
    }

    /**
     * Finds the tab with String <code>title</code>
     * @param title the title of the tab to be found
     * returns the index of the tab.
     */
    public int findTab(String title) {

        int totalTabs = this.getTabCount();

        for (int i = 0; i < totalTabs; i++) {

            String tabTitle = this.getTitleAt(i);

            if (tabTitle.equalsIgnoreCase(title)) {

                return i;
            }
        }
        return -1;
    }
}