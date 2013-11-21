/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package troels.projects.jircc.protocol;

import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author troels
 */
public class ServerResponse {

    private String response,
                    username,
                    client,
                    event,
                    channel,
                    parameter;

    public ServerResponse() {
    }

    public ServerResponse(String response) {

        this.response = response;
        parseResponse(response);
    }

    public void parseResponse(String response) {

        /**
         * Name matcher
         */
        Pattern usernamePattern = Pattern.compile(":(.*?)!.*?");
        Matcher usernameMatcher = usernamePattern.matcher(response);

        if (usernameMatcher.find()) {

            this.username = usernameMatcher.group(1);
        }
        else {
            this.username = "";
        }

        /**
         * client, event.
         */
        Pattern nHEPattern = Pattern.compile(":.*?!?(.*?)\\s([0-9]{3}|[A-Z]+)");
        Matcher nHEMatcher = nHEPattern.matcher(response);

        if (nHEMatcher.find()) {

            this.client = nHEMatcher.group(1);
            this.event = nHEMatcher.group(2);
        }
        else if (response.startsWith("PING")) {

            this.event = "PING";
            this.client = response.substring(response.indexOf(":"));
        }

        /**
         * Find channel name no matter the location
         */
        Pattern channelPattern = Pattern.compile("(#\\w+)");
        Matcher channelMatcher = channelPattern.matcher(response);

        if (channelMatcher.find()) {

            this.channel = channelMatcher.group(1);
        }
        else {

            this.channel = "status";
        }

        /**
         * Group 1 = 'parameter' - everything after the last ':'. Can be channel name for JOIN or new nickname for NICK
         */
        Pattern parameterPattern = Pattern.compile(":.*:(.+?$|)");
        Matcher parameterMatcher = parameterPattern.matcher(response);

        if (parameterMatcher.find()) {

            this.parameter = parameterMatcher.group(1);
        }
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }
}
