package javier.correa.block7crud.controladores;

import javier.correa.block7crud.aplicaciones.PersonaService;
import javier.correa.block7crud.controladores.dto.PersonaOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persona")
public class MostrarPersonaController {

    @Autowired
    PersonaService personaService;

    @GetMapping("/{id}")
    public ResponseEntity<PersonaOutputDto> showById(@PathVariable int id){
        try {

            return new ResponseEntity<>(personaService.getPersonaById(id), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<PersonaOutputDto>> showByName(@PathVariable String nombre){
        try {
            System.out.println(personaService.getPersonaByNombre(nombre));
            return new ResponseEntity<>(personaService.getPersonaByNombre(nombre), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public Iterable<PersonaOutputDto> showAllPersonas(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "25", required = false) int pageSize) {
        return personaService.getAllPersonas(pageNumber, pageSize);
    }
}
