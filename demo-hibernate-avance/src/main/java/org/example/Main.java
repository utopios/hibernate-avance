package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.List;
public class Main {
    public static void main(String[] args) {

        //Insert fake data
        /*for(int i=1; i < 50; i++) {
            insertData(HibernateService.getInstance().getSessionFactory().openSession(), "toto"+String.valueOf(i), "address Toto "+ String.valueOf(i));
        }*/

        Session session = HibernateService.getInstance().getSessionFactory().openSession();



       /* Person person = session.get(Person.class, 1L);
        System.out.println(person.getName());*/



        //Lazy loading
        //System.out.println(person.getAddress());

        //Demo N+1
        List<Person> personList = session.createQuery("from Person", Person.class).list();

        for(Person p : personList) {
            System.out.println(p.getAddress());
        }
        session.close();
      /*  System.out.println(person.getName());
        System.out.println(person.getAddress());*/
    }



    private static void insertData(Session session, String name, String add) {
        try {
            // Démarrer une transaction
            session.beginTransaction();

            // Création d'une personne avec une adresse
            Address address = Address.builder().city(add).street(add).build();
            session.persist(address);
            Person p = Person.builder().name(name).address(address).build();
            session.persist(p);

            // Valider la transaction
            session.getTransaction().commit();
        } catch (Exception e) {
            // En cas d'erreur, annuler la transaction
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            // Fermer la session
            session.close();
        }
    }
}