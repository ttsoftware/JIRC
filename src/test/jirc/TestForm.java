package jirc;/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * jirc.TestForm.java
 *
 * Created on May 9, 2011, 3:08:44 PM
 */

import jirc.ui.IRCTextPane;

/**
 *
 * @author troels
 */
public class TestForm extends javax.swing.JFrame {

    /** Creates new form jirc.TestForm */
    public TestForm() {
        
        initComponents();
    }

    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        IRCTextPane pane = new IRCTextPane();

        String test = "";

        pane.append(test);

        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(null);
        jSplitPane1.setRightComponent(pane);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new TestForm().setVisible(true);
            }
        });
    }
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSplitPane jSplitPane1;
}
