/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jirc.protocol.servereevent;

/**
 *
 * @author troels
 */
public class ServerPartEvent {

    private String channel;
    private String nick;
    
    public ServerPartEvent(String channel, String nick) {
        
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

    public void setNick(String nick) {
        this.nick = nick;
    }
    
}
