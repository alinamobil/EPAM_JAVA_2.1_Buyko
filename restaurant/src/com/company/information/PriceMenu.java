package com.company.information;

import com.company.reader.InfoReader;

import java.sql.*;

public class PriceMenu {
    public InfoReader reader = new InfoReader();
    public Connection connection = reader.SQLReader();
    public Statement statement = connection.createStatement();

    public PriceMenu() throws SQLException {
    }

    public void showMenu() throws SQLException {
        ResultSet result = statement.executeQuery("SELECT * FROM Dishes");

        // Print results from select statement
        System.out.println("Menu:");
        System.out.println("----------");
        while (result.next())
        {
            System.out.println(String.format("%d. %s %4.2f",
                    result.getInt("dishId"),
                    result.getString("name"),
                    result.getDouble("price")));
            //this.menu.put(result.getString("name"), result.getDouble("price"));
        }
        //connection.close();
    }

    public void showByKeyWord() throws SQLException {
        System.out.println("Input part of dish name: ");
        String dishName = reader.readString(System.in);

        String sql = "SELECT * FROM Dishes WHERE name LIKE ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, '%' + dishName + '%');
        ResultSet resultSet = preparedStatement.executeQuery();
        System.out.println("Dishes:");
        while (resultSet.next())
        {
            System.out.println(String.format("%d. %s %4.2f",
                    resultSet.getInt("dishId"),
                    resultSet.getString("name"),
                    resultSet.getDouble("price")));
        }
        //connection.close();
    }

    public void showInPriceRange() throws SQLException {
        System.out.println("Input price range: ");
        System.out.print("Start: ");
        double startPrice = reader.readDouble(System.in);
        System.out.print("End: ");
        double endPrice = reader.readDouble(System.in);

        String sql = "SELECT * FROM Dishes WHERE price BETWEEN ? AND ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setDouble(1, startPrice);
        preparedStatement.setDouble(2, endPrice);
        ResultSet resultSet = preparedStatement.executeQuery();
        System.out.println("Dishes:");
        while (resultSet.next())
        {
            System.out.println(String.format("%d. %s %4.2f",
                    resultSet.getInt("dishId"),
                    resultSet.getString("name"),
                    resultSet.getDouble("price")));
        }
        //connection.close();
    }
}
