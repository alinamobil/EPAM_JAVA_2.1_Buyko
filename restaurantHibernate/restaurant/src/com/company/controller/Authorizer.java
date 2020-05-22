package com.company.controller;

import com.company.persistence.Clients_DB;
import com.company.users.Administrator;
import com.company.users.Chef;
import com.company.users.Client;
import com.company.users.User;
import com.company.util.HibernateSessionFactoryUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Authorizer {
    Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();

    public Authorizer() {
    }

    public Client findClientByNameAndPassword(String nickName, String password) {
        ArrayList<Client> clients = new ArrayList<>();

        List<Clients_DB> clients_dbs = (List<Clients_DB>) session.createQuery("SELECT c FROM Clients_DB c").list();
        for (Clients_DB c : clients_dbs) {
            clients.add(new Client(c.getName(), c.getPassword(), c.getClientId()));
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
        return newClient;
    }

    private void addClientToTable(Client newClient) {
        String sql = "INSERT INTO Clients_DB(clientId, name, password)" +
                    " SELECT distinct " + newClient.getId() + ", '" + newClient.getName() + "', '" + newClient.getPassword() +
                    "' FROM Clients_DB";
        session.createQuery(sql).executeUpdate();
    }

    public User logIn() {
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

