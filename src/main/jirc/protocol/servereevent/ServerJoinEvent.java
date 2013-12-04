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

    private String channel;
    private String nick;

    public ServerJoinEvent(String channel, String nick) {
        
        this.channel = channel;
        this.nick = nick;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getNick() {
        return nick;
    }

    public void setUser(String user) {
        this.nick = user;
    }
    
}
