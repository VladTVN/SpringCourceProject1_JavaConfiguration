package tvn.springCourse.secvices.interfaces;

import tvn.springCourse.exceptions.BookDaoException;
import tvn.springCourse.exceptions.PersonDaoException;
import tvn.springCourse.models.Book;

import java.util.List;

public interface BookAdministration extends BookCrudService{
    void releaseBook(int id) throws BookDaoException;
    void lendBook(int book_id, int person_id) throws BookDaoException, PersonDaoException;


}
