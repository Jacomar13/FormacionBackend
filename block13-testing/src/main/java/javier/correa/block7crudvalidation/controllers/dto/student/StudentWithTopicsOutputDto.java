package javier.correa.block7crudvalidation.controllers.dto.student;

import javier.correa.block7crudvalidation.controllers.dto.studentTopic.StudentTopicOutputDto;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Generated
public class StudentWithTopicsOutputDto {
    private int id_student;
    private Integer num_hours_week;
    private String comments;
    private String branch;
    private Set<StudentTopicOutputDto> estudios;
}
