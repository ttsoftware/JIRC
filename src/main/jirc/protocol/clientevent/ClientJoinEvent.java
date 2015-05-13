/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jirc.protocol.clientevent;

/**
 *
 * @author troels
 */
public class ClientJoinEvent {

    private String channel;

    public ClientJoinEvent(String channel) {
        this.channel = channel;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
    
}
