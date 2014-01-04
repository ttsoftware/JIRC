package jirc.model;

import jirc.ui.ChannelPanel;

import java.io.Serializable;
import java.util.HashMap;

public class Channel implements Serializable {

    private String channelName;
    private ChannelPanel panel;
    private HashMap<String, ChannelStatus> users = new HashMap<>();

    public Channel(String channelName, ChannelPanel panel) {
        this.channelName = channelName;
        this.panel = panel;
    }

    public Channel(String channelName) {
        this.channelName = channelName;
        this.panel = new ChannelPanel(channelName);
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public ChannelPanel getPanel() {
        return panel;
    }

    public void setPanel(ChannelPanel panel) {
        this.panel = panel;
    }

    public HashMap<String, ChannelStatus> getUsers() {
        return users;
    }

    public void setUser(String username, ChannelStatus status) {
        users.put(username, status);
    }

    public void removeUser(String username) {
        users.remove(username);
    }

    public ChannelStatus getUserStatus(String username) {
        return users.get(username);
    }
}
