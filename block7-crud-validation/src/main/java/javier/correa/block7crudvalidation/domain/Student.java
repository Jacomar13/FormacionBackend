package javier.correa.block7crudvalidation.domain;

import jakarta.persistence.*;
import javier.correa.block7crudvalidation.controllers.dto.profesor.ProfesorWithStudentOutputDto;
import javier.correa.block7crudvalidation.controllers.dto.student.StudentInputDto;
import javier.correa.block7crudvalidation.controllers.dto.student.StudentOutputDto;
import javier.correa.block7crudvalidation.controllers.dto.student.StudentSimpleOutputDto;
import javier.correa.block7crudvalidation.controllers.dto.student.StudentWithTopicsOutputDto;
import javier.correa.block7crudvalidation.controllers.dto.studentTopic.StudentTopicOutputDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "estudiantes")

public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    int id_student;
    @Column(name = "horas_por_semana")
    Integer num_hours_week;
    @Column(name = "comentarios")
    String comments;
    @Column(name = "rama")
    String branch;
    @OneToOne(cascade =  CascadeType.ALL)
    @JoinColumn(name = "id_persona", nullable = false)
    private Persona persona;

    @ManyToOne

    @JoinColumn(name = "id_profesor", nullable = true)
    private Profesor profesor;

    @OneToMany(mappedBy = "student")
    Set<StudentTopic> estudios;


    public Student(StudentInputDto studentInputDto) {
        this.id_student = studentInputDto.getId_student();
        this.num_hours_week = studentInputDto.getNum_hours_week();
        this.comments = studentInputDto.getComments();
        this.branch = studentInputDto.getBranch();
    }

    public StudentOutputDto studentToOutputDto(){
        return new StudentOutputDto(this.id_student, this.num_hours_week, this.comments, this.branch, this.persona.getId_persona(),this.persona.getUsuario(),this.persona.getName(), this.persona.getSurname(), this.persona.getCompany_email(), this.persona.getPersonal_email(), this.persona.getCity(), this.persona.isActive(), this.persona.getCreated_date(), this.persona.getImagen_url(), this.persona.getTermination_date());
    }
    public StudentSimpleOutputDto studentSimpleToOutputDto(){
        return new StudentSimpleOutputDto(this.id_student, this.persona.getId_persona(), this.num_hours_week, this.comments, this.branch);
    }



    public StudentWithTopicsOutputDto studentWithTopicsOutputDto(){

        ArrayList<StudentTopicOutputDto> st = new ArrayList<StudentTopicOutputDto>();
        for (StudentTopic topic: this.estudios) {
            st.add(topic.studentTopicToOutputDto());
        }
        Set<StudentTopicOutputDto> estudiantesSet = new HashSet<>(st);

        return new StudentWithTopicsOutputDto(this.id_student, this.num_hours_week, this.comments, this.branch, estudiantesSet);
    }
}

