package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        /*List<Person> personList = session.createQuery("from Person", Person.class).list();

        for(Person p : personList) {
            System.out.println(p.getAddress());
        }
        session.close();
      *//*  System.out.println(person.getName());
        System.out.println(person.getAddress());*//*

        //Inserer utilisateur
        insertDataLibrairyUtilisateur(HibernateService.getInstance().getSessionFactory().openSession(), "u1");

        for(int i=1; i < 50; i++) {
            insertDataLivre(HibernateService.getInstance().getSessionFactory().openSession(), "toto"+String.valueOf(i), "Toto "+ String.valueOf(i));
        }*/

        //Démo cache Session
        Person person = session.get(Person.class, 1L);
        System.out.println(person);
        session.evict(person);
        person = session.get(Person.class, 1L);
        session.refresh(person);
        System.out.println(person);

        session.close();
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

    private static void insertDataLibrairyUtilisateur(Session session, String utilisateur) {
        try {
            session.beginTransaction();


            Utilisateur u = Utilisateur.builder().nom(utilisateur).build();
            session.persist(u);
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


        private static void insertDataLivre(Session session, String titre, String auteur) {
            try {
                session.beginTransaction();

                Livre livre = Livre.builder().titre(titre).anneePublication(2000).build();
                Set<Auteur> auteurSet = new HashSet<>();
                Auteur auteurEnregistre = Auteur.builder().nom(auteur).livres(new HashSet<>()).build();
                auteurEnregistre.addLivre(livre);
                session.persist(auteurEnregistre);
                auteurSet.add(auteurEnregistre);
                session.persist(livre);
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