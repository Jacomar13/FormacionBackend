package javier.correa.block7crudvalidation.controllers.dto;

import javier.correa.block7crudvalidation.domain.Persona;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StudentOutputDto {
    String id_student;
    Persona persona;
    Integer num_hours_week;
    String coments;
    String branch;
}
