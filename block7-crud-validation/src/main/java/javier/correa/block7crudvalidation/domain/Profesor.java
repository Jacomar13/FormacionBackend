package javier.correa.block7crudvalidation.domain;

import jakarta.persistence.*;
import javier.correa.block7crudvalidation.controllers.dto.ProfesorOutputDto;
import javier.correa.block7crudvalidation.controllers.dto.StudentOutputDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;


@Getter
@Setter
@Entity
@NoArgsConstructor
public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id_profesor;

    @OneToOne
    @JoinColumn(name = "id_persona")
    private Persona persona;
    @Column(name = "comentarios")
    private String comments;
    @Column(name = "rama")
    private String branch;
    @OneToMany(mappedBy = "profesor")
    private Set<Student> students;

    public ProfesorOutputDto profesorToOutputDto(){
        return new ProfesorOutputDto(this.id_profesor, this.comments, this.branch, this.persona.getId_persona(),this.persona.getUsuario(),this.persona.getName(), this.persona.getSurname(), this.persona.getCompany_email(), this.persona.getPersonal_email(), this.persona.getCity(), this.persona.isActive(), this.persona.getCreated_date(), this.persona.getImagen_url(), this.persona.getTermination_date());
    }

}
