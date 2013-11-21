/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package troels.projects.jircc.protocol;

import troels.projects.jircc.protocol.servereevent.ServerJoinEvent;
import org.bushe.swing.event.EventBus;
import troels.projects.jircc.protocol.servereevent.ServerEvent;
import troels.projects.jircc.protocol.clientevent.ClientEvent;
import troels.projects.jircc.protocol.servereevent.ServerNoticeEvent;
import troels.projects.jircc.protocol.servereevent.ServerPartEvent;
import troels.projects.jircc.protocol.servereevent.ServerUsersEvent;
import troels.projects.jircc.protocol.servereevent.ServerPrivmsgEvent;
import troels.projects.jircc.protocol.servereevent.ServerQuitEvent;

/**
 *
 * @author troels
 */
public class EventParser {

    public static void parse(ServerResponse response) {

        EventBus.publish(new ServerEvent(response));

        String event = response.getEvent();

        if (event.equals("PING")) {

            EventBus.publish(new ClientEvent("PONG " + response.getClient()));
        }
        else if (event.equals("JOIN")) {

            EventBus.publish(new ServerJoinEvent(response.getChannel(), response.getUsername()));
        }
        else if (event.equals("PRIVMSG")) {

            EventBus.publish(new ServerPrivmsgEvent(response.getParameter(), response.getChannel(), response.getUsername()));
        }
        else if (event.equals("332")) {
            // channel welcome message
        }
        else if (event.equals("333")) {
            // channel modes
        }
        else if (event.equals("353")) {
            // channel /NAMES list

            String[] users = response.getParameter().split("\\s");
            EventBus.publish(new ServerUsersEvent(response.getChannel(), users));
        }
        else if (event.equals("366")) {
            // end of /NAMES list
        }
        else if (event.equals("470")) {
            // forwarding to another channel
        }
        else if (event.equals("QUIT")) {
            //someone quit - remove whitespaces
            EventBus.publish(new ServerQuitEvent(response.getChannel().replaceAll("\\s", ""), response.getUsername()));
        }
        else if (event.equals("PART")) {
            //someone parted - remove whitespaces
            EventBus.publish(new ServerPartEvent(response.getChannel().replaceAll("\\s", ""), response.getUsername()));
        }
        else if (event.equals("NOTICE")) {
            if (!response.getUsername().equals("")) {
                EventBus.publish(new ServerNoticeEvent(response.getUsername(), response.getParameter()));
            }
        }
    }
}
