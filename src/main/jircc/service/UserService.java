package troels.projects.jircc.service;

import troels.projects.jircc.model.ChannelStatus;
import troels.projects.jircc.model.User;
import troels.projects.jircc.model.Channel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserService {

    private static HashMap<String, User> users = new HashMap<>();

    public static ArrayList<User> getUsers(String channelName) {

        ArrayList<User> channelUsers = new ArrayList<>();

        // iterate all users
        for (Map.Entry<String, User> user : UserService.users.entrySet()) {
            // iterate all channels the user exists in
            for (Map.Entry<Channel, ChannelStatus> channel : user.getValue().getChannels().entrySet()) {
                // check if user exists in given channel
                if (channel.getKey().getChannelName().equals(channelName)) {
                    channelUsers.add(user.getValue());
                }
            }
        }

        return channelUsers;
    }

    public static void removeUser(User user) {
        User userToRemove = UserService.getUser(user.getUsername());
        UserService.users.remove(userToRemove);
    }

    public static void addUser(User user) {
        UserService.users.put(user.getUsername(), user);
    }

    public static User getUser(String username) {
        return UserService.users.get(username);
    }
}