package javier.correa.block7crudvalidation.domain;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.Data;


import java.util.Date;
@Data
public class Student_topic {

    Integer id_study;

    Profesor profesor;

    Student student;

    String asignatura;

    String comment;

    Date initial_date;

    Date finish_date;

}