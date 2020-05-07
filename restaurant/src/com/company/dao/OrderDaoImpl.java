package com.company.dao;

import com.company.information.PriceMenu;
import com.company.operations.Operations;
import com.company.reader.InfoReader;
import com.company.users.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class OrderDaoImpl implements OrderDao {
    static Logger logger = LogManager.getLogger();
    public InfoReader reader = new InfoReader();
    public Connection connection = reader.SQLReader();
    public Statement statement = connection.createStatement();
    public PriceMenu priceMenu = new PriceMenu();

    public OrderDaoImpl() throws SQLException {
    }

    @Override
    public void createOrder(Client client) throws SQLException {
        Operations operation = new Operations();
        if(operation.findUnpaidOrder() != 0) {
            System.out.println("You should pay for your previous order");
        }
        else {
            priceMenu.showMenu();
            System.out.println("Input new order by template (dishId:amount). Dishes must be separated by spaces: ");
            String[] dishes = reader.readDish(System.in);
            ArrayList<String> newDishes = new ArrayList<> (Arrays.asList(dishes));
            ResultSet result = statement.executeQuery("SELECT MAX(orderId)+1 AS id FROM DO");
            int id = 0;
            if(result.next()) {
                id = result.getInt(1);
            }
            String sql;
            PreparedStatement preparedStatement;
            for(String str : newDishes) {
                sql = "INSERT INTO DO(dishId, orderId, dishAmount)" +
                             "VALUES (?, ?, ?)";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, Integer.parseInt(str.split(":")[0]));
                preparedStatement.setInt(2, id);
                preparedStatement.setInt(3, Integer.parseInt(str.split(":")[1]));
                preparedStatement.executeUpdate();
            }

            sql = "INSERT INTO CO(clientId, orderId)" +
                    "VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, client.getId());
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            logger.info("Order is created");
        }
    }

    @Override
    public void showOrdersList() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("SELECT orderId, total, paid FROM Orders ");
        // Print results from select statement
        System.out.println("Orders:");
        System.out.println("----------");
        while (result.next())
        {
            System.out.println(String.format("id: %d, total: %4.2f, paid: %s",
                    result.getInt("orderId"),
                    result.getDouble("total"),
                    result.getString("paid")));
        }
    }

    @Override
    public void deleteOrder(int id) throws SQLException {
        String sql = "DELETE FROM Orders WHERE orderId = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
        logger.info("Order is deleted");
    }

    @Override
    public void updateOrder(/*Client client*/) throws SQLException {
        Operations operation = new Operations();
        int id = operation.findUnpaidOrder();
        if(id == 0) {
            System.out.println("You should create an order");
        }
        else {
            priceMenu.showMenu();
            System.out.println("Input new dishes for your order by template(dishId:amount). Dishes must be separated by spaces: ");
            String[] dishes = reader.readDish(System.in);
            ArrayList<String> newDishes = new ArrayList<> (Arrays.asList(dishes));
            String sql;
            PreparedStatement preparedStatement;
            for(String str : newDishes) {
                sql = "INSERT INTO DO(dishId, orderId, dishAmount)" +
                        "VALUES (?, ?, ?)";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, Integer.parseInt(str.split(":")[0]));
                preparedStatement.setInt(2, id);
                preparedStatement.setInt(3, Integer.parseInt(str.split(":")[1]));
                preparedStatement.executeUpdate();
            }
            logger.info("Order is updated");
        }
    }
}
