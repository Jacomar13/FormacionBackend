package javier.correa.block7crudvalidation.controllers.dto.student;

import javier.correa.block7crudvalidation.controllers.dto.studentTopic.StudentTopicOutputDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor

public class StudentWithTopicsOutputDto {
    private int id_student;
    private Integer num_hours_week;
    private String comments;
    private String branch;
    private Set<StudentTopicOutputDto> estudios;
}
