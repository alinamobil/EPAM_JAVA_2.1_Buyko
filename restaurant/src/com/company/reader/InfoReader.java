package com.company.reader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class InfoReader {
    static Logger logger = LogManager.getLogger();
    public static final String REGEX = "[0-4]";
    public static final String REGEX_NATURAL = "\\d+";
    public static final String REGEX_STRING = "\\s+";
    public static final String REGEX_ORDER = "(\\d+:\\d+\\s?)+";

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

    public int readId(InputStream input){
        Scanner scanner = new Scanner(input);
        String line = scanner.nextLine();
        line = line.trim();
        while (!line.matches(REGEX_NATURAL)) {
            System.out.println("Choose natural number!");
            line = scanner.nextLine();
            line = line.trim();
        }
        int n = Integer.parseInt(line);
        return n;
    }

    public String[] readDish(InputStream input) throws SQLException {
        Scanner scanner = new Scanner(input);
        String line = scanner.nextLine();
        line = line.trim();
        while(!line.matches(REGEX_ORDER)) {
            System.out.println("Input order in given format: ");
            line = scanner.nextLine();
            line = line.trim();
        }
        String[] dishes = line.split(REGEX_STRING);
        return dishes;
    }

    public Connection SQLReader() {
        // Connect to database
        String hostName = "french-restaurant.database.windows.net";
        String dbName = "restaurant";
        String user = "ada";
        String password = "French-restaurant";
        String url = String.format("jdbc:sqlserver://%s;databaseName=%s;user=%s;password=%s;", hostName, dbName, user, password);
        Connection connection = null;
        System.out.println("Wait a few seconds...");
        try {
            connection = DriverManager.getConnection(url);
        }
        catch (Exception e) {
            logger.error(e);
        }
        return connection;
    }


    public String readString(InputStream input) {
        Scanner scanner = new Scanner(input);
        String str = scanner.nextLine();
        str = str.trim();
        return str;
    }

    public double readDouble(InputStream input) {
        Scanner scanner = new Scanner(input);
        double price = scanner.nextDouble();
        return price;
    }
}
