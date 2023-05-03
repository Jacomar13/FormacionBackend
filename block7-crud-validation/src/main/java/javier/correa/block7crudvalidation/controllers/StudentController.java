package javier.correa.block7crudvalidation.controllers;

import javier.correa.block7crudvalidation.application.StudentService;
import javier.correa.block7crudvalidation.controllers.dto.PersonaOutputDto;
import javier.correa.block7crudvalidation.controllers.dto.StudentInputDto;
import javier.correa.block7crudvalidation.controllers.dto.StudentOutputDto;
import javier.correa.block7crudvalidation.controllers.dto.StudentSimpleOutputDto;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/student")
@RestController
public class StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping
    public ResponseEntity<StudentOutputDto> addStudentToPersona(@RequestBody StudentInputDto studentInputDto) throws Exception{
        return new  ResponseEntity(studentService.addStudent(studentInputDto), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity showStudentByIdAndOutputType(@PathVariable int id, @RequestParam(defaultValue = "simple", required = true) String outputType) throws Exception{
        return  ResponseEntity.status(HttpStatus.OK).body(studentService.getStudentByIdAndOutputType(id,outputType));
    }

    @GetMapping
    public Iterable<StudentSimpleOutputDto>  showAllStudents(@RequestParam(defaultValue = "0", required = false) int pageNumber,
                                                             @RequestParam(defaultValue = "25", required = false) int pageSize) {
        return studentService.getAllStudents(pageNumber, pageSize);
    }
}
