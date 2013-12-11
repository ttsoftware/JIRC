package jirc.ui;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IRCTextPaneText {

    @Test
    public void testToHTML() {

        String test1 = "^C5,12colored text and background^C";
        String test2 = "^C5colored text.^C Not colored text";
        String test3 = "^C3colored text ^C5,2more colored text and background^C";
        String test4 = "^C3,5colored text and background ^C8other colored text but same background^C";
        String test5 = "^C3,5colored text and background ^C8,7other colored text and different background^C";

        IRCTextPane pane = new IRCTextPane();

        String result1 = pane.toHtml(test1);
        String result2 = pane.toHtml(test2);
        String result3 = pane.toHtml(test3);
        String result4 = pane.toHtml(test4);
        String result5 = pane.toHtml(test5);

        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);
        System.out.println(result4);
        System.out.println(result5);
    }
}
