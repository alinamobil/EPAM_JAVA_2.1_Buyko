package com.company.controller;

import com.company.reader.InfoReader;
import com.company.users.Administrator;
import com.company.users.Chef;
import com.company.users.Client;
import com.company.users.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Authorizer {

    static Logger logger = LogManager.getLogger();
    public InfoReader reader = new InfoReader();
    public Connection connection = reader.SQLReader();
    public Statement statement = connection.createStatement();

    public Authorizer() throws SQLException {
    }

    public Client findClientByNameAndPassword(String nickName, String password) throws SQLException {
        ArrayList<Client> clients = new ArrayList<>();
        ResultSet result = statement.executeQuery("SELECT * FROM Clients");
        while (result.next())
        {
            clients.add(new Client(result.getString("name"), result.getString("password"), result.getInt("clientId")));
        }

        for(Client item : clients) {
            if(item.getName().equals(nickName) && item.getPassword().equals(password)){
                System.out.println("Welcome back, " + nickName + "!");
                return item;
            }
        }

        ArrayList<Integer> identifiers = new ArrayList<>();
        for(Client client : clients) {
            identifiers.add(client.getId());
        }
        Collections.sort(identifiers);
        int id;
        if(clients.size() != 0) {
            id = identifiers.get(identifiers.size()-1) + 1;
        }
        else
            id = 3;
        Client newClient = new Client(nickName, password, id);
        System.out.println("Welcome to our restaurant, " + nickName + "!");

        addClientToTable(newClient);
        connection.close();
        return newClient;
    }

    private void addClientToTable(Client newClient) throws SQLException {
        String sql = "INSERT INTO Clients(clientId, name, password)" +
                "VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, newClient.getId());
        preparedStatement.setString(2, newClient.getName());
        preparedStatement.setString(3, newClient.getPassword());
        preparedStatement.executeUpdate();
        connection.close();
    }

    public User logIn() throws SQLException {
        String nickName, password;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your nickName:");
        nickName = scanner.nextLine();

        System.out.println("Enter your password:");
        password = scanner.nextLine();

        if(nickName.equals("admin") && password.equals("admin")){
            return new Administrator(nickName, password, 1);
        }
        else if(nickName.equals("chef") && password.equals("chef")){
            return new Chef(nickName, password, 2);
        }
        else{
            return  this.findClientByNameAndPassword(nickName, password);
        }
    }
}

