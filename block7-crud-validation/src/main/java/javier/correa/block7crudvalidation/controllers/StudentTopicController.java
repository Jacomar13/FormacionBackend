package javier.correa.block7crudvalidation.controllers;

import javier.correa.block7crudvalidation.application.StudentTopicService;
import javier.correa.block7crudvalidation.controllers.dto.student.StudentWithTopicsOutputDto;
import javier.correa.block7crudvalidation.controllers.dto.studentTopic.StudentTopicInputDto;
import javier.correa.block7crudvalidation.controllers.dto.studentTopic.StudentTopicOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estudios")
public class StudentTopicController {

    @Autowired
    StudentTopicService studentTopicService;
    @PostMapping
    public ResponseEntity addStudentTopic (@RequestBody StudentTopicInputDto topic) throws Exception {
        return new ResponseEntity<>(studentTopicService.addTopic(topic), HttpStatus.CREATED);
    }

    @GetMapping
    public Iterable<StudentTopicOutputDto>  showAllTopics(@RequestParam(defaultValue = "0", required = false) int pageNumber,
                                                             @RequestParam(defaultValue = "25", required = false) int pageSize) {
        return studentTopicService.getAllTopics(pageNumber, pageSize);
    }

    @GetMapping("/student/{id_student}")
    public ResponseEntity<StudentWithTopicsOutputDto> getStudentWithTopics(@PathVariable int id_student) {
        return new ResponseEntity<>(studentTopicService.getListOfTopicByStudent(id_student), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTopic(@PathVariable int id){
        studentTopicService.deleteTopicById(id);
        return new ResponseEntity<>("La asignatura con id: " + id + " ha sido eliminada", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentTopicOutputDto> updateStudentTopic(@PathVariable int id, @RequestBody StudentTopicInputDto studentTopicInputDto){
        return new ResponseEntity<>(studentTopicService.updateTopic(id, studentTopicInputDto), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity showStudentByIdAndOutputType(@PathVariable int id) throws Exception{
        return  ResponseEntity.status(HttpStatus.OK).body(studentTopicService.getTopicById(id));
    }

}
