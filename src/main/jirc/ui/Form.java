/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Form.java
 *
 * Created on May 5, 2011, 11:03:45 PM
 */
package jirc.ui;

import jirc.model.Channel;
import jirc.protocol.IRCConnection;
import jirc.protocol.clientevent.ClientEvent;
import jirc.protocol.clientevent.ClientPrivmsgEvent;
import jirc.protocol.servereevent.*;
import jirc.service.ChannelService;
import jirc.ui.CloseableTabbedPane.CloseableTabbedPane;
import jirc.ui.CloseableTabbedPane.TabCloseListener;
import org.bushe.swing.event.EventBus;
import org.bushe.swing.event.annotation.AnnotationProcessor;
import org.bushe.swing.event.annotation.EventSubscriber;

import javax.swing.*;

/**
 * @author troels
 */
public class Form extends javax.swing.JFrame implements Runnable {

    private IRCConnection c;
    private Thread socket;
    private String nickname;
    private String password;
    private CloseableTabbedPane tabbedPane;
    private javax.swing.JButton connect;
    private javax.swing.JLabel nicknameLabel;
    private javax.swing.JLabel passwordlabel;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JTextField usernameField;

    /**
     * Creates new form Form
     */
    public Form() {

        //maiby this line is not required?
        AnnotationProcessor.process(this);
        c = new IRCConnection();

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception ex) {
            System.out.println("SystemLookAndFeel generated exception: " + ex);
        }

