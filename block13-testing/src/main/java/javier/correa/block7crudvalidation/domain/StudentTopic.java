package javier.correa.block7crudvalidation.domain;

import jakarta.persistence.*;
import javier.correa.block7crudvalidation.controllers.dto.studentTopic.StudentTopicInputDto;
import javier.correa.block7crudvalidation.controllers.dto.studentTopic.StudentTopicOutputDto;
import lombok.*;


import java.util.Date;
@Getter
@Setter
@Entity
@Table(name = "estudios")
@NoArgsConstructor
@AllArgsConstructor
@Generated

public class StudentTopic {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    int id_study;
    @ManyToOne
    @JoinColumn(name = "id_student", nullable = true)
    Student student;
    @Column(name = "asignatura")
    String asignatura;
    @Column(name = "comentarios")
    String comment;
    @Column(name = "initial_date")
    Date initial_date;
    @Column(name = "finish_date")
    Date finish_date;

    public StudentTopic(StudentTopicInputDto studentTopic){
        this.id_study = studentTopic.getId_study();
        this.asignatura = studentTopic.getAsignatura();
        this.comment = studentTopic.getComment();
        this.initial_date = studentTopic.getInitial_date();
        this.finish_date = studentTopic.getFinish_date();
    }

    public StudentTopicOutputDto studentTopicToOutputDto(){
        return new StudentTopicOutputDto(this.id_study, this.asignatura, this.comment, this.initial_date, this.finish_date);
    }
}