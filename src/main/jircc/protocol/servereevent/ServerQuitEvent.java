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

    public ServerQuitEvent(String nickname) {
        this.nickname = nickname;
    }
    
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    
}
