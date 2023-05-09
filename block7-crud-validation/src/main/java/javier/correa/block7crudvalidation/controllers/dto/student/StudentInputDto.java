package javier.correa.block7crudvalidation.controllers.dto.student;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentInputDto {
    private int id_student;
    private int id_persona;
    private Integer num_hours_week;
    private String comments;
    private String branch;

}