        initComponents();
    }

    private void initComponents() {

        connect = new javax.swing.JButton();
        usernameField = new javax.swing.JTextField("jirctest");
        nicknameLabel = new javax.swing.JLabel("Username");
        passwordField = new javax.swing.JPasswordField("nothing");
        passwordlabel = new javax.swing.JLabel("Password");

        tabbedPane = new CloseableTabbedPane();
        tabbedPane.addTabCloseListener(new TabCloseListener() {

            @Override
            public boolean closeTab(int tabIndexToClose) {

                if (tabIndexToClose != tabbedPane.findTab("status")) {
                    // close the tab
                    EventBus.publish(new ClientEvent("PART " + tabbedPane.getTitleAt(tabIndexToClose)));
                    return true;
                }
                return false;
            }
        });


        Channel status = ChannelService.addChannel("status");

        tabbedPane.addTab("status", status.getPanel(), false);
        tabbedPane.setSelectedIndex(0);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        connect.setText("connect");
        connect.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(
                                layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(
                                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(
                                                                tabbedPane,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                608,
                                                                Short.MAX_VALUE
                                                        )
                                                        .addGroup(
                                                                layout.createSequentialGroup()
                                                                        .addComponent(nicknameLabel)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(
                                                                                usernameField,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                147,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE
                                                                        )
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(passwordlabel)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(
                                                                                passwordField,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                153,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE
                                                                        )
                                                                        .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                160,
                                                                                Short.MAX_VALUE
                                                                        )
                                                        )
                                                        .addGroup(
                                                                layout.createSequentialGroup()
                                                                        .addComponent(
                                                                                connect,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                105,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE
                                                                        )
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
                        .addGroup(
                                layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(
                                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(nicknameLabel)
                                                        .addComponent(
                                                                usernameField,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE
                                                        )
                                                        .addComponent(passwordlabel)
                                                        .addComponent(
                                                                passwordField,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE
                                                        )
                                        )
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(
                                                tabbedPane,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                260,
                                                Short.MAX_VALUE
                                        )
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(connect)
                                        .addContainerGap()
                        )
        );

        pack();
    }

    private void connectActionPerformed(java.awt.event.ActionEvent evt) {

        ChannelService.appendMessage("status", "");

        final Form threadContent = this;

        socket = null;
        socket = new Thread(threadContent);
        socket.setDaemon(true);
        socket.start();
    }

    @Override
    public void run() {

        nickname = usernameField.getText();
        password = passwordField.getPassword().toString();

        c = new IRCConnection();

        c.setHost("chat.freenode.net");
        c.setPort(6667);

        c.setNickname(nickname);
        c.setPassword(password);

        c.connect();
    }

    // Standard non-filtered server input handling
    @EventSubscriber(eventClass = ServerEvent.class)
    public void onServerEvent(ServerEvent e) {
        ChannelService.appendMessage("status", e.getResponse().getResponse());
    }

    /* 
     * Join events generated on server is handled here. 
     * When client joins a channel, a corresponding event is generated on the server
     * As such, join-events generated by the client is also handled here.
     */
    @EventSubscriber(eventClass = ServerJoinEvent.class)
    public void onServerJoinEvent(ServerJoinEvent e) {

        if (e.getNick().equals(nickname)) {
            // client joined
            Channel channel = ChannelService.addChannel(e.getChannelName());
            tabbedPane.addTab(e.getChannelName(), channel.getPanel(), true);
        }
        else {
            // someone else joined
            ChannelService.appendMessage(e.getChannelName(), e.getNick() + " has joined " + e.getChannelName());
            ChannelService.getChannel(e.getChannelName()).getPanel().addUserToView(e.getNick());
        }
    }

    /* 
     * Part events generated on server is handled here. 
     * When client parts a channel, a corresponding event is generated on the server
     * As such, part-events generated by the client is also handled here.
     */
    @EventSubscriber(eventClass = ServerPartEvent.class)
    public void onServerPartEvent(ServerPartEvent e) {

        if (e.getNick().equals(nickname)) { // client parted

            int index = tabbedPane.findTab(e.getChannel());
            tabbedPane.removeTabAt(index);
        }
        else {
            // someone else parted
            ChannelService.appendMessage(e.getChannel(), e.getNick() + " has left the channel.");
            ChannelService.removeUser(e.getNick(), e.getChannel());
        }
    }

    @EventSubscriber(eventClass = ServerQuitEvent.class)
    public void onServerQuitEvent(ServerQuitEvent e) {

        if (e.getNickname().equals(nickname)) {
            // client quit
            tabbedPane.removeAll();
            c.cleanUp();
        }
        else {
            // someone else quit
            ChannelService.appendMessage("status", e.getNickname() + " has quit the server.");
            ChannelService.removeFromAllChannels(e.getNickname());
        }
    }

    @EventSubscriber(eventClass = ServerPrivmsgEvent.class)
    public void onServerPrivmsgEvent(ServerPrivmsgEvent e) {
        ChannelService.appendMessage(e.getChannel(), e.getUser(), e.getMessage());
    }

    // A notice to client
    @EventSubscriber(eventClass = ServerNoticeEvent.class)
    public void onServerNoticeEvent(ServerNoticeEvent e) {
        // We need to know the currently focused tab
        // e.getNick is not the username
        ChannelService.appendMessage(tabbedPane.getTitleAt(tabbedPane.getSelectedIndex()), "Notice from " + e.getNick() + ": " + e.getMessage());
    }

    // any kind of input to server, which is not PRIVMSG
    @EventSubscriber(eventClass = ClientEvent.class)
    public void onClientEvent(ClientEvent e) {
        c.doServerCall(e.getRequest());
    }

    //handling of both server and client PRIVMSG
    @EventSubscriber(eventClass = ClientPrivmsgEvent.class)
    public void onClientPrivmsgEvent(ClientPrivmsgEvent e) {
        c.doServerCall("PRIVMSG " + e.getChannel() + " :" + e.getMessage());
        ChannelService.appendMessage(e.getChannel(), nickname, e.getMessage());
    }

    @EventSubscriber(eventClass = ServerInviteOnlyEvent.class)
    public void onServerInviteOnlyEvent(ServerInviteOnlyEvent e) {

        ChannelService.appendMessage(tabbedPane.getTitleAt(tabbedPane.getSelectedIndex()), e.getChannelName() + " has invite only mode set (+i), and cannot be joined.");
    }

    @EventSubscriber(eventClass = ServerUsersEvent.class)
    public void onServerUsersEvent(ServerUsersEvent e) {

        String channelName = e.getChannel();
        String[] users = e.getUsers();

        for (String user : users) {
            ChannelService.addUser(user, channelName);
        }
    }
}
