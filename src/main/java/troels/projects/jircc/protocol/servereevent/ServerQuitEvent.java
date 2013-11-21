/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package troels.projects.jircc.protocol.servereevent;

/**
 *
 * @author troels
 */
public class ServerQuitEvent {
    
    String nickname;
    String channel;

    public ServerQuitEvent(String channel, String nickname) {
        
        this.nickname = nickname;
        this.channel = channel;
    }
    
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
    
}
