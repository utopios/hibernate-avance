package formation.demo.hibernate.repository;

import formation.demo.hibernate.entity.Author;
import formation.demo.hibernate.projection.SimpleAuthor;
import formation.demo.hibernate.impl.BatchRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AuthorRepository extends BatchRepository<Author, Long> {

    @Transactional(readOnly = true)
    List<SimpleAuthor> findAllById(int id);
}
