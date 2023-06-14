package tvn.springCourse.dao.interfaces;

import tvn.springCourse.secvices.interfaces.BookCrudService;

public interface BookAdministrationDao extends BookCrudService {
    void releaseBook(int id);
    void lendBook(int book_id, int person_id);
}
