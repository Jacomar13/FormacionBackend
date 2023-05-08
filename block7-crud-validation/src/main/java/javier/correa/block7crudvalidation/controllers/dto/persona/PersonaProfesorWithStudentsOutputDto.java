package javier.correa.block7crudvalidation.controllers.dto.persona;

import com.fasterxml.jackson.annotation.JsonProperty;
import javier.correa.block7crudvalidation.controllers.dto.profesor.ProfesorWithStudentOutputDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class PersonaProfesorWithStudentsOutputDto {

    private int id_persona;

    private String usuario;

    private String name;

    private String surname;

    private String company_email;

    private String personal_email;

    private String city;

    private boolean active;

    private Date created_date;

    private String imagen_url;

    private Date termination_date;
    @JsonProperty(value = "ValoresDeProfesor")
    private ProfesorWithStudentOutputDto profesorWithStudentOutputDto;
}
