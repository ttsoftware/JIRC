/*
 * IRCTextPane.java
 *
 * Created on 7. februar 2008, 13:22
 */
package jirc.ui;

import java.awt.Color;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.text.html.HTML.Tag;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

/**
 *
 * @author  Troels Thomsen
 */
public class IRCTextPane extends JTextPane {

    HTMLDocument doc;
    HTMLEditorKit editor;
    String c[] = {"FFF", "000", "00007F", "009000", "FF0000", "7F0000", "9F009F", "FF7F00", "FFFF00", "00F800", "00908F", "00FFFF", "0000FF", "FF00FF", "7F7F7F", "CFD0CF"};

    public IRCTextPane() {

        editor = new HTMLEditorKit();
        editor.createDefaultDocument();
        editor.install(this);

        setEditorKitForContentType("text/html", editor);
        setContentType("text/html");
        setEditable(false);
        setBackground(Color.WHITE);

        doc = (HTMLDocument) getDocument();
    }

    public void append(String s) {

        try {

            editor.insertHTML(doc, doc.getLength(), toHtml(s), 0, 0, Tag.SPAN);
        }
        catch (Exception ex) {

            ex.printStackTrace();
        }
    }

    public String toHtml(String input) {

        String output = null;
        
        // match both background and foreground
        Pattern match_1 = Pattern.compile("\\x03(\\d\\d?),(\\d\\d?)(.*?)(?:(?=\\x03)|$)");
        Matcher matcher_1 = match_1.matcher(input);

        while (matcher_1.find()) {

            output = matcher_1.replaceFirst(
                    "</span><span style=\"color: #"
                    + c[Integer.parseInt(matcher_1.group(1))]
                    + "; background-color: #"
                    + c[Integer.parseInt(matcher_1.group(2))]
                    + ";\">" + matcher_1.group(3));
            input = output;
            matcher_1 = match_1.matcher(input);
        }

        // match foreground
        Pattern match_2 = Pattern.compile("\\x03(\\d\\d?)(.*?)(?:(?=\\x03)|$)");
        Matcher matcher_2 = match_2.matcher(input);

        while (matcher_2.find()) {

            output = matcher_2.replaceFirst(
                    "</span><span style=\"color: #"
                    + c[Integer.parseInt(matcher_2.group(1))]
                    + ";\">"
                    + matcher_2.group(2));

            input = output;
            matcher_2 = match_2.matcher(input);
        }

        // match no color
        Pattern match_6 = Pattern.compile("\\x03(\\D?)");
        Matcher matcher_6 = match_6.matcher(input);

        while (matcher_6.find()) {

            output = matcher_6.replaceFirst("</span> ");
            input = output;
            matcher_6 = match_6.matcher(input);
        }
        
        // match bold text
        Pattern match_4 = Pattern.compile("\\x02(.*?)(?:(?=\\x02)\\x02|$)");
        Matcher matcher_4 = match_4.matcher(input);

        while (matcher_4.find()) {

            output = matcher_4.replaceFirst("<b>"
                                            + matcher_4.group(1)
                                            + "</b>");
            input = output;
            matcher_4 = match_4.matcher(input);
        }

        // match underline text
        Pattern match_5 = Pattern.compile("\\x1F(.*?)(?:(?=\\x1F)\\x1F|$)");
        Matcher matcher_5 = match_5.matcher(input);

        while (matcher_5.find()) {

            output = matcher_5.replaceFirst(
                    "<u>"
                    + matcher_5.group(1)
                    + "</u>");
            input = output;
            matcher_5 = match_5.matcher(input);
        }

        if (output == null) {
            output = input;
        }

        return "<span>" + output + "</span></span><br />";
    }
}
