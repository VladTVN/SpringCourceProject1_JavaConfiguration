package tvn.springCourse.secvices.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tvn.springCourse.enums.DaoErrorCode;
import tvn.springCourse.exceptions.BookDaoException;
import tvn.springCourse.exceptions.PersonDaoException;
import tvn.springCourse.models.Book;
import tvn.springCourse.models.Person;
import tvn.springCourse.repositories.BookRepository;
import tvn.springCourse.repositories.PersonRepository;
import tvn.springCourse.secvices.interfaces.BookAdministration;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookService implements BookAdministration {
    private final BookRepository bookRepository;
    private final PersonRepository personRepository;

    @Autowired
    public BookService(BookRepository bookRepository, PersonRepository personRepository) {
        this.bookRepository = bookRepository;
        this.personRepository = personRepository;
    }


    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public Book getById(int id) throws BookDaoException {
        return bookRepository.findById(id).orElseThrow(() -> new BookDaoException(DaoErrorCode.ENTITY_NOT_FOUND));
    }

    @Transactional
    public void update(Book book) {
        bookRepository.save(book);
    }

    @Transactional
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Transactional
    public void delete(int id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Person getBookOwner(int id) throws PersonDaoException, BookDaoException {
        Optional<Book> optionalBookFromDb = bookRepository.findById(id);
        if (optionalBookFromDb.isEmpty()) {
            throw new BookDaoException(DaoErrorCode.ENTITY_NOT_FOUND);
        }
        Person bookOwner = optionalBookFromDb.get().getOwner();
        if (Objects.isNull(bookOwner)) {
            throw new PersonDaoException(DaoErrorCode.ENTITY_NOT_FOUND);
        }
        return bookOwner;
    }


    @Transactional
    @Override
    public void releaseBook(int id) throws BookDaoException {
        Book bookFromDb = bookRepository.findById(id).orElseThrow(() -> new BookDaoException(DaoErrorCode.ENTITY_NOT_FOUND));
        bookFromDb.setOwner(null);

    }

    @Transactional
    @Override
    public void lendBook(int book_id, int person_id) throws BookDaoException, PersonDaoException {
        Book bookFromDb = bookRepository.findById(book_id).orElseThrow(() -> new BookDaoException(DaoErrorCode.ENTITY_NOT_FOUND));
        Person personFromDb = personRepository.findById(person_id).orElseThrow(() -> new PersonDaoException(DaoErrorCode.ENTITY_NOT_FOUND));
        bookFromDb.setOwner(personFromDb);
    }

}
