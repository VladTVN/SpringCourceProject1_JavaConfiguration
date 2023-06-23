package tvn.springCourse.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;
    @NotEmpty(message = "Name cant ba empty")
    @Size(min = 3, max = 30, message = "Name mast be between 3 and 30 symbols")
    @Column(name = "name")
    private String name;
    @NotEmpty(message = "author cant ba empty")
    @Size(min = 3, max = 30, message = "author mast be between 3 and 30 symbols")
    @Column(name = "author")
    private String author;
    @Min(value = 0, message = "Age cant be less then 0")
    @Column(name = "publication_date")
    private int publicationDate;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    private Person owner;

    public Book(int bookId, String name, String author, int publicationDate) {
        this.bookId = bookId;
        this.name = name;
        this.author = author;
        this.publicationDate = publicationDate;
    }

    public Book() {
    }

    public Book(int bookId, String name, String author, int publicationDate, Person owner) {
        this.bookId = bookId;
        this.name = name;
        this.author = author;
        this.publicationDate = publicationDate;
        this.owner = owner;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
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
