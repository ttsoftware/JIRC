import troels.projects.jircc.protocol.ServerResponse;

/**
 * Created with IntelliJ IDEA.
 * User: troels
 * Date: 8/3/12
 * Time: 10:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestServerParser {

    public static void main(String[] args) {

        String test0 = ":adams.freenode.net NOTICE * :*** Looking up your hostname...";
        String test1 = ":sizzurp!~qwebirc@Swift-F885C78D.dhcp.stcd.mn.charter.com PART #irchelp :";
        String test2 = ":Flame_OG!~Flame_OG@im.baking.a.cookie.interglow.net NICK :Flame`Away";
        String test3 = ":Flame_OG!~Flame_OG@im.baking.a.cookie.interglow.net PRIVMSG #irchelp :ok ty";
        String test4 = ":sizzurp!~qwebirc@Swift-F885C78D.dhcp.stcd.mn.chzarter.com JOIN :#irchelp";
        String test5 = ":Tayl!T@yl PRIVMSG #irchelp :Could ask the founder, but if you were banned it was probably for a good reason\n";
        String test6 = ":Global!services@swiftirc.net NOTICE ssdf :[Logon News - Oct 24 2010] There have been several attempts recently by someone pre";
        String test7 = ":Global!services@swiftirc.net KICK #Finnish John :Speaking English";
        String test8 = ":zelazny.freenode.net 001 test :Welcome to the freenode Internet Relay Chat Network test";
        String test9 = ":zelazny.freenode.net 002 test :Your host is zelazny.freenode.net[140.211.167.106/6667], running version ircd-seven-1.1.3";
        String test10 = "PING :brooks.freenode.net";
        String test11 = ":brooks.freenode.net 470 traeasdfasdfasdf #help #freenode :Forwarding to another channel";
        String test12 = ":cameron.freenode.net 353 dgadgadfgadfg = #freenode :MissionCritical LeonRadley kakain Gizmokid2005 michagogo Sudden ivenkys Shiva IdleOne matejv ms_ edit_21 TonyL Iamonthissong sid|1 hattwick unreal sleeptyper JosephW1993 Chilley capella kPa_ MichaelC|Away TheLonelyGod jimerickson serjs meeple mosno Savage_CL Fersure a-treme bolt Tanvir SirCmpwn aji Bry8Star{EB BruceBurge spyro chops JC-Arch dedis Sprocks nb AndChat106409 scorche pavlz_ xyphoid Buglouse River-Rat JodaZ McPeter ravenlock Aha2Y\n";

        ServerResponse response1 = new ServerResponse();

        response1.parseResponse(test12);
        String[] users = response1.getParameter().split("\\s");

        System.out.println("event: " + response1.getEvent());
        System.out.println("username: " + response1.getUsername());
        System.out.println("client: " + response1.getClient());
        System.out.println("channel: " + response1.getChannel());
        System.out.println("parameter: " + response1.getParameter());
        for (String user : users) {
            System.out.println(user);
        }
        System.out.println("\n");

        /*
        response1.parseResponse(test0);

        System.out.println("event: " + response1.getEvent());
        System.out.println("username: " + response1.getUsername());
        System.out.println("client: " + response1.getClient());
        System.out.println("channel: " + response1.getChannel());
        System.out.println("parameter: " + response1.getParameter());
        System.out.println("\n");

        ServerResponse response2 = new ServerResponse();

        response2.parseResponse(test1);

        System.out.println("event: " + response2.getEvent());
        System.out.println("username: " + response2.getUsername());
        System.out.println("client: " + response2.getClient());
        System.out.println("channel: " + response2.getChannel());
        System.out.println("parameter: " + response2.getParameter());
        System.out.println("\n");

        ServerResponse response3 = new ServerResponse();

        response3.parseResponse(test2);

        System.out.println("event: " + response3.getEvent());
        System.out.println("username: " + response3.getUsername());
        System.out.println("client: " + response3.getClient());
        System.out.println("channel: " + response3.getChannel());
        System.out.println("parameter: " + response3.getParameter());
        System.out.println("\n");

        ServerResponse response4 = new ServerResponse();

        response4.parseResponse(test8);

        System.out.println("event: " + response4.getEvent());
        System.out.println("username: " + response4.getUsername());
        System.out.println("client: " + response4.getClient());
        System.out.println("channel: " + response4.getChannel());
        System.out.println("parameter: " + response4.getParameter());
        System.out.println("\n");

        ServerResponse response5 = new ServerResponse();

        response5.parseResponse(test10);

        System.out.println("event: " + response5.getEvent());
        System.out.println("username: " + response5.getUsername());
        System.out.println("client: " + response5.getClient());
        System.out.println("channel: " + response5.getChannel());
        System.out.println("parameter: " + response5.getParameter());
        System.out.println("\n");

        /*
        response1.parseResponse(test3);
        response1.parseResponse(test4);
        response1.parseResponse(test5);
        response1.parseResponse(test6);
        response1.parseResponse(test7);
        */
    }
}
