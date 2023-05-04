package javier.correa.block7crudvalidation.controllers;

import javier.correa.block7crudvalidation.application.ProfesorService;
import javier.correa.block7crudvalidation.controllers.dto.ProfesorInputDto;
import javier.correa.block7crudvalidation.controllers.dto.ProfesorOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/profesor")
public class ProfesorController {
    @Autowired
    ProfesorService profesorService;

    @PostMapping
    public ResponseEntity<ProfesorOutputDto> addStudentToPersona(@RequestBody ProfesorInputDto profesorInputDto) throws Exception {
        return new ResponseEntity(profesorService.addProfesor(profesorInputDto), HttpStatus.OK);
    }
}
