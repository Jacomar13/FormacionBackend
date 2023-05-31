package javier.correa.block13mongodb.domain;

import javier.correa.block13mongodb.controller.dto.PersonaInputDto;
import javier.correa.block13mongodb.controller.dto.PersonaOutputDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

import static java.util.Calendar.DATE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;
@Setter
@Getter
@Document(collection = "person")
public class Persona {
    @Id
    private String personId;
    private String name;
    private long age;
    private List<String> favoriteBooks;
    private Date dateOfBirth;
    public Persona() {
    }
    public Persona(String name, List<String> childrenName, Date dateOfBirth) {
        this.name = name;
        this.favoriteBooks = childrenName;
        this.dateOfBirth = dateOfBirth;
        this.age = getDiffYears(dateOfBirth, new Date());
    }
    // standard getters and setters
    private int getDiffYears(Date first, Date last) {
        Calendar a = getCalendar(first);
        Calendar b = getCalendar(last);
        int diff = b.get(YEAR) - a.get(YEAR);
        if (a.get(MONTH) > b.get(MONTH) ||
                (a.get(MONTH) == b.get(MONTH) && a.get(DATE) > b.get(DATE))) {
            diff--;
        }
        return diff;
    }
    private Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance(Locale.US);
        cal.setTime(date);
        return cal;
    }
    @Override
    public String toString() {
        return String.format("Person{personId='%s', name='%s', age=%d, dateOfBirth=%s}\n",
                personId, name, age, dateOfBirth);
    }
    public Persona (PersonaInputDto personaInputDto){
        this.personId = UUID.randomUUID().toString();
        this.name = personaInputDto.getName();
        this.age = personaInputDto.getAge();
        this.favoriteBooks = personaInputDto.getFavoriteBooks();
        this.dateOfBirth = personaInputDto.getDateOfBirth();
    }
    public PersonaOutputDto personaToOutputDto(){
        return new PersonaOutputDto(this.personId, this.name,this.age, this.favoriteBooks, this.dateOfBirth);
    }
}
