package javier.correa.block7crud.controladores;

import jakarta.persistence.EntityNotFoundException;
import javier.correa.block7crud.aplicaciones.PersonaServiceImpl;
import javier.correa.block7crud.controladores.dto.PersonaInputDto;
import javier.correa.block7crud.controladores.dto.PersonaOutputDto;
import javier.correa.block7crud.dominio.Persona;
import javier.correa.block7crud.repositorio.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/persona")
public class ModificarPersonaController {
    @Autowired
    PersonaServiceImpl personaService;
    @Autowired
    PersonaRepository personaRepository;

    @PutMapping("/{id}")
    public ResponseEntity<Persona> updatePersona(@PathVariable int id, @RequestBody PersonaInputDto personaInputDto){
        Persona persona = personaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Persona no encontrada"));

        if (personaInputDto.getNombre() != null) {
            persona.setNombre(personaInputDto.getNombre());
        }
        if (personaInputDto.getEdad() != null) {
            persona.setEdad(personaInputDto.getEdad());
        }
        if (personaInputDto.getPoblacion() != null) {
            persona.setPoblacion(personaInputDto.getPoblacion());
        }

        Persona personaGuardada = personaRepository.save(persona);
        return ResponseEntity.ok(personaGuardada);
    }
}
