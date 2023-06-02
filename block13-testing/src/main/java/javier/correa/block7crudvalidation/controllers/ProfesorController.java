package javier.correa.block7crudvalidation.controllers;

import javier.correa.block7crudvalidation.application.ProfesorService;
import javier.correa.block7crudvalidation.controllers.dto.profesor.ProfesorInputDto;
import javier.correa.block7crudvalidation.controllers.dto.profesor.ProfesorOutputDto;
import javier.correa.block7crudvalidation.controllers.dto.profesor.ProfesorSimpleOutputDto;
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
    public ResponseEntity<ProfesorOutputDto> addProfesor(@RequestBody ProfesorInputDto profesorInputDto) throws Exception {
        return new ResponseEntity(profesorService.addProfesor(profesorInputDto), HttpStatus.OK);
    }


    @PutMapping("/student")
    public ResponseEntity<String> addStudentToProfesor(@RequestParam int idProfesor, @RequestParam int idStudent){
        profesorService.addStudentToProfesor(idProfesor, idStudent);
        return ResponseEntity.ok().body("Al estudiante con id: " + idStudent + ", se le ha asignado correctamente el profesor con id: " + idProfesor);

    }

    @GetMapping("/{idProfesor}")
    public ResponseEntity<ProfesorWithStudentOutputDto> getProfesorWithStudent(@PathVariable int idProfesor) {
        return new ResponseEntity<>(profesorService.getProfesorWithStudents(idProfesor), HttpStatus.OK);
    }
    @GetMapping("/student/{id}")
    public ResponseEntity showProfesorByIdAndOutputType(@PathVariable int id, @RequestParam(defaultValue = "simple", required = true) String outputType){
        return ResponseEntity.status(HttpStatus.OK).body(profesorService.getProfesorByIdAndOutputType(id,outputType));
    }
    @GetMapping
    public Iterable<ProfesorSimpleOutputDto>  showAllProfesors(@RequestParam(defaultValue = "0", required = false) int pageNumber,
                                                              @RequestParam(defaultValue = "25", required = false) int pageSize) {
        return profesorService.getAllProfesors(pageNumber, pageSize);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProfesor(@PathVariable int id){
        profesorService.deleteProfesorById(id);
        return new ResponseEntity<>("El profesor con el id: " + id + " ha sido eliminado", HttpStatus.OK);
    }
    @GetMapping("/output/{id}")
    public ResponseEntity showSProfesorByIdAndOutputType(@PathVariable int id, @RequestParam(defaultValue = "simple", required = true) String outputType){
        return  ResponseEntity.status(HttpStatus.OK).body(profesorService.getProfesorByIdAndOutputType(id,outputType));
    }

}
