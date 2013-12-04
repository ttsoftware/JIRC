/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package troels.projects.jircc.protocol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.Socket;
import java.net.URI;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author troels
 */
public class IRCConnection {

    private Socket socket;
    private BufferedReader input; // server output = client input
    private PrintWriter output; // server input = client output
    int port;
    String host;
    String nickname;
    String password;

    public IRCConnection() {
    }

    public void connect() {

        if (!checkProxy()) {

            try {

                socket = new Socket(host, port);
                input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            }
            catch (IOException e) {

                e.printStackTrace();
            }

            doServerCall("PASS " + password);
            doServerCall("NICK " + nickname);
            doServerCall("USER " + "JIRCC" + " 0 * :" + "TTSoftware");

            try {

                while (!Thread.interrupted()) {

                    String line = input.readLine();

                    if (line != null) {

                        System.out.println(line);

                        //Do something with what the server tells you
                        EventParser.parse(new ServerResponse(line));
                    }
                    else {

                        System.out.println("\nConnection to " + host + ":" + port + " has been closed by server\n");

                        cleanUp();
                    }
                }
            }
            catch (Exception e) {

                e.printStackTrace();
            }
        }
    }

    public void doServerCall(String message) {

        output.print(message + "\r\n");
        output.flush();
    }

    public void cleanUp() {

        try {
            socket.close();
            output.close();
            input.close();
        }
        catch (IOException ex) {
            Logger.getLogger(IRCConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

        socket = null;
        output = null;
        input = null;
    }

    public boolean checkProxy() {

        boolean isProxy = false;

        System.setProperty("Java.net.useSystemProxies", "true");

        try {

            List l = ProxySelector.getDefault().select(new URI("http://www.google.com/"));

            for (Object aL : l) {

                Proxy proxy = (Proxy) aL;
                InetSocketAddress addr = (InetSocketAddress) proxy.address();
                isProxy = addr != null;
            }
        }
        catch (Exception e) {

            e.printStackTrace();
        }

        return isProxy;
    }
}
