package jirc.model;

import jirc.ui.ChannelPanel;

import java.io.Serializable;

public class Channel implements Serializable {

    private String channelName;
    private ChannelPanel panel;

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

    public ChannelPanel getChannelPanel() {
        return panel;
    }

    public void setPanel(ChannelPanel panel) {
        this.panel = panel;
    }
}
