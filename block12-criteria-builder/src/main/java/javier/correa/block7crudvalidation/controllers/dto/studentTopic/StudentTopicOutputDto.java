package javier.correa.block7crudvalidation.controllers.dto.studentTopic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class StudentTopicOutputDto {
    private int id_study;
    private String asignatura;
    private String comment;
    private Date initial_date;
    private Date finish_date;
}
