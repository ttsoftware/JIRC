/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package troels.projects.jircc.protocol.servereevent;

/**
 *
 * @author troels
 */
public class ServerPrivmsgEvent {
    
    private String message;
    private String channel;
    private String user;

    public ServerPrivmsgEvent(String message, String channel, String user) {
        this.message = message;
        this.channel = channel;
        this.user = user;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
    
}
