package javier.correa.block13mongodb.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonaInputDto {
    private String personId;
    private String name;
    private long age;
    private List<String> favoriteBooks;
    private Date dateOfBirth;
}
