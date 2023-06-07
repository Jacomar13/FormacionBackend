package javier.correa.block7crudvalidation.controllers.dto.studentTopic;


import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StudentTopicInputDto {

    private int id_study;
    private String asignatura;
    private String comment;
    private Date initial_date;
    private Date finish_date;
}
