package com.company.information;

import com.company.persistence.Dishes_DB;
import com.company.reader.InfoReader;
import com.company.util.HibernateSessionFactoryUtil;
import org.hibernate.Session;

import java.util.List;

public class PriceMenu {
    public InfoReader reader = new InfoReader();
    Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();

    public PriceMenu() {
    }

    public void showMenu() {
        List<Dishes_DB> dishes = (List<Dishes_DB>) session.createQuery("From Dishes_DB").list();
        System.out.println("Menu:");
        System.out.println("----------");
        for (Dishes_DB d : dishes) {
            System.out.println(String.format("%d. %s %4.2f",
                    d.getDishId(),
                    d.getName(),
                    d.getPrice()));
        }
    }

    public void showByKeyWord() {
        System.out.println("Input part of dish name: ");
        String dishName = reader.readString(System.in);
        List<Dishes_DB> dishes = (List<Dishes_DB>) session.createQuery("From Dishes_DB d WHERE name LIKE '%" + dishName + "%'").list();
        System.out.println("Dishes:");
        for (Dishes_DB d : dishes) {
            System.out.println(String.format("%d. %s %4.2f",
                    d.getDishId(),
                    d.getName(),
                    d.getPrice()));
        }
    }

    public void showInPriceRange() {
        System.out.println("Input price range: ");
        System.out.print("Start: ");
        double startPrice = reader.readDouble(System.in);
        System.out.print("End: ");
        double endPrice = reader.readDouble(System.in);

        String sql = "From Dishes_DB d " +
                    "WHERE price BETWEEN " + startPrice + "AND " + endPrice;
        List<Dishes_DB> dishes = (List<Dishes_DB>) session.createQuery(sql).list();
        for (Dishes_DB d : dishes) {
            System.out.println(String.format("%d. %s %4.2f",
                    d.getDishId(),
                    d.getName(),
                    d.getPrice()));
        }
    }
}
