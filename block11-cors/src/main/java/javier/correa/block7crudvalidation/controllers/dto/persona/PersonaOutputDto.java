package javier.correa.block7crudvalidation.controllers.dto.persona;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class PersonaOutputDto {
    private int id_persona;

    private String username;

    private String passwd;

    private String name;

    private String surname;

    private String emailcomp;
    private String emailpers;

    private String city;

    private boolean active;

    private Date created_date;

    private String imagen_url;

    private Date finish_date;
}
