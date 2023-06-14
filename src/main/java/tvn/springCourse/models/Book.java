package tvn.springCourse.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Component;

@Component
public class Book {
    private int bookId;
    @NotEmpty(message = "Name cant ba empty")
    @Size(min = 3, max = 30, message = "Name mast be between 3 and 30 symbols")
    private String name;
    @NotEmpty(message = "author cant ba empty")
    @Size(min = 3, max = 30, message = "author mast be between 3 and 30 symbols")
    private String author;
    @Min(value = 0, message = "Age cant be less then 0")
    private int publicationDate;


    public Book(int bookId, String name, String author, int publicationDate) {
        this.bookId = bookId;
        this.name = name;
        this.author = author;
        this.publicationDate = publicationDate;
    }

    public Book() {
    }



    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(int publicationDate) {
        this.publicationDate = publicationDate;
    }
}
