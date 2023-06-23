package tvn.springCourse.secvices.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tvn.springCourse.enums.DaoErrorCode;
import tvn.springCourse.exceptions.PersonDaoException;
import tvn.springCourse.models.Book;
import tvn.springCourse.models.Person;
import tvn.springCourse.repositories.PersonRepository;
import tvn.springCourse.secvices.interfaces.PersonCrudService;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PersonService implements PersonCrudService {

    @PersistenceContext
    private EntityManager entityManager;

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    public List<Person> getAll() {
        return personRepository.findAll();
    }

    public Person getById(int id) throws PersonDaoException {
        return personRepository.findById(id).orElseThrow(() -> new PersonDaoException(DaoErrorCode.ENTITY_NOT_FOUND));
    }

    @Transactional
    public void update(Person person) {
        personRepository.save(person);
    }

    @Transactional
    public void save(Person newPerson) {
        personRepository.save(newPerson);
    }

    @Transactional
    public void delete(int id) {
        personRepository.deleteById(id);
    }


    @Override
    public List<Book> getPersonsBook(int id) throws PersonDaoException {
        Person person = personRepository.findById(id).orElseThrow(() -> new PersonDaoException(DaoErrorCode.ENTITY_NOT_FOUND));
        Hibernate.initialize(person.getBookList());
        return person.getBookList();
    }


    @Override
    public Optional<Person> findByFullName(String fullName) {
        return personRepository.findByFullName(fullName);
    }

}
