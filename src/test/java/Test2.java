import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: troels
 * Date: 10/9/12
 * Time: 10:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class Test2 {

    public static void main(String[] args) {

        String[] structuralArray = {"package"};
        String[] objectArray = {"class"};
        String[] flowArray = {"for"};
        String[] choiceArray = {"switch"};
        String[] exceptionArray = {"throw"};
        String[] typeArray = {"boolean"};

        String[][] categoryArray = {
                structuralArray,
                objectArray,
                flowArray,
                choiceArray,
                exceptionArray,
                typeArray
        };

        Scanner scanner = new Scanner(System.in);

        int totalKeywords = 0;

        for (String[] category : categoryArray) {
            totalKeywords += category.length;
        }

        int pointCounter = 0;

        for (int i = 0; i < totalKeywords; i++) {

            int randomCategory = new Random().nextInt(categoryArray.length);

            String[] randomCategoryArray = categoryArray[randomCategory];

            int randomCategoryIndex = new Random().nextInt(randomCategoryArray.length);

            System.out.println("Hvilken kategori tilhører " + randomCategoryArray[randomCategoryIndex] + "?");

            int input = scanner.nextInt() - 1;

            if (input == randomCategory) {
                System.out.println("Korrekt!");
                pointCounter++;
            }
            else {
                System.out.println("Forkert!");
                break;
            }
        }

        System.out.println("Antal point i alt: " + pointCounter);
    }
}
