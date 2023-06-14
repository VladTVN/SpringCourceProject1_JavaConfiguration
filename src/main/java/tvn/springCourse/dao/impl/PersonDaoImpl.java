package tvn.springCourse.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import tvn.springCourse.dao.interfaces.PersonCrudDao;
import tvn.springCourse.enums.DaoErrorCode;
import tvn.springCourse.exceptions.PersonDaoException;
import tvn.springCourse.models.Book;
import tvn.springCourse.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDaoImpl implements PersonCrudDao {

    private final JdbcTemplate jdbcTemplate;
    private final String SQL_SELECT_ALL = "SELECT * FROM Person";
    private final String SQL_SELECT_BY_ID = "SELECT * FROM Person WHERE person_id=?";
    private final String SQL_INSERT = "INSERT INTO Person(full_name, year_of_birth) VALUES(?,?)";
    private final String SQL_UPDATE_BY_ID = "UPDATE Person SET full_name=?, year_of_birth=? WHERE person_id=?";
    private final String SQL_DELETE_BY_ID = "DELETE FROM Person WHERE person_id=?";
    private final String SQL_SELECT_PERSONS_BOOK = "SELECT * FROM Book WHERE Book.person_id=?";
    private final String SQL_SELECT_BY_FULL_NAME = "SELECT * FROM Person WHERE full_name=?";

    @Autowired
    public PersonDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> getAll(){
        return jdbcTemplate.query(SQL_SELECT_ALL, new BeanPropertyRowMapper<>(Person.class));
    }

    public Person getById(int id) throws PersonDaoException {
        List<Person> personList = jdbcTemplate.query(SQL_SELECT_BY_ID, new BeanPropertyRowMapper<>(Person.class), id);
        return personList.stream().findAny().orElseThrow(() -> new PersonDaoException(DaoErrorCode.ENTITY_NOT_FOUND));
    }

    public void save(Person newPerson){
        jdbcTemplate.update(SQL_INSERT, newPerson.getFullName(), newPerson.getYearOfBirth());
    }

    public void update(Person updatedPerson){
        jdbcTemplate.update(SQL_UPDATE_BY_ID, updatedPerson.getFullName(), updatedPerson.getYearOfBirth(), updatedPerson.getPersonId());
    }

    public void delete(int id){
        jdbcTemplate.update(SQL_DELETE_BY_ID, id);
    }

    @Override
    public Optional<Person> findByFullName(String fullName) {
        return jdbcTemplate.query(SQL_SELECT_BY_FULL_NAME, new BeanPropertyRowMapper<>(Person.class), fullName).stream().findAny();
    }

    @Override
    public List<Book> getPersonsBook(int id) {
        return jdbcTemplate.query(SQL_SELECT_PERSONS_BOOK, new BeanPropertyRowMapper<>(Book.class), id);
    }


}
