package javier.correa.block7crudvalidation.controllers;

import javier.correa.block7crudvalidation.application.ProfesorService;
import javier.correa.block7crudvalidation.controllers.dto.profesor.ProfesorInputDto;
import javier.correa.block7crudvalidation.controllers.dto.profesor.ProfesorOutputDto;
import javier.correa.block7crudvalidation.controllers.dto.profesor.ProfesorWithStudentOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/profesor")
public class ProfesorController {
    @Autowired
    ProfesorService profesorService;

    @PostMapping
    public ResponseEntity<ProfesorOutputDto> addStudentToPersona(@RequestBody ProfesorInputDto profesorInputDto) throws Exception {
        return new ResponseEntity(profesorService.addProfesor(profesorInputDto), HttpStatus.OK);
    }


    @PutMapping("/student")
    public ResponseEntity<String> addStudentToProfesor(@RequestParam int id_profesor, @RequestParam int id_student){
        try {
            profesorService.addStudentToProfesor(id_profesor, id_student);
            return ResponseEntity.ok().body("Al estudiante con id: " + id_student + ", se le ha asignado correctamente el profesor con id: " + id_profesor);
        }catch (Exception e){
            return ResponseEntity.badRequest().body("No se ha podido asignar el estudiante correctamente");
        }
    }

    @GetMapping("/{id_profesor}")
    public ResponseEntity<ProfesorWithStudentOutputDto> getProfesorWithStudent(@PathVariable int id_profesor) {
        return new ResponseEntity<>(profesorService.getProfesorWithStudents(id_profesor), HttpStatus.OK);
    }

}
