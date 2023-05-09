package javier.correa.block7crudvalidation.controllers.dto.studentTopic;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class StudentTopicInputDto {

    int id_study;
    String asignatura;
    String comment;
    Date initial_date;
    Date finish_date;
}