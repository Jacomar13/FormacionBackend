package javier.correa.block7crudvalidation.application;

import javier.correa.block7crudvalidation.controllers.dto.student.StudentInputDto;
import javier.correa.block7crudvalidation.controllers.dto.student.StudentOutputDto;
import javier.correa.block7crudvalidation.controllers.dto.student.StudentSimpleOutputDto;

import java.util.List;

public interface StudentService {
    StudentOutputDto addStudent(StudentInputDto persona) throws Exception;

    Iterable<StudentSimpleOutputDto> getAllStudents(int pageNumber, int pageSize);

    Object getStudentByIdAndOutputType(int id, String outputType);

    void addTopicToStudent(int id_student, int id_study);

    void removeTopicOfStudent(int id_student, List<Integer> id_study);

}
