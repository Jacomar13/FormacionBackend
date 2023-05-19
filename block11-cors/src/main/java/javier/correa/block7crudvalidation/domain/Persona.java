package javier.correa.block7crudvalidation.domain;

import jakarta.persistence.*;

import javier.correa.block7crudvalidation.controllers.dto.persona.PersonaInputDto;
import javier.correa.block7crudvalidation.controllers.dto.persona.PersonaOutputDto;
import javier.correa.block7crudvalidation.controllers.dto.persona.PersonaProfesorWithStudentsOutputDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Persona {
    @Id
    @GeneratedValue
    /*@OneToOne*/
    private int id_persona;
    @Column(nullable = false, length = 10)
    private String username;
    @Column(nullable = false)
    private String passwd;
    @Column(nullable = false)
    private String name;
    private String surname;
    @Column(nullable = false)
    private String emailcomp;
    @Column(nullable = false)
    private String emailpers;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private boolean active;
    @Column(nullable = false)
    private Date created_date;
    private String imagen_url;
    private Date finish_date;
    @OneToOne
    private Student student;
    @OneToOne
    private Profesor profesor;

    public Persona(PersonaInputDto personaInputDto) {
        this.id_persona = personaInputDto.getId_persona();
        this.username = personaInputDto.getUsername();
        this.passwd = personaInputDto.getPasswd();
        this.name = personaInputDto.getName();
        this.surname = personaInputDto.getSurname();
        this.emailcomp = personaInputDto.getEmailcomp();
        this.emailpers = personaInputDto.getEmailpers();
        this.city = personaInputDto.getCity();
        this.active = personaInputDto.isActive();
        this.created_date = personaInputDto.getCreated_date();
        this.imagen_url = personaInputDto.getImagen_url();
        this.finish_date = personaInputDto.getFinish_date();
    }
    public PersonaOutputDto personaToOutputDto(){
        return new PersonaOutputDto(this.id_persona, this.username,this.passwd, this.name, this.surname, this.emailcomp, this.emailpers, this.city, this.active, this.created_date, this.imagen_url, this.finish_date);
    }
    public PersonaProfesorWithStudentsOutputDto personaProfesorWithStudentsToOutputDto(){
        return new PersonaProfesorWithStudentsOutputDto(this.id_persona, this.username, this.name, this.surname, this.emailcomp, this.emailpers, this.city, this.active, this.created_date, this.imagen_url, this.finish_date, this.profesor.profesorWithStudentToOutputDto());
    }
}
