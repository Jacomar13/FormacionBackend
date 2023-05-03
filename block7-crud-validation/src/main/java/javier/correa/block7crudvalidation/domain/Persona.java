package javier.correa.block7crudvalidation.domain;

import jakarta.persistence.*;

import javier.correa.block7crudvalidation.controllers.dto.PersonaInputDto;
import javier.correa.block7crudvalidation.controllers.dto.PersonaOutputDto;
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
    private String usuario;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    private String surname;

    @Column(nullable = false)
    private String company_email;

    @Column(nullable = false)
    private String personal_email;
    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private boolean active;

    @Column(nullable = false)
    private Date created_date;

    private String imagen_url;

    private String termination_date;
    @OneToOne
    private Student student;
    @OneToOne
    private Profesor profesor;

    public Persona(PersonaInputDto personaInputDto) {
        this.id_persona = personaInputDto.getId_persona();
        this.usuario = personaInputDto.getUsuario();
        this.password = personaInputDto.getPassword();
        this.name = personaInputDto.getName();
        this.surname = personaInputDto.getSurname();
        this.company_email = personaInputDto.getCompany_email();
        this.personal_email = personaInputDto.getPersonal_email();
        this.city = personaInputDto.getCity();
        this.active = personaInputDto.isActive();
        this.created_date = personaInputDto.getCreated_date();
        this.imagen_url = personaInputDto.getImagen_url();
        this.termination_date = personaInputDto.getTermination_date();
    }
    public PersonaOutputDto personaToOutputDto(){
        return new PersonaOutputDto(this.id_persona, this.usuario, this.name, this.surname, this.company_email, this.personal_email, this.city, this.active, this.created_date, this.imagen_url, this.termination_date);
    }
}
