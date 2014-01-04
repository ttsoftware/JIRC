package jirc.ui;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IRCTextPaneText {

    @Test
    public void testToHTML() {

        String test1 = 0x03 + "5,12colored text and background" + 0x03;
        String test2 = 0x03 + "5colored text." + 0x03 + " Not colored text" + 0x03;
        String test3 = 0x03 + "3colored text " + 0x03 + "5,2more colored text and background" + 0x03;
        String test4 = 0x03 + "3,5colored text and background " + 0x03 + "8other colored text but same background" + 0x03;
        String test5 = 0x03 + "3,5colored text and background " + 0x03 + "8,7other colored text and different background" + 0x03;
        String test6 = ":NickServ!NickServ@services. NOTICE jirctest :\u0002jirctest\u0002 is not a registered nickname.";

        IRCTextPane pane = new IRCTextPane();

        String result1 = pane.toHtml(test1);
        String result2 = pane.toHtml(test2);
        String result3 = pane.toHtml(test3);
        String result4 = pane.toHtml(test4);
        String result5 = pane.toHtml(test5);
        String result6 = pane.toHtml(test6);

        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);
        System.out.println(result4);
        System.out.println(result5);
        System.out.println(result6);
    }
}
