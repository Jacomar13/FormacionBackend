package javier.correa.block7crud.controladores;

import javier.correa.block7crud.aplicaciones.PersonaService;
import javier.correa.block7crud.controladores.dto.PersonaInputDto;
import javier.correa.block7crud.controladores.dto.PersonaOutputDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/persona")
public class ModificarPersonaController {
    @Autowired
    PersonaService personaService;

    @PutMapping("/{id}")
    public ResponseEntity<PersonaOutputDto> updatePersona(@PathVariable int id, @RequestBody PersonaInputDto personaInputDto){

        return ResponseEntity.ok(personaService.updatePersona(id, personaInputDto).personaToOutputDto());
    }
}
