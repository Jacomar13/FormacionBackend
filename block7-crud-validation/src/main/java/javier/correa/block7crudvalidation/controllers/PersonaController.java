package javier.correa.block7crudvalidation.controllers;

import jakarta.persistence.EntityNotFoundException;
import javier.correa.block7crudvalidation.application.PersonaService;
import javier.correa.block7crudvalidation.controllers.dto.PersonaInputDto;
import javier.correa.block7crudvalidation.controllers.dto.PersonaOutputDto;
import javier.correa.block7crudvalidation.domain.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/persona")
public class PersonaController {
    @Autowired
    PersonaService personaService;


    @PostMapping
    public ResponseEntity<PersonaOutputDto> addPersona(@RequestBody PersonaInputDto persona) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(personaService.addPersona(persona));
    }

    @GetMapping("/{id}")
    public ResponseEntity showPersonaById(@PathVariable Integer id) throws Exception{
        try {
            return new ResponseEntity<>(personaService.getPersonaById(id), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/usuario/{user}")
    public ResponseEntity showPersonaByUser(@PathVariable String user) throws Exception{
        try {
            return new ResponseEntity<>(personaService.getPersonabyUsuario(user), HttpStatus.OK);
        } catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping
    public Iterable<PersonaOutputDto> showAllPersonas (
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "25", required = false) int pageSize) {
        return personaService.getAllPersonas(pageNumber, pageSize);

    }

}
