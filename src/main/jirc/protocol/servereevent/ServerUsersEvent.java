/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jirc.protocol.servereevent;


/**
 *
 * @author troels
 */
public class ServerUsersEvent {
    
    String[] users;
    String channel;

    public ServerUsersEvent(String channel, String[] users) {

        this.channel = channel;
        this.users = users;
    }

    public String[] getUsers() {
        return users;
    }

    public void setUsers(String[] users) {
        this.users = users;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
    
}
