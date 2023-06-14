package tvn.springCourse.dao.interfaces;

import tvn.springCourse.exceptions.PersonDaoException;
import tvn.springCourse.models.Book;
import tvn.springCourse.models.Person;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface PersonCrudDao {
    List<Person> getAll();
    Person getById(int id) throws PersonDaoException;
    void save(Person newPerson);
    void update(Person updatedPerson);
    void delete(int id);
    Optional<Person> findByFullName(String fullName);
    List<Book> getPersonsBook(int id);
}
