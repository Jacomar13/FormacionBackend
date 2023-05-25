package javier.correa.block7crudvalidation.controllers;

import javier.correa.block7crudvalidation.application.PersonaService;
import javier.correa.block7crudvalidation.application.ProfesorClient;
import javier.correa.block7crudvalidation.controllers.dto.persona.PersonaInputDto;
import javier.correa.block7crudvalidation.controllers.dto.persona.PersonaOutputDto;
import javier.correa.block7crudvalidation.controllers.dto.profesor.ProfesorOutputDto;
import javier.correa.block7crudvalidation.domain.exception.EntityNotFoundException;
import javier.correa.block7crudvalidation.repository.PersonaRepository;
import javier.correa.block7crudvalidation.repository.PersonaRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;


@RestController
@RequestMapping("/persona")
public class PersonaController {
    @Autowired
    PersonaService personaService;

    /*private final RestTemplate restTemplate;

    public PersonaController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }*/
    @Autowired
    ProfesorClient profesorClient;
    @Autowired
    PersonaRepositoryImpl personaRepository;

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

    /*@GetMapping("/profesor/{id}")
    public ResponseEntity<ProfesorOutputDto> getProfesor(@PathVariable int id) {
        String url = "http://localhost:8081/profesor/{id}";
        ProfesorOutputDto profesor = restTemplate.getForObject(url, ProfesorOutputDto.class, id);
        if (profesor == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(profesor);
    }*/
    @GetMapping("/profesor/{id}")
    public ProfesorOutputDto getProfesor(@PathVariable int id) {
        ProfesorOutputDto profesor = profesorClient.getProfesor(id);
        return profesor;
    }
    @GetMapping("/customquery")
    public Iterable<PersonaOutputDto> findPersontByNameAndOrLastname(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String surname,
            @RequestParam(required = false) String usuario,
            @RequestParam(required = false) String created_date) {

        HashMap<String, Object> data = new HashMap<>();

        if(name != null) data.put("name",name);

        if(surname != null) data.put ("surname", surname);
        if(usuario != null) data.put("usuario",usuario);
        if(created_date != null) data.put ("created_date", created_date);

        return personaRepository.getCustomQuery(data);
    }

}
