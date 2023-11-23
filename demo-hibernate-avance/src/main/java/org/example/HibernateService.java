package org.example;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateService {

    private Configuration configuration;
    private SessionFactory sessionFactory;
    private static HibernateService instance = null;
    private HibernateService() {
        configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        // Création d'une SessionFactory
        sessionFactory = configuration.buildSessionFactory();
    }

    public static  HibernateService getInstance() {
        if(instance == null)
            instance= new HibernateService();
        return instance;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
