/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package troels.projects.jircc.protocol.servereevent;

import troels.projects.jircc.protocol.ServerResponse;

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
