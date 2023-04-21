package javier.correa.block7crud.controladores;

import javier.correa.block7crud.aplicaciones.PersonaServiceImpl;
import javier.correa.block7crud.controladores.dto.PersonaInputDto;
import javier.correa.block7crud.controladores.dto.PersonaOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/persona")
public class CrearPersonaController {
    @Autowired
    PersonaServiceImpl personaService;

    @PostMapping
    public ResponseEntity<PersonaOutputDto> addPersona(@RequestBody PersonaInputDto personaInputDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(personaService.addPersona(personaInputDto));
    }
}
