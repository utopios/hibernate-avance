package org.example.concurrence;

import org.example.exercicehotel.util.HibernateUtil;
import org.hibernate.LockMode;
import org.hibernate.LockOptions;
import org.hibernate.Session;

import javax.persistence.OptimisticLockException;
import java.util.Random;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        Session sessionA = HibernateUtil.getSessionFactory().openSession();
        Session sessionB = HibernateUtil.getSessionFactory().openSession();

        /*Book book = new Book("test");
        book.setPrice(100.00);
        new BookDAO().insertBook(book, sessionA);*/


        //Condition de course
        /*sessionA.beginTransaction();
        Book book = sessionA.get(Book.class, 1L);
        book.setPrice(100.00);
        sessionA.getTransaction().commit();


        sessionB.beginTransaction();
        book = sessionB.get(Book.class, 1L);
        book.setPrice(150.00);
        sessionB.getTransaction().commit();*/

        //Lecture sale
        /*sessionA.beginTransaction();
        Book bookA = sessionA.get(Book.class, 1L);
        bookA.setPrice(160.00);

        sessionB.beginTransaction();
        Book bookB = sessionB.get(Book.class, 1L);
        System.out.println(bookB); // Prix => 150

        sessionA.getTransaction().commit();*/

        //Lecture non-repetable
        //Première lecture
        /*sessionA.beginTransaction();
        Book bookA = sessionA.get(Book.class, 1L);
        System.out.println(bookA);

        //Transaction B modifie la même donnée
        sessionB.beginTransaction();
        Book bookB = sessionB.get(Book.class, 1L);
        bookB.setPrice(new Random().nextDouble());
        sessionB.getTransaction().commit();

        //Transaction A lit à nouveau la même donnée
        sessionA.clear();
        bookA = sessionA.get(Book.class, 1L);
        System.out.println(bookA);*/


        //Lectures Fantômes
        //Transaction A
        /*sessionA.beginTransaction();
        List<Book> books = sessionA.createQuery("FROM Book").list();
        System.out.println(books.size());

        //Transaction B
        sessionB.beginTransaction();
        Book newBook = new Book("Nouveau Livre");
        sessionB.save(newBook);
        sessionB.getTransaction().commit();

        books = sessionA.createQuery("FROM Book").list();
        System.out.println(books.size());*/

        sessionA.beginTransaction();
        Book bookA = sessionA.get(Book.class, 1L, LockMode.PESSIMISTIC_FORCE_INCREMENT);

        sessionB.beginTransaction();
        Book bookB = sessionB.get(Book.class, 1L);

        bookA.setTitle("Title book Abis");
        sessionA.getTransaction().commit();

        bookB.setTitle("Title book B");
        sessionB.getTransaction().commit();
        /*try {
            sessionB.getTransaction().commit();
        }catch (OptimisticLockException ex) {
            sessionB.evict(bookB);
            //bookB = sessionB.get(Book.class, 1L);
            //bookB.setTitle("Title book B");
            //sessionB.getTransaction().commit();
        }*/
    }
}
