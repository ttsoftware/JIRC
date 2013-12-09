/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jirc.ui;

import jirc.service.InputService;
import org.jdesktop.swingx.JXList;

import javax.swing.*;
import java.awt.*;

/**
 * @author troels
 */
public class ChannelPanel extends JPanel {

    private JTextField inputTextField;
    private JSplitPane splitPane;
    private IRCTextPane textArea;

    private JXList userListView;
    private DefaultListModel<String> userListModel;

    private String channelName;

    public ChannelPanel(String channelName) {
        this.channelName = channelName;

        initComponents();
    }

    private void initComponents() {

        Font font = new Font("Monospaced", 0, 12);

        inputTextField = new javax.swing.JTextField();
        splitPane = new javax.swing.JSplitPane();
        JScrollPane textAreaScrollPane = new JScrollPane();
        textArea = new IRCTextPane();
        textArea.setFont(font);

        ColorListCellRenderer renderer = new ColorListCellRenderer();

        userListModel = new DefaultListModel<String>();
        userListView = new JXList(userListModel);

        userListView.setComparator(new UserComparator());
        userListView.setAutoCreateRowSorter(true);
        userListView.toggleSortOrder();
        userListView.setCellRenderer(renderer);
        userListView.setFont(font);
        userListView.setBackground(Color.WHITE);
        userListView.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        userListView.setSelectedIndex(0);
        userListView.setVisibleRowCount(5);

        JScrollPane userAreaScrollPane = new JScrollPane(userListView);

        splitPane.setDividerLocation(470);
        textAreaScrollPane.setViewportView(textArea);

        splitPane.setLeftComponent(textAreaScrollPane);
        splitPane.setRightComponent(null);

        if (!channelName.equals("status")) {
            userAreaScrollPane.setViewportView(userListView);
            userAreaScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

            splitPane.setRightComponent(userAreaScrollPane);
        }

        inputTextField.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputTextFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);

        this.setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).
                        addGroup(
                                layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(
                                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(
                                                                inputTextField,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                608,
                                                                Short.MAX_VALUE
                                                        )
                                                        .addComponent(
                                                                splitPane,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                608,
                                                                Short.MAX_VALUE
                                                        )
                                                        .addGroup(
                                                                layout.createSequentialGroup()
                                                                        .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                503,
                                                                                Short.MAX_VALUE
                                                                        )
                                                        )
                                        )
                                        .addContainerGap()
                        )
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                          .addContainerGap()
                                          .addGroup(
                                                  layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                          )
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                          .addComponent(
                                                  splitPane,
                                                  javax.swing.GroupLayout.DEFAULT_SIZE,
                                                  260,
                                                  Short.MAX_VALUE
                                          )
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                          .addComponent(
                                                  inputTextField,
                                                  javax.swing.GroupLayout.PREFERRED_SIZE,
                                                  javax.swing.GroupLayout.DEFAULT_SIZE,
                                                  javax.swing.GroupLayout.PREFERRED_SIZE
                                          )
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                          .addContainerGap()
                        )
        );

    }

    private void inputTextFieldActionPerformed(java.awt.event.ActionEvent evt) {

        if (!"".equals(inputTextField.getText())) {

            InputService.interpret(inputTextField.getText(), channelName);
            inputTextField.setText("");
        }
    }

    public void appendMessage(String message) {
        textArea.append(message);
    }

    public void addUserToView(String username) {
        userListModel.addElement(username);
    }

    public void removeUserFromView(String username) {
        userListModel.removeElement(username);
    }
}
