package tvn.springCourse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tvn.springCourse.models.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

}
