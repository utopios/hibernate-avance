package formation.demo.hibernate.service;

import formation.demo.hibernate.entity.Author;
import formation.demo.hibernate.repository.BookRepository;
import formation.demo.hibernate.entity.Book;
import formation.demo.hibernate.repository.AuthorRepository;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BookService {

    @PersistenceContext
    EntityManager entityManager;
    BookRepository bookRepository;
    AuthorRepository authorRepository;
    public void save(Book book) {
        var author = authorRepository.findById(1L);
    }
    public void batchBooksAndAuthors() {
        List<Book> books = new ArrayList<>();
        for(int i=1; i <= 10; i++) {
            Book b = new Book();
            for(int j=1; j < 10; j++) {
                b.addAuthor(new Author());
            }
        }
        authorRepository.saveInBatch(books);
    }

    public void findByGraph() {
        EntityGraph graph = entityManager.getEntityGraph("graph-author");
        HashMap<String, Object> props = new HashMap<>();
        props.put("javax.persistence.fetchgraph", graph);
        Book book = entityManager.find(Book.class, 1, props);

    }

}
