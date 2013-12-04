package troels.projects.jircc.protocol;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ServerResponseTest {

    @Test
    public void testParseResponse00X() {
        // Test welcome message

        String test8 = ":zelazny.freenode.net 001 test :Welcome to the freenode Internet Relay Chat Network test";
        String test9 = ":zelazny.freenode.net 002 test :Your host is zelazny.freenode.net[140.211.167.106/6667], running version ircd-seven-1.1.3";

        ServerResponse response12 = new ServerResponse(test8);

        assertEquals(response12.getEvent(), "001");
        assertEquals(response12.getUsername(), null);
        assertEquals(response12.getClient(), "zelazny.freenode.net");
        assertEquals(response12.getChannel(), "status");
        assertEquals(response12.getAffectedUser(), "test");
        assertEquals(response12.getAffectedChannel(), null);
        assertEquals(response12.getParameter(), "Welcome to the freenode Internet Relay Chat Network test");

        response12 = new ServerResponse(test9);

        assertEquals(response12.getEvent(), "002");
        assertEquals(response12.getUsername(), null);
        assertEquals(response12.getClient(), "zelazny.freenode.net");
        assertEquals(response12.getChannel(), "status");
        assertEquals(response12.getAffectedUser(), "test");
        assertEquals(response12.getAffectedChannel(), null);
        assertEquals(response12.getParameter(), "Your host is zelazny.freenode.net[140.211.167.106/6667], running version ircd-seven-1.1.3");
    }

    @Test
    public void testParseResponse470() {

        String test11 = ":brooks.freenode.net 470 traeasdfasdfasdf #help #freenode :Forwarding to another channel";

        ServerResponse response12 = new ServerResponse(test11);

        assertEquals(response12.getEvent(), "470");
        assertEquals(response12.getUsername(), null);
        assertEquals(response12.getClient(), "brooks.freenode.net");
        assertEquals(response12.getChannel(), "#help");
        assertEquals(response12.getAffectedUser(), "traeasdfasdfasdf");
        assertEquals(response12.getAffectedChannel(), "#freenode");
        assertEquals(response12.getParameter(), "Forwarding to another channel");
    }

    @Test
    public void testParseResponsePing() {

        String test10 = "PING :brooks.freenode.net";

        ServerResponse response12 = new ServerResponse(test10);

        assertEquals(response12.getEvent(), "PING");
        assertEquals(response12.getUsername(), null);
        assertEquals(response12.getClient(), ":brooks.freenode.net");
        assertEquals(response12.getChannel(), "status");
        assertEquals(response12.getAffectedUser(), null);
        assertEquals(response12.getParameter(), null);
    }

    @Test
    public void testParseResponseKick() {

        String test7 = ":Global!services@swiftirc.net KICK #Finnish John :Speaking English";

        ServerResponse response12 = new ServerResponse(test7);

        assertEquals(response12.getEvent(), "KICK");
        assertEquals(response12.getUsername(), "Global");
        assertEquals(response12.getClient(), "Global!services@swiftirc.net");
        assertEquals(response12.getChannel(), "#Finnish");
        assertEquals(response12.getAffectedUser(), "John");
        assertEquals(response12.getParameter(), "Speaking English");
    }

    @Test
    public void testParseResponseJoin() {

        String test4 = ":sizzurp!~qwebirc@Swift-F885C78D.dhcp.stcd.mn.chzarter.com JOIN :#irchelp";

        ServerResponse response12 = new ServerResponse(test4);

        assertEquals(response12.getEvent(), "JOIN");
        assertEquals(response12.getUsername(), "sizzurp");
        assertEquals(response12.getClient(), "sizzurp!~qwebirc@Swift-F885C78D.dhcp.stcd.mn.chzarter.com");
        assertEquals(response12.getChannel(), "#irchelp");
        assertEquals(response12.getParameter(), "#irchelp");
    }

    @Test
    public void testParseResponsePrivmsg() {

        String test3 = ":Flame_OG!~Flame_OG@im.baking.a.cookie.interglow.net PRIVMSG #irchelp :ok ty";
        ServerResponse response = new ServerResponse(test3);

        assertEquals(response.getEvent(), "PRIVMSG");
        assertEquals(response.getUsername(), "Flame_OG");
        assertEquals(response.getClient(), "Flame_OG!~Flame_OG@im.baking.a.cookie.interglow.net");
        assertEquals(response.getChannel(), "#irchelp");
        assertEquals(response.getParameter(), "ok ty");

        String test5 = ":Tayl!T@yl PRIVMSG #irchelp :Could ask the founder, but if you were banned it was probably for a good reason\n";
        ServerResponse response1 = new ServerResponse(test5);

        assertEquals(response1.getEvent(), "PRIVMSG");
        assertEquals(response1.getUsername(), "Tayl");
        assertEquals(response1.getClient(), "Tayl!T@yl");
        assertEquals(response1.getChannel(), "#irchelp");
        assertEquals(response1.getParameter(), "Could ask the founder, but if you were banned it was probably for a good reason");
    }

    @Test
    public void testParseResponseNick() {

        String test2 = ":Flame_OG!~Flame_OG@im.baking.a.cookie.interglow.net NICK :Flame`Away";

        ServerResponse response12 = new ServerResponse(test2);

        assertEquals(response12.getEvent(), "NICK");
        assertEquals(response12.getUsername(), "Flame_OG");
        assertEquals(response12.getClient(), "Flame_OG!~Flame_OG@im.baking.a.cookie.interglow.net");
        assertEquals(response12.getChannel(), "status");
        assertEquals(response12.getParameter(), "Flame`Away");
    }

    @Test
    public void testParseResponsePart() {

        String test1 = ":sizzurp!~qwebirc@Swift-F885C78D.dhcp.stcd.mn.charter.com PART #irchelp :";

        ServerResponse response12 = new ServerResponse(test1);

        assertEquals(response12.getEvent(), "PART");
        assertEquals(response12.getUsername(), "sizzurp");
        assertEquals(response12.getClient(), "sizzurp!~qwebirc@Swift-F885C78D.dhcp.stcd.mn.charter.com");
        assertEquals(response12.getChannel(), "#irchelp");
        assertEquals(response12.getParameter(), "");
    }

    @Test
    public void testParseResponseNotice() {

        String test0 = ":adams.freenode.net NOTICE * :*** Looking up your hostname...";

        ServerResponse response12 = new ServerResponse(test0);

        assertEquals(response12.getEvent(), "NOTICE");
        assertEquals(response12.getUsername(), null);
        assertEquals(response12.getClient(), "adams.freenode.net");
        assertEquals(response12.getChannel(), "status");
        assertEquals(response12.getParameter(), "*** Looking up your hostname...");

        String test6 = ":Global!services@swiftirc.net NOTICE ssdf :[Logon News - Oct 24 2010] There have been several attempts recently by someone pre";

        ServerResponse response = new ServerResponse(test6);

        assertEquals(response.getEvent(), "NOTICE");
        assertEquals(response.getUsername(), "Global");
        assertEquals(response.getClient(), "Global!services@swiftirc.net");
        assertEquals(response.getChannel(), "status");
        assertEquals(response.getAffectedUser(), "ssdf");
        assertEquals(response.getParameter(), "[Logon News - Oct 24 2010] There have been several attempts recently by someone pre");
    }

    @Test
    public void testParseResponse353() {

        String test12 = ":cameron.freenode.net 353 dgadgadfgadfg = #freenode :MissionCritical LeonRadley kakain Gizmokid2005 michagogo Sudden ivenkys Shiva IdleOne matejv ms_ edit_21 TonyL Iamonthissong sid|1 hattwick unreal sleeptyper JosephW1993 Chilley capella kPa_ MichaelC|Away TheLonelyGod jimerickson serjs meeple mosno Savage_CL Fersure a-treme bolt Tanvir SirCmpwn aji Bry8Star{EB BruceBurge spyro chops JC-Arch dedis Sprocks nb AndChat106409 scorche pavlz_ xyphoid Buglouse River-Rat JodaZ McPeter ravenlock Aha2Y\n";

        ServerResponse response12 = new ServerResponse(test12);

        assertEquals(response12.getEvent(), "353");
        assertEquals(response12.getUsername(), null);
        assertEquals(response12.getClient(), "cameron.freenode.net");
        assertEquals(response12.getChannel(), "#freenode");
        assertEquals(response12.getParameter(), "MissionCritical LeonRadley kakain Gizmokid2005 michagogo Sudden ivenkys Shiva IdleOne matejv ms_ edit_21 TonyL Iamonthissong sid|1 hattwick unreal sleeptyper JosephW1993 Chilley capella kPa_ MichaelC|Away TheLonelyGod jimerickson serjs meeple mosno Savage_CL Fersure a-treme bolt Tanvir SirCmpwn aji Bry8Star{EB BruceBurge spyro chops JC-Arch dedis Sprocks nb AndChat106409 scorche pavlz_ xyphoid Buglouse River-Rat JodaZ McPeter ravenlock Aha2Y");
    }
}
