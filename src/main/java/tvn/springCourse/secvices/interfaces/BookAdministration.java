package tvn.springCourse.secvices.interfaces;

import tvn.springCourse.models.Book;

public interface BookAdministration extends BookCrudService{
    void releaseBook(int id);
    void lendBook(int book_id, int person_id);
}
