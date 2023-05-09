package javier.correa.block7crudvalidation.controllers.dto.studentTopic;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class StudentTopicInputDto {

    private int id_study;
    private String asignatura;
    private String comment;
    private Date initial_date;
    private Date finish_date;
}
