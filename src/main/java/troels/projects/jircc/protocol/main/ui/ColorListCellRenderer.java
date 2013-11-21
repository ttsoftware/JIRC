package troels.projects.jircc.protocol.main.ui;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: troels
 * Date: 10/8/12
 * Time: 1:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class ColorListCellRenderer extends JLabel implements ListCellRenderer {

    private String match = null;
    private JList list;

    public ColorListCellRenderer(JList list) {
        setOpaque(true);
        this.list = list;
    }

    public Component getListCellRendererComponent(JList paramlist, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        setText(value.toString());
        if (value.toString().equals(match)) {
            setBackground(Color.WHITE);
            SwingWorker worker = new SwingWorker() {
                @Override
                public Object doInBackground() {
                    try {
                        Thread.sleep(5000);
                    }
                    catch (InterruptedException e) {
                        /*Who cares*/
                    }
                    return null;
                }

                @Override
                public void done() {
                    match = null;
                    list.repaint();
                }
            };
            worker.execute();
        }
        else {
            setBackground(Color.RED);
        }
        return this;
    }
}

