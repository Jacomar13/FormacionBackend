package javier.correa.block7crudvalidation.controllers;

import javier.correa.block7crudvalidation.application.PersonaService;
import javier.correa.block7crudvalidation.application.StudentService;
import javier.correa.block7crudvalidation.application.StudentTopicService;
import javier.correa.block7crudvalidation.controllers.dto.student.StudentSimpleOutputDto;
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
    public Iterable<StudentTopicOutputDto>  showAllStudents(@RequestParam(defaultValue = "0", required = false) int pageNumber,
                                                             @RequestParam(defaultValue = "25", required = false) int pageSize) {
        return studentTopicService.getAllTopics(pageNumber, pageSize);
    }

}
