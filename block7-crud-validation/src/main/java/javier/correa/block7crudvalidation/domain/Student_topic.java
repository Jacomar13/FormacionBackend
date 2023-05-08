package javier.correa.block7crudvalidation.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Date;
@Getter
@Setter
@Entity
@Table(name = "estudios")
@NoArgsConstructor

public class Student_topic {
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
}