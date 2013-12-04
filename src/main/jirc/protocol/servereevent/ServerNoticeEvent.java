/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jirc.protocol.servereevent;

/**
 *
 * @author troels
 */
public class ServerNoticeEvent {
    
    private String message;
    private String user;

    public ServerNoticeEvent(String user, String message) {
        this.message = message;
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNick() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
    
}
