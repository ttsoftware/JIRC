/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package troels.projects.jircc.protocol.main.ui;

import java.awt.Color;
import java.awt.Font;
import java.util.*;
import javax.swing.*;

import troels.projects.jircc.protocol.main.model.ChannelStatus;
import troels.projects.jircc.protocol.main.model.User;
import troels.projects.jircc.protocol.main.ui.logic.CommandInterpreter;

/**
 * @author troels
 */
public class Channel extends JPanel {

    private javax.swing.JTextField inputTextField;
    private javax.swing.JSplitPane splitPane;
    private IRCTextPane textArea;

    private JList userListView;
    private List<User> userList;
    private DefaultListModel userListModel;

    private String channelName;

    private String[] users;

    public Channel(String channelName) {

        this.channelName = channelName;

        initComponents();
    }

    private void initComponents() {

        Font font = new Font("Monospace", 0, 12);

        inputTextField = new javax.swing.JTextField();
        splitPane = new javax.swing.JSplitPane();
        JScrollPane textAreaScrollPane = new JScrollPane();
        textArea = new IRCTextPane();
        textArea.setFont(font);

        userList = new ArrayList<User>();
        userListModel = new DefaultListModel();

        userListView = new JList(userListModel);

        userListView.setFont(font);
        userListView.setBackground(Color.WHITE);
        userListView.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        userListView.setSelectedIndex(0);
        userListView.setVisibleRowCount(5);

        JScrollPane userAreaScrollPane = new JScrollPane(userListView);

        splitPane.setDividerLocation(470);
        textAreaScrollPane.setViewportView(textArea);

        splitPane.setLeftComponent(textAreaScrollPane);

        if (!channelName.equals("status")) {

            userAreaScrollPane.setViewportView(userListView);
            userAreaScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

            splitPane.setRightComponent(userAreaScrollPane);
        }
        else {

            splitPane.setRightComponent(null);
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
                        addGroup(layout.createSequentialGroup().
                                addContainerGap().
                                addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).
                                        addComponent(inputTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 608, Short.MAX_VALUE).
                                        addComponent(splitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 608, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 503, Short.MAX_VALUE)))
                                .addContainerGap()));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(splitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(inputTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addContainerGap()));

    }

    public void removeUser(User user) {

        userList.remove(user);
        userListModel.removeElement(user);
    }

    public void addUser(String username) {

        User user = new User(username);

        try {
            if (username.contains("@")) {
                user.addChannel(this, ChannelStatus.OPERATOR);
            }
            else if (username.contains("%")) {
                user.addChannel(this, ChannelStatus.HALFOPERATOR);
            }
            else if (username.contains("+")) {
                user.addChannel(this, ChannelStatus.VOICE);
            }
            else {
                user.addChannel(this, ChannelStatus.NORMAL);
            }
        }
        catch (Exception e) {
            e.printStackTrace(System.out);
        }

        userList.add(user);

        for (int i = 0; i < userList.size(); i++) {
            userListModel.insertElementAt(userList.get(i).getUsername(), i);
        }
    }

    private void inputTextFieldActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:

        if (!"".equals(inputTextField.getText())) {

            CommandInterpreter ci = new CommandInterpreter(inputTextField.getText(), channelName);
            inputTextField.setText("");
        }
    }

    public JTextField getInputTextField() {
        return inputTextField;
    }

    public void setInputTextField(JTextField inputTextField) {
        this.inputTextField = inputTextField;
    }

    public JSplitPane getSplitPane() {
        return splitPane;
    }

    public void setSplitPane(JSplitPane splitPane) {
        this.splitPane = splitPane;
    }

    public IRCTextPane getTextArea() {
        return textArea;
    }

    public void setTextArea(IRCTextPane textArea) {
        this.textArea = textArea;
    }

    public JList getUserListView() {
        return userListView;
    }

    public void setUserListView(JList userListView) {
        this.userListView = userListView;
    }

    public String[] getUsers() {
        return users;
    }

    public void setUsers(String[] users) {
        this.users = users;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public DefaultListModel getUserListModel() {
        return userListModel;
    }

    public void setUserListModel(DefaultListModel userListModel) {
        this.userListModel = userListModel;
    }
}
