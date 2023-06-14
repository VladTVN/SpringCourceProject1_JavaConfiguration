package tvn.springCourse.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Component;

@Component
public class Person {
    private int personId;
    @NotEmpty(message = "Full name cant ba empty")
    @Size(min = 3, max = 30, message = "Full name mast be between 3 and 30 symbols")
    private String fullName;
    @Min(value = 0, message = "Year of birth cant be less then 0")
    private int yearOfBirth;

    public Person(int personId, String fullName, int yearOfBirth) {
        this.personId = personId;
        this.fullName = fullName;
        this.yearOfBirth = yearOfBirth;
    }

    public Person() {
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
}
