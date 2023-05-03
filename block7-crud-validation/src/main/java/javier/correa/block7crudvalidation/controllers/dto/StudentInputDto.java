package javier.correa.block7crudvalidation.controllers.dto;

import javier.correa.block7crudvalidation.domain.Persona;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentInputDto {
    int id_student;
    int id_persona;
    Integer num_hours_week;
    String comments;
    String branch;

}
