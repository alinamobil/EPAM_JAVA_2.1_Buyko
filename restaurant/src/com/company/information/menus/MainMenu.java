package com.company.information.menus;

import com.company.controller.Authorizer;
import com.company.dao.OrderDao;
import com.company.reader.InfoReader;
import com.company.users.Administrator;
import com.company.users.Client;
import com.company.users.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class MainMenu {
    private static OrderDao orderDao;
    static Logger logger = LogManager.getLogger();

    public void showMainMenu() {
        System.out.println("----------");
        System.out.println("Choose an option:");
        System.out.println("1. Log in");
        System.out.println("2. Exit");
        System.out.print(">> ");
    }

    public void makeOperations(int n) throws SQLException {
        switch (n) {
            case 1:
                this.initMenuForUser();
                break;
            case 2:
                break;
        }
    }

    public void initMenuForUser() throws SQLException {
        Authorizer authorizer = new Authorizer();
        User authorizedUser = authorizer.logIn();
        Client currentClient = new Client(authorizedUser);
        InfoReader reader = new InfoReader();

        if(authorizedUser instanceof Client){
            ClientMenu clientMenu = new ClientMenu(currentClient);

            int n = 1;
            while (n != 0) {
                clientMenu.showClientMenu();
                n = reader.readInt(System.in);
                clientMenu.makeOperations(n);
            }
        }
        else if (authorizedUser instanceof Administrator){
            AdminMenu adminMenu = new AdminMenu();
            int n = 1;
            while (n != 0) {
                adminMenu.showAdminMenu();
                n = reader.readInt(System.in);
                adminMenu.makeOperations(n);
            }
        }
        else {
            ChefMenu chefMenu = new ChefMenu();
            int n = 1;
            while (n != 0) {
                chefMenu.showChefMenu();
                n = reader.readInt(System.in);
                chefMenu.makeOperations(n);
            }
        }
    }
}
