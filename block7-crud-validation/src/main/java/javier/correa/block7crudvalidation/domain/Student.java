package javier.correa.block7crudvalidation.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


public class Student {

    Integer id_student;

    Persona persona;
    Integer num_hours_week;
    String coments;

    Profesor profesor;
    String branch;

}
