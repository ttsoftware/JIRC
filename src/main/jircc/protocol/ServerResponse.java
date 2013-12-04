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
                    channel = "status",
                    affectedUser,
                    affectedChannel,
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
         * If there are two channels, we should add the second to affectedChannel.
         */
        Pattern channelPattern = Pattern.compile("(#[\\w\\d]+)(\\s#[\\w\\d]+)?");
        Matcher channelMatcher = channelPattern.matcher(response);

        if (channelMatcher.find()) {
            this.channel = channelMatcher.group(1);
            if (channelMatcher.group(2) != null) {
                this.affectedChannel = channelMatcher.group(2).trim();
            }
        }

        /**
         * Find the user on which the action is affection. The user to be kicked for instance.
         */
        Pattern affectedPattern = Pattern.compile("(" + this.channel + "|" + this.event + ")\\s([\\w\\d_-]+)\\s:?");
        Matcher affectedMatcher = affectedPattern.matcher(response);

        if (affectedMatcher.find()) {
            this.affectedUser = affectedMatcher.group(2);
        }

        /**
         * Group 1 = 'parameter' - everything after the last ':'.
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

    public String getAffectedUser() {
        return affectedUser;
    }

    public void setAffectedUser(String affectedUser) {
        this.affectedUser = affectedUser;
    }

    public String getAffectedChannel() {
        return affectedChannel;
    }

    public void setAffectedChannel(String affectedChannel) {
        this.affectedChannel = affectedChannel;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }
}
