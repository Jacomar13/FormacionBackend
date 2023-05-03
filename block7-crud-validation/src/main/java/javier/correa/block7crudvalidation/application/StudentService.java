package javier.correa.block7crudvalidation.application;

import javier.correa.block7crudvalidation.controllers.dto.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StudentService {
    StudentOutputDto addStudent(StudentInputDto persona) throws Exception;

    Iterable<StudentSimpleOutputDto> getAllStudents(int pageNumber, int pageSize);

    Object getStudentByIdAndOutputType(int id, String outputType);
}
