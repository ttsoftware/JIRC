/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package troels.projects.jircc.protocol.clientevent;

/**
 * Generic event.
 * @author troels
 */
public class ClientEvent {
    
    private String request;

    public ClientEvent(String request) {
    
        this.request = request;
    }
    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }
    
}
