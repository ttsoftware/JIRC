package jirc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: troels
 * Date: 10/9/12
 * Time: 8:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class Test {

    public static void main(String args[]) {

        HashMap<String, KeywordType> keywordsMap = getKeywordsMap();

        Scanner scanner = new Scanner(System.in);

        ArrayList<String> keywordValues = new ArrayList<String>(keywordsMap.keySet());

        for (int i = 0; i < keywordsMap.size(); i++) {

            int randomIndex = new Random().nextInt(keywordValues.size());
            String randomKeyword = keywordValues.get(randomIndex);

            System.out.println("Hvilken kategori tilhører: " + keywordValues.get(randomIndex) + "?");

            String input = scanner.nextLine().toUpperCase();
            KeywordType inputType = KeywordType.EMPTY;

            try {
                inputType = KeywordType.valueOf(input);
            }
            catch (IllegalArgumentException e) {
                // who cares?
            }

            if (keywordsMap.get(randomKeyword) == inputType) {
                System.out.println("Korrekt!");
            }
            else {
                System.out.println("FORKERT!");
            }

            keywordValues.remove(randomIndex);
        }
    }

    private static HashMap<String, KeywordType> getKeywordsMap() {

        HashMap<String, KeywordType> keywordTypeHashMap = new HashMap<String, KeywordType>();

        keywordTypeHashMap.put("package", KeywordType.STRUCTURAL);
        keywordTypeHashMap.put("class", KeywordType.OBJECT);
        keywordTypeHashMap.put("for", KeywordType.FLOW);
        keywordTypeHashMap.put("switch", KeywordType.CHOICE);
        keywordTypeHashMap.put("throw", KeywordType.EXCEPTIONHANDLING);
        keywordTypeHashMap.put("boolean", KeywordType.TYPE);

        return keywordTypeHashMap;
    }

    private enum KeywordType {
        STRUCTURAL,
        OBJECT,
        FLOW,
        CHOICE,
        EXCEPTIONHANDLING,
        TYPE,
        EMPTY
    }
}
