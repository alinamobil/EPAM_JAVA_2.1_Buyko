package reader;

import java.io.InputStream;
import java.util.Scanner;

public class InfoReader {
    public static final String REGEX = "[1-5]";
    public static final String REGEX_NATURAL = "[1-9]\\d*";
    public static final String REGEX_DELIMETER = "\\s";
    public static final String REGEX_RANGE = "[A-Z][1-9]\\d*:[A-Z][1-9]\\d*";

    public int readInt(InputStream input){
        Scanner scanner = new Scanner(input);
        String line = scanner.nextLine();
        line = line.trim();
        while (!line.matches(REGEX)) {
            System.out.println("Choose correct option!");
            line = scanner.nextLine();
            line = line.trim();
        }
        int n = Integer.parseInt(line);
        return n;
    }

    public int readNumber(InputStream input) {
        Scanner scanner = new Scanner(input);
        String line = scanner.nextLine();
        line = line.trim();
        while (!line.matches(REGEX_NATURAL)) {
            System.out.println("Input a natural number!");
            line = scanner.nextLine();
            line = line.trim();
        }
        int n = Integer.parseInt(line);
        return n;
    }

    public String[] readRecord(InputStream input) {
        System.out.println("Input a new record. Data must be separated by spaces:");
        Scanner scanner = new Scanner(input);
        String line = scanner.nextLine();
        line = line.trim();
        String[] result = line.split(REGEX_DELIMETER);
        return result;
    }

    public String readRange(InputStream input) {
        System.out.println("Input a range:");
        Scanner scanner = new Scanner(input);
        String line = scanner.nextLine();
        line = line.trim();
        while (!line.matches(REGEX_RANGE)) {
            System.out.println("Input range in correct format(e.g. A1:C10)!");
            line = scanner.nextLine();
            line = line.trim();
        }
        return line;
    }
}
