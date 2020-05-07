package com.company.information.menus;

import com.company.dao.OrderDao;
import com.company.dao.OrderDaoImpl;
import com.company.information.PriceMenu;
import com.company.operations.Operations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class AdminMenu {
    private static OrderDao orderDao;
    static Logger logger = LogManager.getLogger();

    public void showAdminMenu() {
        System.out.println("----------");
        System.out.println("Choose an option:");
        System.out.println("1. Show clients");
        System.out.println("2. Show previous orders");
        System.out.println("3. Show price menu");
        System.out.println("4. Delete order");
        System.out.println("0. Exit");
        System.out.print(">> ");
    }

    public void makeOperations(int n) throws SQLException {
        orderDao = new OrderDaoImpl();
        Operations operation = new Operations();
        int id;
        switch (n) {
            case 1:
                operation.showClients();
                break;
            case 2:
                orderDao.showOrdersList();
                break;
            case 3:
                PriceMenu priceMenu = new PriceMenu();
                priceMenu.showMenu();
                break;
            case 4:
                orderDao.showOrdersList();
                id = operation.chooseIdentifier();
                orderDao.deleteOrder(id);
                break;
            case 0:
                logger.info("Changes saved");
                operation.closeConnection();
                break;
        }
    }
}
