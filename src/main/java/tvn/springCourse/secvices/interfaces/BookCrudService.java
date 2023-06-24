package tvn.springCourse.secvices.interfaces;

import org.springframework.data.domain.Pageable;
import tvn.springCourse.exceptions.BookDaoException;
import tvn.springCourse.exceptions.PersonDaoException;
import tvn.springCourse.models.Book;
import tvn.springCourse.models.Person;


import java.util.List;

public interface BookCrudService {
    List<Book> getAll(Pageable pageable);

    List<Book> getAllSortByYear();

    List<Book> getAll();

    List<Book> findByNameStartingWith(String query);

    Book getById(int id) throws BookDaoException;

    void update(Book book);

    void save(Book book);

    void delete(int id);

    Person getBookOwner(int id) throws PersonDaoException, BookDaoException;
}
