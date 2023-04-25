package javier.correa.block7crudvalidation.controllers;

import javier.correa.block7crudvalidation.application.PersonaServiceImpl;
import javier.correa.block7crudvalidation.controllers.dto.PersonaInputDto;
import javier.correa.block7crudvalidation.controllers.dto.PersonaOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persona")
public class PersonaController {
    @Autowired
    PersonaServiceImpl personaService;


    @PostMapping
    public ResponseEntity<PersonaOutputDto> addPersona(@RequestBody PersonaInputDto persona) throws Exception {
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(personaService.addPersona(persona));
        } catch (Exception e){
            if (persona.getUsuario()==null){throw new Exception("Usuario no puede ser nulo"); return new ResponseEntity<>(HttpStatus.BAD_REQUEST)}

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonaOutputDto> showPersonaById(@PathVariable int id){
        try {
            return new ResponseEntity<>(personaService.getPersonaById(id), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/usuario/{user}")
    public ResponseEntity<List<PersonaOutputDto>> showPersonaByUser(@PathVariable String user){
        try {
            return new ResponseEntity<>(personaService.getPersonabyUsuario(user), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
