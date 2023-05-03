package javier.correa.block7crudvalidation.domain;

import jakarta.persistence.*;
import javier.correa.block7crudvalidation.controllers.dto.PersonaInputDto;
import javier.correa.block7crudvalidation.controllers.dto.PersonaOutputDto;
import javier.correa.block7crudvalidation.controllers.dto.StudentInputDto;
import javier.correa.block7crudvalidation.controllers.dto.StudentOutputDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "estudiantes")

public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    String id_student;
    @OneToOne
    @JoinColumn(name = "id_persona")
    Persona persona;
    @Column(name = "horas_por_semana")
    Integer num_hours_week;
    @Column(name = "comentarios")
    String coments;

    @Column(name = "rama")
    String branch;

    /*@OneToMany
    List<Student_topic> estudios;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profesor")
    Profesor profesor;*/

    public Student(StudentInputDto studentInputDto) {
        this.id_student = studentInputDto.getId_student();
        this.persona = studentInputDto.getPersona();
        this.num_hours_week = studentInputDto.getNum_hours_week();
        this.coments = studentInputDto.getComents();
        this.branch = studentInputDto.getBranch();
    }

    public StudentOutputDto studentOutputDto(){
        return new StudentOutputDto(this.id_student, this.persona, this.num_hours_week, this.coments, this.branch);
    }
}

