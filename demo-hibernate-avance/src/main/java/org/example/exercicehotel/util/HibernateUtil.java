package org.example.exercicehotel.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.util.Properties;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        // Configuration de la SessionFactory
        Properties properties = new Properties();
        //properties.put("hibernate.connection.isolation", Connection.TRANSACTION_READ_COMMITTED);
        return new Configuration().setProperties(properties).configure().buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
