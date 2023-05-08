package javier.correa.block7crudvalidation.controllers.dto.student;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StudentSimpleOutputDto {
    int id_student;
    int id_persona;
    Integer num_hours_week;
    String comments;
    String branch;
}
