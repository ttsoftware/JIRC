package jirc.model;

import java.io.Serializable;
import java.util.HashMap;

public class User implements Serializable {

    private String username;
    private HashMap<Channel, ChannelStatus> channels;

    public User(String username) {
        this.username = username;
    }

    public User(String username, HashMap<Channel, ChannelStatus> channels) {
        this.username = username;
        this.channels = channels;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public HashMap<Channel, ChannelStatus> getChannels() {
        return channels;
    }

    public void addChannel(Channel channel, ChannelStatus status) {
        channels.put(channel, status);
    }

    public void setChannels(HashMap<Channel, ChannelStatus> channels) {
        this.channels = channels;
    }
}
