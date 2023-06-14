package tvn.springCourse.dao.interfaces;

import tvn.springCourse.exceptions.BookDaoException;
import tvn.springCourse.exceptions.PersonDaoException;
import tvn.springCourse.models.Book;
import tvn.springCourse.models.Person;

import java.util.List;

public interface BookCrudDao {
    List<Book> getAll();
    Book getById(int id) throws BookDaoException;
    void save(Book newBook);
    void update(Book updatedBook);
    void delete(int id);

    Person getBookOwner(int id) throws PersonDaoException;

}
