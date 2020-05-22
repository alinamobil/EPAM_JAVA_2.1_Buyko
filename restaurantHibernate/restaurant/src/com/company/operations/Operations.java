package com.company.operations;

import com.company.persistence.*;
import com.company.reader.InfoReader;
import com.company.util.HibernateSessionFactoryUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Operations {
    static Logger logger = LogManager.getLogger();
    public InfoReader reader = new InfoReader();
    Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();

    public Operations() {
    }

    public void addDishToPriceMenu() {
        System.out.println("Input name of new dish: ");
        String dishName = reader.readString(System.in);
        System.out.println("Input price of new dish: ");
        double dishPrice = reader.readDouble(System.in);

        String sql = "INSERT INTO Dishes_DB(dishId, name, price) " +
                "SELECT MAX(dishId)+1, '" + dishName + "', " + dishPrice + " FROM Dishes_DB";
        session.createQuery(sql).executeUpdate();
    }

    public int chooseIdentifier() {
        ArrayList<Orders_DB> orders = (ArrayList<Orders_DB>) session.createQuery("FROM Orders_DB").list();
        ArrayList<Integer> identifiers = new ArrayList<>();
        for (Orders_DB o : orders) {
            identifiers.add(o.getOrderId());
        }

        System.out.println("Input an id: ");
        int id = reader.readId(System.in);
        while(!identifiers.contains(id)) {
            System.out.println("Input correct id: ");
            id = reader.readId(System.in);
        }
        return id;
    }

    public void showClientsOrders(int id) {
        List<ClientOrder_DB> orders = (List<ClientOrder_DB>) session.createQuery("From ClientOrder_DB WHERE clientId=" + id).list();
        if (orders.size() == 0) {
            System.out.println("You have no orders yet");
        } else {
            for (ClientOrder_DB ord : orders) {
                readOrder(ord.getOrderId());
            }
        }
    }

    public void readOrder(int id) {
        String sql = " FROM DishOrder_DB DO, Dishes_DB d, Orders_DB o " +
                " WHERE DO.dishId = d.dishId " +
                " AND DO.orderId = o.orderId " +
                " AND o.orderId=" + id;
        List<Orders_DB> order = (List<Orders_DB>) session.createQuery("SELECT " + "o" + sql).list();
        List<DishOrder_DB> dishOrder = (List<DishOrder_DB>) session.createQuery("SELECT " + "DO" + sql).list();
        List<Dishes_DB> dishes = (List<Dishes_DB>) session.createQuery("SELECT " + "d" + sql).list();
        if (order.size() != 0) {
            System.out.println("----------");
            System.out.println("Order: ");
            System.out.print(String.format("id: %d", order.get(0).getOrderId()) + ", dishes{ ");
            for (int i = 0; i < dishes.size(); i++) {
                System.out.print(dishes.get(i).getName() + " - " + dishOrder.get(i).getDishAmount() + " pieces ");
            }
            System.out.println(String.format("}, total: %4.2f", order.get(0).getTotal()) + ", paid: " + order.get(0).getPaid());
            System.out.println("----------");
        }
    }

    public int findUnpaidOrder() {
        List<Orders_DB> orders = (List<Orders_DB>) session.createQuery("From Orders_DB WHERE paid = 'false'").list();
        int id = 0;
        if (orders.size() != 0) {
            id = orders.get(0).getOrderId();
        }
        return id;
    }

    public void payForOrder() {
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
            session.createQuery("UPDATE Orders_DB SET paid ='true' WHERE paid = 'false'").executeUpdate();
            logger.info("Payment succeed");
        }
    }

    public void showClients() {
        List<Clients_DB> clients = (List<Clients_DB>) session.createQuery("From Clients_DB").list();
        System.out.println("Clients:");
        System.out.println("----------");
        for (Clients_DB cl : clients) {
            System.out.println(String.format("id: %d, name: %s",
                    cl.getClientId(),
                    cl.getName()));
        }
    }

    public void closeConnection() {
        session.close();
        logger.info("Session closed");
    }
}
