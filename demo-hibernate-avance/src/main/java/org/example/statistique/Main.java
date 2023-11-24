package org.example.statistique;

import org.example.*;
import org.example.exercicehotel.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        HibernateUtil.getSessionFactory().getStatistics().setStatisticsEnabled(true);
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        //Performances de sessions
        Long openSession = sessionFactory.getStatistics().getSessionOpenCount();
        StatelessSession sessionWithOutFirstCache = sessionFactory.openStatelessSession();
        System.out.println(openSession);
        // Sessions fermées, la durée de vie moyenne des sessions

        //opérations de cache.
        System.out.println(sessionFactory.getStatistics().getSecondLevelCachePutCount());

        //Suivi des requêtes
        System.out.println(sessionFactory.getStatistics().getQueryExecutionMaxTime());

        //Suivi des entités
        System.out.println(sessionFactory.getStatistics().getEntityLoadCount());

        //suivi de transactions
        System.out.println(sessionFactory.getStatistics().getTransactionCount());

    }




}