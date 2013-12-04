package jirc.model;

import jirc.ui.ChannelPanel;

public class Channel {

    private String channelName;
    private ChannelPanel channel;

    public Channel(String channelName, ChannelPanel channel) {
        this.channelName = channelName;
        this.channel = channel;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public ChannelPanel getChannelPanel() {
        return channel;
    }

    public void setChannel(ChannelPanel channel) {
        this.channel = channel;
    }
}
