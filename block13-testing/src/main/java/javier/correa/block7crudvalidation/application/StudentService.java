package javier.correa.block7crudvalidation.application;

import javier.correa.block7crudvalidation.controllers.dto.student.StudentInputDto;
import javier.correa.block7crudvalidation.controllers.dto.student.StudentOutputDto;
import javier.correa.block7crudvalidation.controllers.dto.student.StudentSimpleOutputDto;

import java.util.List;

public interface StudentService {
    StudentOutputDto addStudent(StudentInputDto persona) throws Exception;

    Iterable<StudentSimpleOutputDto> getAllStudents(int pageNumber, int pageSize);

    Object getStudentByIdAndOutputType(int id, String outputType);

    void addTopicToStudent(int idStudent, int idStudy);

    void removeTopicOfStudent(int idStudent, List<Integer> idStudy);
    void deleteStudentById(int id);

    StudentSimpleOutputDto updateStudent(int id, StudentInputDto studentInputDto);

}
