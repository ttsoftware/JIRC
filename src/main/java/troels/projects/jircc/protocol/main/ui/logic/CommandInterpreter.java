/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package troels.projects.jircc.protocol.main.ui.logic;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.bushe.swing.event.EventBus;
import troels.projects.jircc.protocol.clientevent.ClientEvent;
import troels.projects.jircc.protocol.clientevent.ClientPrivmsgEvent;

/**
 *
 * @author troels
 */
public class CommandInterpreter {

    String input;
    String[] commands = {"join", "msg"};
    
    public CommandInterpreter(String input, String channelName) {
        
        interpret(input, channelName);
    }
    
    private void interpret(String input, String channelName) {
        
        if (input.startsWith("/")) {
            
            String[] tokens = input.substring(1).split("\\s");
            
            if (tokens.length > 0) {
                
                Set<String> set = new HashSet<String> (Arrays.asList(commands));
                String command = tokens[0];
                
                if (set.contains(tokens[0])) {
                    
                    if (command.toLowerCase().equals("join")) {
                        
                        EventBus.publish(new ClientEvent("JOIN " + tokens[1]));
                    }
                }
                else {
                    
                    //unsupported command
                }
            }
            else {
                
                //invalid input length
            }
        }
        else {
            
            //regular input (PRIVMSG), post to channel
            EventBus.publish(new ClientPrivmsgEvent(channelName, input));
        }
    }
}
