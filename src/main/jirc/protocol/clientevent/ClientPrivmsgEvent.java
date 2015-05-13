/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jirc.protocol.clientevent;

/**
 *
 * @author troels
 */
public class ClientPrivmsgEvent {

    private String message;
    private String channelName;
    
    public ClientPrivmsgEvent(String message) {
        
        this.message = message;
    }
    
    public ClientPrivmsgEvent(String channelName, String message) {
        
        this.channelName = channelName;
        this.message = message;
    }

    public String getChannel() {
        return channelName;
    }

    public void setChannel(String channelName) {
        this.channelName = channelName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
