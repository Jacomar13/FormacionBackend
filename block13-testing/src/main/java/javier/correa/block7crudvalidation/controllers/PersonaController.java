package javier.correa.block7crudvalidation.controllers;

import javier.correa.block7crudvalidation.application.PersonaService;
import javier.correa.block7crudvalidation.application.ProfesorClient;
import javier.correa.block7crudvalidation.controllers.dto.persona.PersonaInputDto;
import javier.correa.block7crudvalidation.controllers.dto.persona.PersonaOutputDto;
import javier.correa.block7crudvalidation.controllers.dto.profesor.ProfesorOutputDto;
import javier.correa.block7crudvalidation.domain.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/persona")
public class PersonaController {
    @Autowired
    PersonaService personaService;

    @Autowired
    ProfesorClient profesorClient;

    @PostMapping
    public ResponseEntity<PersonaOutputDto> addPersona(@RequestBody PersonaInputDto persona) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(personaService.addPersona(persona));
    }

    @GetMapping("/{id}")
    public ResponseEntity showPersonaById(@PathVariable Integer id, @RequestParam String personType) throws EntityNotFoundException {
        return new ResponseEntity<>(personaService.getPersonaById(id, personType), HttpStatus.OK);
    }

    @GetMapping("/usuario/{user}")
    public ResponseEntity showPersonaByUser(@PathVariable String user) throws EntityNotFoundException {
            return new ResponseEntity<>(personaService.getPersonabyUsuario(user), HttpStatus.OK);
    }


    @GetMapping
    public Iterable<PersonaOutputDto> showAllPersonas (
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "25", required = false) int pageSize) {
        return personaService.getAllPersonas(pageNumber, pageSize);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePersona(@PathVariable int id){
        personaService.deletePersonaById(id);
        return new ResponseEntity<>("Persona con el id: " + id + " ha sido eliminada", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonaOutputDto> updatePersona(@PathVariable int id, @RequestBody PersonaInputDto personaInputDto){
        return new ResponseEntity<>(personaService.updatePersona(id, personaInputDto), HttpStatus.OK);
    }


    @GetMapping("/profesor/{id}")
    public ProfesorOutputDto getProfesor(@PathVariable int id) {
        return profesorClient.getProfesor(id);
    }
}
