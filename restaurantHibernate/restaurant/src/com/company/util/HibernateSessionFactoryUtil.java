package com.company.util;

import com.company.persistence.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;



public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil(){}

    public static SessionFactory getSessionFactory(){
        if (sessionFactory == null){
            try{
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Clients_DB.class);
                configuration.addAnnotatedClass(ClientOrder_DB.class);
                configuration.addAnnotatedClass(Dishes_DB.class);
                configuration.addAnnotatedClass(DishOrder_DB.class);
                configuration.addAnnotatedClass(DishOrder_DBPK.class);
                configuration.addAnnotatedClass(Orders_DB.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
