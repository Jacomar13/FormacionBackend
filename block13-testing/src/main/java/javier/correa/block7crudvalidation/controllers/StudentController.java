package javier.correa.block7crudvalidation.controllers;

import javier.correa.block7crudvalidation.application.StudentService;
import javier.correa.block7crudvalidation.controllers.dto.student.StudentInputDto;
import javier.correa.block7crudvalidation.controllers.dto.student.StudentOutputDto;
import javier.correa.block7crudvalidation.controllers.dto.student.StudentSimpleOutputDto;
import javier.correa.block7crudvalidation.controllers.dto.studentTopic.StudentTopicUpdateInputDto;
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
    public ResponseEntity<String> addTopicToStudent(@RequestParam int idStudent, @RequestParam int idStudy){
        studentService.addTopicToStudent(idStudent, idStudy);
        return ResponseEntity.ok().body("A la asignatura con id: " + idStudy + ", se le ha asignado correctamente el estudiante con id: " + idStudent);

    }

    @PutMapping("/topic/{idStudent}")
    public ResponseEntity<String> removeTopicOfStudent(@PathVariable int idStudent, @RequestBody StudentTopicUpdateInputDto inputDto) {

        studentService.removeTopicOfStudent(idStudent, inputDto.getId_study());
        return ResponseEntity.ok().body("A la asignatura con id: " + null + ", se le ha quitado correctamente el estudiante con id: " + idStudent);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id){
        studentService.deleteStudentById(id);
        return new ResponseEntity<>("Estudiante con el id: " + id + " ha sido eliminado", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentSimpleOutputDto> updatePersona(@PathVariable int id, @RequestBody StudentInputDto studentInputDto){
        return new ResponseEntity<>(studentService.updateStudent(id, studentInputDto), HttpStatus.OK);
    }
}
