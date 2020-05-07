package com.company.operations;

import com.company.reader.InfoReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Operations {
    static Logger logger = LogManager.getLogger();
    public InfoReader reader = new InfoReader();
    public Connection connection = reader.SQLReader();
    public Statement statement = connection.createStatement();

    public Operations() throws SQLException {
    }

    public void addDishToPriceMenu() throws SQLException {
        String sql = "INSERT INTO Dishes(dishId, name, price)" +
                "SELECT MAX(dishId)+1, ?, ? FROM Dishes";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        System.out.println("Input name of new dish: ");
        String dishName = reader.readString(System.in);

        System.out.println("Input price of new dish: ");
        double dishPrice = reader.readDouble(System.in);
        preparedStatement.setString(1, dishName);
        preparedStatement.setDouble(2, dishPrice);
        preparedStatement.executeUpdate();
    }

    public int chooseIdentifier() throws SQLException {
        ResultSet result = statement.executeQuery("SELECT orderId FROM Orders ");
        ArrayList<Integer> identifiers = new ArrayList<>();
        while(result.next()) {
            identifiers.add(result.getInt(1));
        }
        System.out.println("Input an id: ");
        int id = reader.readId(System.in);
        while(!identifiers.contains(id)) {
            System.out.println("Input correct id: ");
            id = reader.readId(System.in);
        }
        return id;
    }

    public void showClientsOrders(int id) throws SQLException {
        ResultSet result = statement.executeQuery("SELECT orderId FROM CO WHERE clientId=" + id);
        if(!result.next()) {
            System.out.println("You have no orders yet");
        }
        else {
            result = statement.executeQuery("SELECT orderId FROM CO WHERE clientId=" + id);
            while (result.next()) {
                readOrder(result.getInt(1));
            }
        }
    }

    public void readOrder(int id) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "SELECT o.orderId, d.name, DO.dishAmount, o.total, o.paid\n" +
                     "FROM DO\n" +
                     "INNER JOIN Dishes d ON DO.dishId = d.dishId\n" +
                     "INNER JOIN Orders o ON DO.orderId = o.orderId\n" +
                     "WHERE o.orderId=" + id;
        ResultSet result = statement.executeQuery(sql);
        if (result.next()) {
            int orderId =  result.getInt("orderId");
            double total =  result.getDouble("total");
            String paid = result.getString("paid");

            System.out.println("----------");
            System.out.println("Order: ");
            System.out.print(String.format("id: %d", orderId) + ", dishes{ ");
            System.out.print(result.getString("name") + " - " + result.getInt("dishAmount") + " pieces ");
            while (result.next()) {
                String dishes = result.getString("name");
                int amount = result.getInt("dishAmount");
                System.out.print(dishes + " - " + amount + " pieces ");
            }
            System.out.println(String.format("}, total: %4.2f", total) + ", paid: " + paid);
            System.out.println("----------");
        }
        else {
            System.out.println("You have no orders to update");
        }
    }

    public int findUnpaidOrder() throws SQLException {
        ResultSet result = statement.executeQuery("SELECT orderId FROM Orders WHERE paid = 'false'");
        int id = 0;
        if(result.next()) {
            id = result.getInt(1);
        }
        return id;
    }

    public void payForOrder() throws SQLException {
        if(findUnpaidOrder() == 0) {
            System.out.println("You have no orders to pay for");
        }
        else {
            readOrder(findUnpaidOrder());
            Scanner scanner = new Scanner(System.in);
            System.out.print("Input 'pay' to pay: ");
            String key = scanner.nextLine();
            while(!key.equals("pay")) {
                System.out.print("Input 'pay' to pay: ");
                key = scanner.nextLine();
            }
            String sql = "UPDATE Orders SET paid ='true' WHERE paid = 'false' ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            logger.info("Payment succeed");
        }
    }

    public void showClients() throws SQLException {
        ResultSet result = statement.executeQuery("SELECT * FROM Clients");

        System.out.println("Clients:");
        System.out.println("----------");
        while (result.next())
        {
            System.out.println(String.format("id: %d, name: %s",
                    result.getInt("clientId"),
                    result.getString("name")));
        }
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }
}
