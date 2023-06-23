package tvn.springCourse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tvn.springCourse.models.Book;
import tvn.springCourse.models.Person;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    Optional<Person> findByFullName(String name);
}
