package javier.correa.block7crudvalidation.controllers;

import javier.correa.block7crudvalidation.application.PersonaService;
import javier.correa.block7crudvalidation.application.StudentService;
import javier.correa.block7crudvalidation.application.StudentTopicService;
import javier.correa.block7crudvalidation.controllers.dto.studentTopic.StudentTopicInputDto;
import javier.correa.block7crudvalidation.controllers.dto.studentTopic.StudentTopicOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estudios")
public class StudentTopicController {

    @Autowired
    StudentTopicService studentTopicService;
    @PostMapping
    public ResponseEntity addStudentTopic (@RequestBody StudentTopicInputDto topic) throws Exception {
        return new ResponseEntity<>(studentTopicService.addTopic(topic), HttpStatus.CREATED);
    }

}
