package jirc.service;

import jirc.model.Channel;

import java.util.HashMap;

public class ChannelService {

    private static HashMap<String, Channel> channels = new HashMap<>();

    public static Channel getChannel(String channelName) {
        return ChannelService.channels.get(channelName);
    }

    public static void addChannel(Channel channel) {
        ChannelService.channels.put(channel.getChannelName(), channel);
    }
}
