package javier.correa.block7crudvalidation.controllers.dto;

import javier.correa.block7crudvalidation.domain.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class ProfesorWithStudentOutputDto {
    private int id_profesor;
    private String comments;
    private String branch;
    private Set<StudentSimpleOutputDto> estudiantes;
}
