package javier.correa.block7crudvalidation.domain;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

import java.util.List;

public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Integer id_student;
    @OneToOne
    @JoinColumn(name = "id_persona")
    Persona persona;
    @Column(name = "horas_por_semana")
    Integer num_hours_week;
    @Column(name = "comentarios")
    String coments;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profesor")
    Profesor profesor;
    @Column(name = "rama")
    String branch;
    @OneToMany
    List<Student_topic> estudios;
}
