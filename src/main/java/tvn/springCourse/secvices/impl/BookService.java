package tvn.springCourse.secvices.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tvn.springCourse.dao.interfaces.BookAdministrationDao;
import tvn.springCourse.exceptions.BookDaoException;
import tvn.springCourse.exceptions.PersonDaoException;
import tvn.springCourse.models.Book;
import tvn.springCourse.models.Person;
import tvn.springCourse.secvices.interfaces.BookAdministration;

import java.util.List;

@Service
public class BookService implements BookAdministration {
    private final BookAdministrationDao bookAdministrationDao;

    @Autowired
    public BookService(BookAdministrationDao bookAdministrationDao) {
        this.bookAdministrationDao = bookAdministrationDao;
    }


    public List<Book> getAll() {
        return bookAdministrationDao.getAll();
    }

    public Book getById(int id) throws BookDaoException {
        return bookAdministrationDao.getById(id);
    }

    public void update(Book book) {
        bookAdministrationDao.update(book);
    }

    public void save(Book book) {
        bookAdministrationDao.save(book);
    }

    public void delete(int id) {
        bookAdministrationDao.delete(id);
    }

    @Override
    public Person getBookOwner(int id) throws PersonDaoException {
        return bookAdministrationDao.getBookOwner(id);
    }


    @Override
    public void releaseBook(int id) {
        bookAdministrationDao.releaseBook(id);
    }

    @Override
    public void lendBook(int book_id, int person_id) {
        bookAdministrationDao.lendBook(book_id, person_id);
    }

}
