/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jirc.protocol.servereevent;

import jirc.protocol.ServerResponse;

/**
 *
 * @author troels
 */
public class ServerEvent {
    
    private ServerResponse response;

    public ServerEvent(ServerResponse response) {
    
        this.response = response;
    }

    public ServerResponse getResponse() {
        return response;
    }

    public void setResponse(ServerResponse response) {
        this.response = response;
    }
}
