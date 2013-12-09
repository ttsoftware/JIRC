package jirc.service;

import jirc.model.Channel;
import jirc.model.ChannelStatus;
import jirc.model.User;

import java.util.HashMap;

public class ChannelService {

    final private static HashMap<String, Channel> channels = new HashMap<>();

    public static void message(String channelName, String message) {
        Channel channel = getChannel(channelName);
        channel.getChannelPanel().appendMessage(message + "\n");
    }

    public static void message(Channel channel, String message) {
        channel.getChannelPanel().appendMessage(message + "\n");
    }

    public static Channel getChannel(String channelName) {
        return ChannelService.channels.get(channelName);
    }

    public static void addChannel(Channel channel) {
        ChannelService.channels.put(channel.getChannelName(), channel);
    }

    public static void addUser(String username, String channelName) {
        Channel channel = getChannel(channelName);
        ChannelStatus status = getUserStatus(username);

        if (channel != null &&
            status != null) {

            channel.getChannelPanel().addUserToView(username);
        }
    }

    public static Channel addChannel(String channelName) {
        Channel channel = new Channel(channelName);
        ChannelService.channels.put(channelName, channel);
        return channel;
    }

    public static ChannelStatus getUserStatus(String username) {

        String ident = username.substring(0, 1);

        switch (ident) {
            case "+":
                return ChannelStatus.VOICE;
            case "%":
                return ChannelStatus.HALFOPERATOR;
            case "@":
                return ChannelStatus.OPERATOR;
            default:
                return ChannelStatus.NORMAL;
        }
    }
}
