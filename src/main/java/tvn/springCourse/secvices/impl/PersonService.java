package tvn.springCourse.secvices.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tvn.springCourse.dao.interfaces.PersonCrudDao;
import tvn.springCourse.exceptions.PersonDaoException;
import tvn.springCourse.models.Book;
import tvn.springCourse.models.Person;
import tvn.springCourse.secvices.interfaces.PersonCrudService;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService implements PersonCrudService {
    private final PersonCrudDao personCrudDao;

    @Autowired
    public PersonService(PersonCrudDao PersonCrudDao) {
        this.personCrudDao = PersonCrudDao;
    }

    public List<Person> getAll() {
        return personCrudDao.getAll();
    }

    public Person getById(int id) throws PersonDaoException {
        return personCrudDao.getById(id);
    }

    public void update(Person person) {
        personCrudDao.update(person);
    }

    public void save(Person newPerson) {
        personCrudDao.save(newPerson);
    }

    public void delete(int id) {
        personCrudDao.delete(id);
    }

    @Override
    public List<Book> getPersonsBook(int id) {
        return personCrudDao.getPersonsBook(id);
    }

    @Override
    public Optional<Person> findByFullName(String fullName) {
        return personCrudDao.findByFullName(fullName);
    }

}
