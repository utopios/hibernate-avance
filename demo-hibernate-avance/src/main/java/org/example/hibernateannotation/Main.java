package org.example.hibernateannotation;

import org.example.exercicehotel.util.HibernateUtil;
import org.hibernate.Filter;
import org.hibernate.Session;

public class Main {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Filter filter = session.enableFilter("ageFilter");

        filter.setParameter("minAge", 18);

        //Session session1 = en.unwrap(Session.class);
    }
}
