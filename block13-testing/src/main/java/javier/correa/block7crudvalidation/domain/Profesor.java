package javier.correa.block7crudvalidation.domain;

import jakarta.persistence.*;
import javier.correa.block7crudvalidation.controllers.dto.profesor.ProfesorInputDto;
import javier.correa.block7crudvalidation.controllers.dto.profesor.ProfesorOutputDto;
import javier.correa.block7crudvalidation.controllers.dto.profesor.ProfesorSimpleOutputDto;
import javier.correa.block7crudvalidation.controllers.dto.profesor.ProfesorWithStudentOutputDto;
import javier.correa.block7crudvalidation.controllers.dto.student.StudentSimpleOutputDto;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int idProfesor;

    @OneToOne
    @JoinColumn(name = "id_persona")
    private Persona persona;
    @Column(name = "comentarios")
    private String comments;
    @Column(name = "rama")
    private String branch;
    @OneToMany(mappedBy = "profesor")
    private Set<Student> students;


    public Profesor(ProfesorInputDto profesorInputDto) {
        this.idProfesor = profesorInputDto.getId_profesor();
        this.comments = profesorInputDto.getComments();
        this.branch = profesorInputDto.getBranch();
    }

    public ProfesorOutputDto profesorToOutputDto(){
        return new ProfesorOutputDto(this.idProfesor, this.comments, this.branch, this.persona.getId_persona(),this.persona.getUsuario(),this.persona.getName(), this.persona.getSurname(), this.persona.getCompany_email(), this.persona.getPersonal_email(), this.persona.getCity(), this.persona.isActive(), this.persona.getCreated_date(), this.persona.getImagen_url(), this.persona.getTermination_date());
    }
    public ProfesorSimpleOutputDto profesorToSimpleOutputDto(){
        return new ProfesorSimpleOutputDto(this.idProfesor, this.persona.getId_persona(), this.comments, this.branch);
    }
    public ProfesorWithStudentOutputDto profesorWithStudentToOutputDto(){

        ArrayList<StudentSimpleOutputDto> st = new ArrayList<StudentSimpleOutputDto>();
        for (Student student: this.students) {
            st.add(student.studentSimpleToOutputDto());
        }
        Set<StudentSimpleOutputDto> estudiantesSet = new HashSet<>(st);

        return new ProfesorWithStudentOutputDto(this.idProfesor, this.comments, this.branch, estudiantesSet);
    }
}
