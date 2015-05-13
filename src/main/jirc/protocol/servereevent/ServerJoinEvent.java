/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jirc.protocol.servereevent;


/**
 *
 * @author troels
 */
public class ServerJoinEvent {

    private String channelName;
    private String nick;

    public ServerJoinEvent(String channelName, String nick) {
        
        this.channelName = channelName;
        this.nick = nick;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getNick() {
        return nick;
    }

    public void setUser(String user) {
        this.nick = user;
    }
    
}
