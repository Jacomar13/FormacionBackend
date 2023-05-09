package javier.correa.block7crudvalidation.controllers;

import javier.correa.block7crudvalidation.application.StudentService;
import javier.correa.block7crudvalidation.controllers.dto.student.StudentInputDto;
import javier.correa.block7crudvalidation.controllers.dto.student.StudentOutputDto;
import javier.correa.block7crudvalidation.controllers.dto.student.StudentSimpleOutputDto;

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
    public ResponseEntity<StudentOutputDto> addStudentToPersona(@RequestBody StudentInputDto studentInputDto) throws Exception {
        return new ResponseEntity(studentService.addStudent(studentInputDto), HttpStatus.OK);
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



    @PutMapping("/topic")
    public ResponseEntity<String> addTopicToStudent(@RequestParam int id_student, @RequestParam int id_study){
        try {
            studentService.addTopicToStudent(id_student, id_study);
            return ResponseEntity.ok().body("A la asignatura con id: " + id_study + ", se le ha asignado correctamente el estudiante con id: " + id_student);
        }catch (Exception e){
            return ResponseEntity.badRequest().body("No se ha podido asignar el estudiante correctamente");
        }
    }
}
