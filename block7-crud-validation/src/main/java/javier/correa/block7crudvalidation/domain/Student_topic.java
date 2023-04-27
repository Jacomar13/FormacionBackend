package javier.correa.block7crudvalidation.domain;

import jakarta.persistence.*;
import lombok.Data;


import java.util.Date;
@Data
@Entity
@Table(name = "estudios")

public class Student_topic {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Integer id_study;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profesor_id")
    Profesor profesor;
    @ManyToMany(cascade = CascadeType.ALL)
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