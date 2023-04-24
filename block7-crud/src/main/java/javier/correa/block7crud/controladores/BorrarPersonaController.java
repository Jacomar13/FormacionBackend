package javier.correa.block7crud.controladores;

import javier.correa.block7crud.aplicaciones.PersonaService;
import javier.correa.block7crud.aplicaciones.PersonaServiceImpl;
import javier.correa.block7crud.controladores.dto.PersonaInputDto;
import javier.correa.block7crud.dominio.Persona;
import javier.correa.block7crud.repositorio.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/persona")
public class BorrarPersonaController {
    @Autowired
    PersonaService personaService;

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePersona(@PathVariable int id){
        try {
            personaService.deletePersonaById(id);
            return new ResponseEntity<>("Persona con el id: " + id + " ha sido eliminada", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("Persona con el id: " + id + " no ha sido encontrada", HttpStatus.NOT_FOUND);
        }
    }
}
