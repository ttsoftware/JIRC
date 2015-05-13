package jirc.ui.CloseableTabbedPane;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author troels
 */
public class TabComponent extends JPanel {

    private CloseableTabbedPane pane;
    
    public TabComponent(final CloseableTabbedPane pane, final String title, boolean closeAble) {
    
        super(new FlowLayout(FlowLayout.LEFT, 0, 0));

        CloseButton closeButton = new CloseButton();
        
        closeButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                pane.fireCloseTab(pane.findTab(title));
            }
        });
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setBackground(this.getBackground());

        setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        add(titleLabel);

        if (closeAble) {

            add(closeButton);
        }
    }
}
