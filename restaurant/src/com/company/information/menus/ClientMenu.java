package com.company.information.menus;

import com.company.dao.OrderDao;
import com.company.dao.OrderDaoImpl;
import com.company.operations.Operations;
import com.company.users.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class ClientMenu {
    Client client;

    private static OrderDao orderDao;
    static Logger logger = LogManager.getLogger();

    public ClientMenu(Client client) {
        this.client = client;
    }

    public void showClientMenu() {
        System.out.println("----------");
        System.out.println("Choose an option:");
        System.out.println("1. Create an order");
        System.out.println("2. Show my orders");
        System.out.println("3. Add dishes to order");
        System.out.println("4. Pay for order");
        System.out.println("0. Exit and save changes");
        System.out.print(">> ");
    }

    public void makeOperations(int n) throws SQLException {
        orderDao = new OrderDaoImpl();
        Operations operation = new Operations();

        switch (n) {
            case 1:
                orderDao.createOrder(client);
                break;
            case 2:
                operation.showClientsOrders(client.getId());
                break;
            case 3:
                operation.readOrder(operation.findUnpaidOrder());
                orderDao.updateOrder();
                break;
            case 4:
                operation.payForOrder();
                break;
            case 0:
                while(operation.findUnpaidOrder() != 0) {
                    operation.payForOrder();
                }
                logger.info("Changes saved");
                operation.closeConnection();
                break;
        }
    }
}
