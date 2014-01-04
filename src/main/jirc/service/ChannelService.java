package jirc.service;

import jirc.model.Channel;
import jirc.model.ChannelStatus;

import java.util.HashMap;
import java.util.Map;

public class ChannelService {

    final private static HashMap<String, Channel> channels = new HashMap<>();

    public static void appendMessage(String channelName, String username, String message) {
        Channel channel = getChannel(channelName);

        System.out.println(getNameColor(username, channel));

        appendMessage(channel, getNameColor(username, channel) + ": " + message);
    }

    public static void appendMessage(String channelName, String message) {
        Channel channel = getChannel(channelName);
        appendMessage(channel, message);
    }

    public static void appendMessage(Channel channel, String message) {
        channel.getPanel().appendMessage(message + "\n");
    }

    public static Channel getChannel(String channelName) {
        return ChannelService.channels.get(channelName);
    }

    public static void addChannel(Channel channel) {
        ChannelService.channels.put(channel.getChannelName(), channel);
    }

    public static void addUser(String username, String channelName) {
        Channel channel = getChannel(channelName);
        ChannelStatus status = getChannelStatus(username);

        if (channel != null &&
            status != null) {

            channel.getPanel().addUserToView(username);
            channel.setUser(username, status);
        }
    }

    public static void removeUser(String username, String channelName) {
        Channel channel = getChannel(channelName);
        channel.getPanel().removeUserFromView(username);
        channel.removeUser(username);
    }

    public static void removeFromAllChannels(String username) {
        for (Map.Entry<String, Channel> entry : ChannelService.channels.entrySet()) {
            Channel channel = entry.getValue();
            channel.getPanel().removeUserFromView(username);
            channel.removeUser(username);
        }
    }

    public static Channel addChannel(String channelName) {
        Channel channel = new Channel(channelName);
        ChannelService.channels.put(channelName, channel);
        return channel;
    }

    public static String getNameColor(String username, Channel channel) {
        ChannelStatus status = channel.getUserStatus(username);
        char color = 0x01;

        if (status != null) {
            switch (status) {
                case OPERATOR:
                    color = 0x04;
                    break;
                case HALFOPERATOR:
                    color = 0x08;
                    break;
                case VOICE:
                    color = 0x0C;
                    break;
            }
        }

        return 0x03 + "" + color + "" + username + 0x03;
        //return 0x02 + username + 0x02;
    }

    public static ChannelStatus getChannelStatus(String username) {

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
