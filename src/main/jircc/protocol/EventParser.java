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

        switch (event) {

            case "PING":
                EventBus.publish(new ClientEvent("PONG " + response.getClient()));
                break;

            case "JOIN":
                EventBus.publish(new ServerJoinEvent(response.getChannel(), response.getUsername()));
                break;

            case "PRIVMSG":
                // privmsg event for channels or users
                EventBus.publish(new ServerPrivmsgEvent(response.getParameter(), response.getChannel(), response.getUsername()));
                break;

            case "332":
                // channel welcome message
                break;

            case "333":
                // channel modes
                break;

            case "353":
                // channel /NAMES list
                String[] users = response.getParameter().split("\\s");
                EventBus.publish(new ServerUsersEvent(response.getChannel(), users));
                break;

            case "366":
                // end of /NAMES list
                break;

            case "470":
                // forwarding to another channel
                break;

            case "QUIT":
                //someone quit
                EventBus.publish(new ServerQuitEvent(response.getUsername()));
                break;

            case "PART":
                //someone parted
                EventBus.publish(new ServerPartEvent(response.getChannel(), response.getUsername()));
                break;

            case "NOTICE":
                if (response.getUsername() != null) {
                    EventBus.publish(new ServerNoticeEvent(response.getUsername(), response.getParameter()));
                }
                break;
        }
    }
}
