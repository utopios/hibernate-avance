package formation.demo.hibernate.service;

import formation.demo.hibernate.entity.Author;
import formation.demo.hibernate.repository.AuthorRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;

import static com.vladmihalcea.sql.SQLStatementCountValidator.assertInsertCount;

public class AuthorService {

    @PersistenceContext
    EntityManager entityManager;
    AuthorRepository authorRepository;
    public void update() {
        //entityManager.createQuery("Update Book set title= 'new vaue'").executeUpdate();
        /*Query nativeQuery = entityManager.createNativeQuery("update book set title = 'newvalue'");
        nativeQuery.unwrap(NativeQuery.class).addSynchronizedEntityClass(Book.class);*/
    }

    public List get() {
        return entityManager.createQuery("SELECT b FROM Book b").setHint("javax.persistence.fetchgraph",entityManager.getEntityGraph("graph-author")).setHint("org.hibernate.cacheable", true).getResultList();
    }

    public void mockAuthors() {
        Author a1 = new Author();
        Author a2 = new Author();
        List<Author> as = Arrays.asList(a1, a2);
        authorRepository.saveAll(as);

        //Pour l'exemple l'utilisation des assert sur le nombre de requêtes
        assertInsertCount(4);
    }
}
