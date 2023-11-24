package org.example.concurrence;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class BookDAO {

    /**
     * Insère un livre dans la base de données.
     *
     * @param book Le livre à insérer.
     */
    public void insertBook(Book book, Session session) {
        Transaction transaction = null;
        try {
            // Commence une transaction
            transaction = session.beginTransaction();

            // Enregistre l'objet book dans la base de données
            session.save(book);

            // Valide la transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}