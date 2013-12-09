package jirc.protocol.servereevent;

public class ServerInviteOnlyEvent {

    String channelName;

    public ServerInviteOnlyEvent(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }
}
