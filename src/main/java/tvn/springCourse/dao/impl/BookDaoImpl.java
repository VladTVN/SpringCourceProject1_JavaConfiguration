package tvn.springCourse.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import tvn.springCourse.dao.interfaces.BookAdministrationDao;
import tvn.springCourse.enums.DaoErrorCode;
import tvn.springCourse.exceptions.BookDaoException;
import tvn.springCourse.exceptions.PersonDaoException;
import tvn.springCourse.models.Book;
import tvn.springCourse.models.Person;

import java.util.List;

@Component
public class BookDaoImpl implements BookAdministrationDao {
    private final JdbcTemplate jdbcTemplate;
    private final String SQL_SELECT_ALL = "SELECT * FROM Book";
    private final String SQL_SELECT_BY_ID = "SELECT * FROM Book WHERE book_id=?";
    private final String SQL_INSERT = "INSERT INTO Book(name, author, publication_date) VALUES(?,?,?)";
    private final String SQL_UPDATE_BY_ID = "UPDATE Book SET name=?, author=?, publication_date=? WHERE book_id=?";
    private final String SQL_DELETE_BY_ID = "DELETE FROM Book WHERE book_id=?";
    private final String SQL_RELEASE_BOOK = "UPDATE Book SET person_id=NULL WHERE book_id=?";
    private final String SQL_ASSIGN_BOOK = "UPDATE Book SET person_id=? WHERE book_id=?";
    private final String SQL_SELECT_BOOK_OWNER = "SELECT * FROM Book" +
            " JOIN Person ON Book.person_id = Person.person_id WHERE Book.book_id=?";


    @Autowired
    public BookDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Book> getAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, new BeanPropertyRowMapper<>(Book.class));
    }

    @Override
    public Book getById(int id) throws BookDaoException {
        List<Book> bookList = jdbcTemplate.query(SQL_SELECT_BY_ID, new BeanPropertyRowMapper<>(Book.class), id);
        return bookList.stream().findAny().orElseThrow(() -> new BookDaoException(DaoErrorCode.ENTITY_NOT_FOUND));
    }

    @Override
    public void save(Book newBook) {
        jdbcTemplate.update(SQL_INSERT, newBook.getName(), newBook.getAuthor(), newBook.getPublicationDate());
    }

    @Override
    public void update(Book updatedBook) {
        jdbcTemplate.update(SQL_UPDATE_BY_ID, updatedBook.getName(),
                updatedBook.getAuthor(), updatedBook.getPublicationDate(), updatedBook.getBookId());
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update(SQL_DELETE_BY_ID, id);
    }

    @Override
    public Person getBookOwner(int id) throws PersonDaoException {
        List<Person> personList = jdbcTemplate.query(SQL_SELECT_BOOK_OWNER, new BeanPropertyRowMapper<>(Person.class), id);
        return personList.stream().findAny().orElseThrow(() -> new PersonDaoException(DaoErrorCode.ENTITY_NOT_FOUND));
    }

    @Override
    public void releaseBook(int id) {
        jdbcTemplate.update(SQL_RELEASE_BOOK, id);
    }
    @Override
    public void lendBook(int id, int person_id) {
        jdbcTemplate.update(SQL_ASSIGN_BOOK, person_id, id);
    }

}
