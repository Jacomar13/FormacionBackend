package javier.correa.block13mongodb.controller;

import javier.correa.block13mongodb.application.PersonaService;
import javier.correa.block13mongodb.controller.dto.PersonaInputDto;
import javier.correa.block13mongodb.controller.dto.PersonaOutputDto;
import javier.correa.block13mongodb.domain.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/persona")
public class PersonaController {
    @Autowired
    PersonaService personaService;

    @PostMapping
    public ResponseEntity<PersonaOutputDto> addPersona(@RequestBody PersonaInputDto personaInputDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(personaService.savePerson(personaInputDto));
    }
    @GetMapping("/oneByName")
    public ResponseEntity<PersonaOutputDto> findOneByName(@RequestParam String name){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(personaService.findOneByName(name).personaToOutputDto());
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/byName")
    public ResponseEntity<List<PersonaOutputDto>> findbByName(@RequestParam String name){
        try {
            List<PersonaOutputDto> persons = new ArrayList<>();
            for (Persona persona:personaService.findByName(name)){
                persons.add(persona.personaToOutputDto());
            }
            return ResponseEntity.status(HttpStatus.OK).body(persons);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/findAll")
    public ResponseEntity<List<PersonaOutputDto>> findAll(){
        List<PersonaOutputDto> persons = new ArrayList<>();
        for (Persona persona:personaService.getAllPerson()){
            persons.add(persona.personaToOutputDto());
        }
        return ResponseEntity.ok(persons);
    }
    @GetMapping("/findAllPaginated")
    public ResponseEntity<List<PersonaOutputDto>> findAllPaginated(@RequestParam(defaultValue = "0", required = false) int pageNumber,
                                                                   @RequestParam(defaultValue = "25", required = false) int pageSize){
        List<PersonaOutputDto> persons = new ArrayList<>();
        for (Persona persona:personaService.getAllPersonPaginated(pageNumber, pageSize)){
            persons.add(persona.personaToOutputDto());
        }
        return ResponseEntity.ok(persons);
    }

    @PutMapping()
    public ResponseEntity<PersonaOutputDto> updatePersona(@RequestParam String personId, @RequestBody PersonaInputDto personaInputDto){
        return ResponseEntity.ok(personaService.updateOnePerson(personId, personaInputDto));
    }
    @DeleteMapping()
    public ResponseEntity<String> deletePersona(@PathVariable PersonaInputDto personaInputDto){
        try {
            personaService.deletePerson(personaInputDto);
            return new ResponseEntity<>("La persona ha sido eliminada", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("La persona  no ha sido encontrada", HttpStatus.NOT_FOUND);
        }
    }
}
