/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package troels.projects.jircc.ui.CloseableTabbedPane;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.plaf.basic.BasicButtonUI;

/**
 *
 * @author troels
 */
public class CloseButton extends JButton {

    public CloseButton() {
        
        int size = 17;
        setPreferredSize(new Dimension(size, size));
        setToolTipText("close this tab");
        //Make the button looks the same for all Laf's
        setUI(new BasicButtonUI());
        //Make it transparent
        setContentAreaFilled(false);
        //No need to be focusable
        setFocusable(false);
        setBorder(BorderFactory.createEtchedBorder());
        setBorderPainted(false);
        //Making nice rollover effect
        //we use the same listener for all buttons
        addMouseListener(buttonMouseListener);
        setRolloverEnabled(true);
    }
    
    private final static MouseListener buttonMouseListener = new MouseAdapter() {

        @Override
        public void mouseEntered(MouseEvent e) {
            
            Component component = e.getComponent();
            
            if (component instanceof AbstractButton) {
            
                AbstractButton button = (AbstractButton) component;
                button.setBorderPainted(true);
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            
            Component component = e.getComponent();
            
            if (component instanceof AbstractButton) {
            
                AbstractButton button = (AbstractButton) component;
                button.setBorderPainted(false);
            }
        }
    };

    //we don't want to update UI for this button
    @Override
    public void updateUI() {
    }

    //paint the cross
    @Override
    protected void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        
        //shift the image for pressed buttons
        if (getModel().isPressed()) {
            g2.translate(1, 1);
        }
        
        g2.setStroke(new BasicStroke(1));
        
        g2.setColor(Color.BLACK);
        
        if (getModel().isRollover()) {
        
            g2.setColor(Color.RED);
        }
        
        int delta = 4;
        
        g2.drawLine(delta, delta, getWidth() - delta - 2, getHeight() - delta - 2);
        g2.drawLine(getWidth() - delta - 2, delta, delta, getHeight() - delta - 2);
        g2.dispose();
    }
}
