package com.company.operations;

import com.company.persistence.*;
import com.company.reader.InfoReader;
import com.company.util.HibernateSessionFactoryUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

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

    public String showClientsOrders(int id) {
        List<ClientOrder_DB> orders = (List<ClientOrder_DB>) session.createQuery("From ClientOrder_DB WHERE clientId=" + id).list();
        String result = "";
        if (orders.size() == 0) {
            result = "You have no orders yet";
        } else {
            for (ClientOrder_DB ord : orders) {
                result += readOrder(ord.getOrderId());
            }
        }
        return result;
    }

    public String readOrder(int id) {
        String sql = " FROM DishOrder_DB DO, Dishes_DB d, Orders_DB o " +
                " WHERE DO.dishId = d.dishId " +
                " AND DO.orderId = o.orderId " +
                " AND o.orderId=" + id;
        List<Orders_DB> order = (List<Orders_DB>) session.createQuery("SELECT " + "o" + sql).list();
        List<DishOrder_DB> dishOrder = (List<DishOrder_DB>) session.createQuery("SELECT " + "DO" + sql).list();
        List<Dishes_DB> dishes = (List<Dishes_DB>) session.createQuery("SELECT " + "d" + sql).list();

        String result = "";
        if (order.size() != 0) {
            result += "<tr><td>" + String.format("%d", order.get(0).getOrderId()) + "</td><td>";
            for (int i = 0; i < dishes.size(); i++) {
                result += dishes.get(i).getName() + " - " + dishOrder.get(i).getDishAmount() + " pieces<br>";
            }
            result += "</td><td>" + String.format("%4.2f", order.get(0).getTotal()) + "</td><td>" + order.get(0).getPaid() + "</td></tr>";
        }
        return result;
    }

    public int findUnpaidOrder() {
        List<Orders_DB> orders = (List<Orders_DB>) session.createQuery("From Orders_DB WHERE paid = 'false'").list();
        int id = 0;
        if (orders.size() != 0) {
            id = orders.get(0).getOrderId();
        }
        return id;
    }

    public String payForOrder() {
        String result =  "<p style=\"font-size: 20px;\"><b>Your order</b></p>" +
                        "<table class=\"table-view\">" +
                            "<thead>\n" +
                                "<tr style=\"background: lightgray;\">\n" +
                                    "<td><b>id</b></td>\n" +
                                    "<td><b>dishes</b></td>\n" +
                                    "<td><b>total</b></td>\n" +
                                    "<td><b>paid</b></td>\n" +
                                "</tr>\n" +
                            "</thead>" +
                            readOrder(findUnpaidOrder()) +
                        "</table>";
        return result;
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
