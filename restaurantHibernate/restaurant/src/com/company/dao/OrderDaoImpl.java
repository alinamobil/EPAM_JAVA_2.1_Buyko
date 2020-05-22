package com.company.dao;

import com.company.information.PriceMenu;
import com.company.operations.Operations;
import com.company.persistence.ClientOrder_DB;
import com.company.persistence.Orders_DB;
import com.company.reader.InfoReader;
import com.company.users.Client;
import com.company.util.HibernateSessionFactoryUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    static Logger logger = LogManager.getLogger();
    public InfoReader reader = new InfoReader();
    public Operations operations = new Operations();
    Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();

    public PriceMenu priceMenu = new PriceMenu();

    public OrderDaoImpl() {
    }

    @Override
    public void createOrder(Client client) {
        Operations operation = new Operations();
        if(operation.findUnpaidOrder() != 0) {
            System.out.println("You should pay for your previous order");
        }
        else {
            priceMenu.showMenu();
            System.out.println("Input new order by template (dishId:amount). Dishes must be separated by spaces: ");
            String[] dishes = reader.readDish(System.in);
            ArrayList<String> newDishes = new ArrayList<> (Arrays.asList(dishes));
            ArrayList<Integer> idList = (ArrayList<Integer>) session.createQuery("SELECT MAX(orderId)+1 AS id FROM DishOrder_DB").list();
            int id = 0;
            if (idList.size() != 0) {
                id = idList.get(0);
            }
            String sql;
            for(String str : newDishes) {
                sql = "INSERT INTO DishOrder_DB(dishId, orderId, dishAmount)" +
                        "SELECT distinct " + Integer.parseInt(str.split(":")[0]) + ", " + id + ", " + Integer.parseInt(str.split(":")[1]) +
                        " FROM DishOrder_DB";
                session.createQuery(sql).executeUpdate();
            }
            ClientOrder_DB clientOrder = new ClientOrder_DB(client.getId(), id);
            Transaction tx1 = session.beginTransaction();
            session.save(clientOrder);
            tx1.commit();
            logger.info("Order is created");
        }
    }

    @Override
    public void showOrdersList() {
        List<Orders_DB> orders = (List<Orders_DB>) session.createQuery("From Orders_DB").list();
        System.out.println("Order list:");
        for (Orders_DB ord : orders) {
            operations.readOrder(ord.getOrderId());
        }
    }

    @Override
    public void deleteOrder(int id) {
        session.createQuery("DELETE FROM Orders_DB WHERE orderId = " + id).executeUpdate();
        logger.info("Order is deleted");
    }

    @Override
    public void updateOrder() {
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

                sql = "INSERT INTO DishOrder_DB(dishId, orderId, dishAmount)" +
                        "SELECT distinct " + Integer.parseInt(str.split(":")[0]) + ", " + id + ", " + Integer.parseInt(str.split(":")[1]) +
                        " FROM DishOrder_DB";
                session.createQuery(sql).executeUpdate();
            }
            logger.info("Order is updated");
        }
    }
}
