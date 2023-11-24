package org.example.association;

import org.example.concurrence.Book;
import org.example.exercicehotel.util.HibernateUtil;
import org.hibernate.LockMode;
import org.hibernate.Session;

public class Main {
    public static void main(String[] args) {
        Session sessionA = HibernateUtil.getSessionFactory().openSession();

        Commande commande = new Commande();
        Article article = new Article();
        article.setCommande(commande);

        //Cohérence des données en mémoire
        commande.getArticles().add(article);
    }
}
