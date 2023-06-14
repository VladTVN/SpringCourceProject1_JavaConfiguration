package tvn.springCourse.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tvn.springCourse.enums.DaoErrorCode;
import tvn.springCourse.exceptions.BookDaoException;
import tvn.springCourse.exceptions.PersonDaoException;
import tvn.springCourse.models.Book;
import tvn.springCourse.models.Person;
import tvn.springCourse.secvices.interfaces.BookAdministration;
import tvn.springCourse.secvices.interfaces.PersonCrudService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookAdministration bookAdministration;
    private final PersonCrudService personCrudService;

    @Autowired
    public BookController(BookAdministration bookAdministration, PersonCrudService personCrudService) {
        this.bookAdministration = bookAdministration;
        this.personCrudService = personCrudService;
    }

    @GetMapping
    public String getPeopleList(Model model){
        model.addAttribute("books", bookAdministration.getAll());
        return "book/books";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable("id") int id, Model model,
                          @ModelAttribute("book") Book book, BindingResult bindingResult, @ModelAttribute("person") Person person){

        try {
            book = bookAdministration.getById(id);
            model.addAttribute("book", book);
        } catch (BookDaoException bookDAOException) {
            if (bookDAOException.getErrorCode().equals(DaoErrorCode.ENTITY_NOT_FOUND)){
            bindingResult.rejectValue("id","",  bookDAOException.errorMessage());
            }
        }

        try {
            Person owner = bookAdministration.getBookOwner(id);
            model.addAttribute("owner", owner);
        } catch (PersonDaoException personDaoException) {
            List<Person> people;

            if (personDaoException.getErrorCode().equals(DaoErrorCode.ENTITY_NOT_FOUND)) {
                people = personCrudService.getAll();
            } else {
                people = new ArrayList<>();
            }
            model.addAttribute("people", people);

        }

        return "book/book";
    }

    @PatchMapping("/{id}/lend")
    public String lendBook(@PathVariable("id") int id, @ModelAttribute("person") Person person,
                             @ModelAttribute("book") Book book){

        bookAdministration.lendBook(id, person.getPersonId());
        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/release")
    public String releaseBook(@PathVariable("id") int id, @ModelAttribute("book") Book book){

        bookAdministration.releaseBook(id);
        return "redirect:/books/" + id;
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model,
                       @ModelAttribute("book") Book book, BindingResult bindingResult){

        try {
            book = bookAdministration.getById(id);
            model.addAttribute("book",book);
        } catch (BookDaoException bookDAOException) {
            bindingResult.rejectValue("id","",  bookDAOException.errorMessage());
        }

        return "book/edit";
    }

    @PatchMapping("/{id}")
    public String edit(@PathVariable("id") int id, @ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "book/edit";
        }
        bookAdministration.update(book);
        return "redirect:/books";
    }

    @GetMapping("new")
    public String newBook(@ModelAttribute("book") Book book){
        return "book/new";
    }

    @PostMapping
    public String newPerson(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "book/new";
        }
        bookAdministration.save(book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        bookAdministration.delete(id);
        return "redirect:/books";
    }

}
